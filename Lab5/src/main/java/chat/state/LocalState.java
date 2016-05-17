package chat.state;

import chat.receivers.ChatReceiver;
import chat.state.exceptions.StateException;
import org.jgroups.JChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wiktor on 15/05/16.
 */
//Making all methods synchronized is far from optimal, but well it's not the case of this assignment.
public class LocalState {
    private static final Logger logger = LoggerFactory.getLogger(LocalState.class);
    private static final String ADDRESS_PREFIX = "230.0.0.";

    private final JChannel managementChannel;
    private final String nickname;

    private Map<String, ChatChannel> chatChannels;
    private Set<String> chatUsers;
    private List<String> clientChannels;
    private boolean initialized = false;

    public LocalState(JChannel managementChannel, String nickname) {
        this.nickname = nickname;
        this.managementChannel = managementChannel;
        this.clientChannels = new ArrayList<>();
        this.chatUsers = new HashSet<>();
        chatChannels = new HashMap<>();
    }

    public synchronized ChatOperationProtos.ChatState getStateToSend() throws StateException {
        if (!initialized) {
            throw new StateException("Cannot get state. This Client hasn't been initialized yet!");
        }
        return ChatOperationProtos.ChatState.newBuilder()
                .addAllState(chatChannels
                        .values()
                        .stream()
                        .flatMap(e -> e.getChatActions().stream())
                        .collect(Collectors.toList()))
                .build();
    }

    public synchronized void setNewState(ChatOperationProtos.ChatState chatState) {
        if (initialized) {
            logger.warn("This client already have localState. All active connection will be closed");
            clientChannels.stream().forEach(e -> {
                try {
                    chatChannels.get(e).disconnect();
                    sendManagementMessage(e, ChatOperationProtos.ChatAction.ActionType.LEAVE);
                } catch (Exception e1) {
                    logger.warn("Unable to send leave massage to channel: {}", e);
                }
            });
            clientChannels = new ArrayList<>();
        }
        initialized = true;
        Map<String, ChatChannel> stateMap = new HashMap<>();
        chatState.getStateList().stream().forEach(e -> {
            if (!stateMap.containsKey(e)) {
                ChatChannel chatChannel = new ChatChannel(e.getChannel());
                chatChannel.addUser(e.getNickname());
                stateMap.put(e.getChannel(), chatChannel);
            } else {
                stateMap.get(e).addUser(e.getNickname());
            }
            chatUsers.add(e.getNickname());
        });
        this.chatChannels = stateMap;
    }

    public synchronized void leaveChannel(String channelName) throws Exception {
        clientChannels.remove(channelName);
        chatChannels.get(channelName).disconnect();
        sendManagementMessage(channelName, ChatOperationProtos.ChatAction.ActionType.LEAVE);
    }

    public synchronized void joinChannel(String channelName) throws Exception {
        addNewUserToChannel(nickname, channelName);
        chatChannels.get(channelName).connect(nickname, ADDRESS_PREFIX + channelName, new ChatReceiver(channelName, nickname));
        sendManagementMessage(channelName, ChatOperationProtos.ChatAction.ActionType.JOIN);
        clientChannels.add(channelName);
    }

    public synchronized void leaveAll() throws Exception {
        for (String s : new ArrayList<>(clientChannels)) {
            leaveChannel(s);
        }
        managementChannel.close();
    }

    public synchronized void getChannelList() {
        chatChannels.values().stream().forEach(e -> {
            System.out.print("\nChannel: " + e.getChannelName() + " Users: ");
            e.getUsersNames().stream().forEach(ee -> System.out.print(ee + ", "));
        });
    }

    public synchronized void sendMessage(String channelName, String message) throws Exception {
        chatChannels.get(channelName).sendMessage(message);
    }

    public synchronized void updateActiveUsers(List<String> newUsers) {
        this.chatUsers.clear();
        this.chatUsers.addAll(newUsers);
        List<String> inactiveChannels = new ArrayList<>();
        chatChannels.values().stream().forEach(e -> {
            e.getUsersNames().retainAll(newUsers);
            if (e.getUsersNames().isEmpty()) {
                inactiveChannels.add(e.getChannelName());
            }
        });

        inactiveChannels.stream().forEach(chatChannels::remove);
    }


    public synchronized void addNewUserToChannel(String nickname, String channel) {
        if (!chatChannels.containsKey(channel)) {
            chatChannels.put(channel, new ChatChannel(channel));
        }
        chatChannels.get(channel).addUser(nickname);
    }

    public synchronized void deleteUserFromChannel(String nickname, String channel) {
        chatChannels.get(channel).removeUser(nickname);
        if (chatChannels.get(channel).getUsersNames().isEmpty()) {
            chatChannels.remove(channel);
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setInitialized() {
        initialized = true;
    }

    private synchronized void sendManagementMessage(String channelName, ChatOperationProtos.ChatAction.ActionType type) throws Exception {
        managementChannel.send(ChatUtils.createActionMessage(nickname, channelName, type));
    }

}
