package chat.receivers;

import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

/**
 * Created by wiktor on 16/05/16.
 */
public class ChatReceiver extends ReceiverAdapter {

    private String nickname;
    private String channelName;

    public ChatReceiver(String channelName, String nickname) {
        this.channelName = channelName;
        this.nickname = nickname;
    }

    @Override
    public void receive(Message msg) {
        String sender = msg.getSrc().toString();
        if (!sender.equals(nickname)) {
            System.out.println("Channel: " + channelName + "||From: " + sender);
            System.out.println(msg.getObject());
        }
    }
}
