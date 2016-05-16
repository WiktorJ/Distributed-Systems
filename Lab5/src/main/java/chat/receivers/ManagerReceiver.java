package chat.receivers;

import chat.state.LocalState;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Collectors;

/**
 * Created by wiktor on 15/05/16.
 */
public class ManagerReceiver extends ReceiverAdapter {

    private LocalState localState;

    public ManagerReceiver(LocalState localState) {
        this.localState = localState;
    }

    @Override
    public void receive(Message msg) {
        System.out.println("Received Message: " + msg.toString());
        ChatOperationProtos.ChatAction action = (ChatOperationProtos.ChatAction) msg.getObject();
        if (action.getAction() == ChatOperationProtos.ChatAction.ActionType.JOIN) {
            localState.addNewUserToChannel(action.getNickname(), action.getChannel());
        } else if (action.getAction() == ChatOperationProtos.ChatAction.ActionType.LEAVE) {
            localState.deleteUserFromChannel(action.getNickname(), action.getChannel());
        }
    }

    @Override
    public void viewAccepted(View view) {
        System.out.println("Change in chat structure: " + view);
        localState.updateActiveUsers(view.getMembers().stream().map(Object::toString).collect(Collectors.toList()));
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        System.out.println("GET state in user: " + localState.getNickname());
        Util.objectToStream(localState.getStateToSend(), new DataOutputStream(output));

    }

    @Override
    public void setState(InputStream input) throws Exception {
        System.out.println("SET state in user: " + localState.getNickname());
        localState.setNewState((ChatOperationProtos.ChatState) Util.objectFromStream(new DataInputStream(input)));
    }
}
