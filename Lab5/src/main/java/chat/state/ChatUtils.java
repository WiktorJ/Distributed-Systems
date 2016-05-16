package chat.state;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.protocols.BARRIER;
import org.jgroups.protocols.FD_ALL;
import org.jgroups.protocols.FD_SOCK;
import org.jgroups.protocols.FRAG2;
import org.jgroups.protocols.MERGE2;
import org.jgroups.protocols.MFC;
import org.jgroups.protocols.PING;
import org.jgroups.protocols.UDP;
import org.jgroups.protocols.UFC;
import org.jgroups.protocols.UNICAST2;
import org.jgroups.protocols.VERIFY_SUSPECT;
import org.jgroups.protocols.pbcast.FLUSH;
import org.jgroups.protocols.pbcast.GMS;
import org.jgroups.protocols.pbcast.NAKACK;
import org.jgroups.protocols.pbcast.STABLE;
import org.jgroups.protocols.pbcast.STATE_TRANSFER;
import org.jgroups.stack.ProtocolStack;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by wiktor on 15/05/16.
 */
public class ChatUtils {

    public static JChannel createChannel() throws Exception {
        return createChannel(null);
    }

    public static JChannel createChannel(String multicastAddress) throws Exception {
        JChannel channel = new JChannel(false);
        ProtocolStack stack = new ProtocolStack();
        channel.setProtocolStack(stack);
        UDP upd = new UDP();
        if(multicastAddress != null) {
            upd.setValue("mcast_group_addr", InetAddress.getByName(multicastAddress));
        }
        stack.addProtocol(upd)
                .addProtocol(new PING())
                .addProtocol(new MERGE2())
                .addProtocol(new FD_SOCK())
                .addProtocol(new FD_ALL().setValue("timeout", 12000).setValue("interval", 3000))
                .addProtocol(new VERIFY_SUSPECT())
                .addProtocol(new BARRIER())
                .addProtocol(new NAKACK())
                .addProtocol(new UNICAST2())
                .addProtocol(new STABLE())
                .addProtocol(new GMS())
                .addProtocol(new UFC())
                .addProtocol(new MFC())
                .addProtocol(new FRAG2())
                .addProtocol(new STATE_TRANSFER())
                .addProtocol(new FLUSH());
        stack.init();
        return channel;
    }


    public static Message createActionMessage(String nickname, String channelName, ChatOperationProtos.ChatAction.ActionType actionType) {
        return new Message(null, ChatOperationProtos.ChatAction.newBuilder()
                .setNickname(nickname)
                .setChannel(channelName)
                .setAction(actionType)
                .build());
    }

}
