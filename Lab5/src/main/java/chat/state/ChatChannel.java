package chat.state;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wiktor on 15/05/16.
 */
class ChatChannel {

    private static final Logger logger = LoggerFactory.getLogger(ChatChannel.class);

    private final String channelName;
    private List<String> usersNames;
    private JChannel channel;


    ChatChannel(String channelName) {
        this.channelName = channelName;
        this.usersNames = new ArrayList<>();
    }

    void connect(String nickname, String address, Receiver receiver) throws Exception {
        if (channel == null) {
            JChannel channel = ChatUtils.createChannel(address);
            channel.setName(nickname);
            channel.setReceiver(receiver);
            channel.connect(channelName);
            this.channel = channel;
        } else {
            logger.warn("Already connected to channel: {}", channelName);
        }
    }


    void disconnect(){
        if(channel != null) {
            channel.close();
        } else {
            logger.warn("User is not connected to channel: {}", channelName);
        }
    }

    void sendMessage(String message) throws Exception {
        channel.send(new Message(null, message));
    }

    List<ChatOperationProtos.ChatAction> getChatActions() {
        return usersNames.stream().map( e -> ChatOperationProtos.ChatAction.newBuilder()
                 .setAction(ChatOperationProtos.ChatAction.ActionType.JOIN)
                 .setChannel(channelName)
                 .setNickname(e)
                 .build())
                .collect(Collectors.toList());
    }

    List<String> getUsersNames() {
        return usersNames;
    }

    void setUsersNames(List<String> usersNames) {
        this.usersNames = usersNames;
    }

    void addUser(String name) {
        this.usersNames.add(name);
    }

    void removeUser(String name) {
        this.usersNames.remove(name);
    }

    String getChannelName() {
        return channelName;
    }
}
