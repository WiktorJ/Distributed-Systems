package chat;

import chat.receivers.ManagerReceiver;
import chat.state.ChatUtils;
import chat.state.LocalState;
import org.jgroups.JChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by wiktor on 15/05/16.
 */
public class Chat {

    private static final Logger logger = LoggerFactory.getLogger(Chat.class);
    private static final String MANAGEMENT_ADDRESS = "228.8.8.8";
    private static final String MANAGEMENT_CHANNEL_NAME = "ChannelManagement321123";

    private String nickname;
    private LocalState localState;

    public Chat(String nickname) throws Exception {
        this.nickname = nickname;
        JChannel channel = ChatUtils.createChannel(MANAGEMENT_ADDRESS);
        this.localState = new LocalState(channel, nickname);
        channel.setName(nickname);
        channel.setReceiver(new ManagerReceiver(this.localState));
        channel.connect(MANAGEMENT_CHANNEL_NAME);
        channel.getState(null, 0);
        localState.setInitialized();
    }

    public void sendMessage(String channel, String message) {
        try {
            localState.sendMessage(channel, message);
        } catch (Exception e) {
            logger.warn("Unable to send message to channel: {}", channel ,e);
        }
    }
    public void getChannelsList(){
        localState.getChannelList();
    }
    public void joinChannel(String channel){
        try {
            localState.joinChannel(channel);
        } catch (Exception e) {
            logger.warn("Unable to join channel: {}", channel ,e);
        }
    }
    public void leaveChannel(String channel){
        try {
            localState.leaveChannel(channel);
        } catch (Exception e) {
            logger.warn("Unable to leave channel: {}", channel ,e);
        }
    }
    public void leaveChat(){
        try {
            localState.leaveAll();
        } catch (Exception e) {
            logger.warn("Unable to leave chat",e);
        }
    }

}
