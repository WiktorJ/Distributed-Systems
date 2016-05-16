package client;

import chat.Chat;

import java.util.Scanner;

/**
 * Created by wiktor on 15/05/16.
 */
public class Client {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write nickname: ");
        String nickname = scanner.nextLine();
        Chat chat = new Chat(nickname);
        String command;
        while ( !(command = scanner.nextLine()).equals("exit") ) {
            String[] split = command.split(" ");
            switch (split[0]) {
                case "join":
                    chat.joinChannel(split[1]);
                    break;
                case "leave":
                    chat.leaveChannel(split[1]);
                    break;
                case "send":
                    chat.sendMessage(split[1], split[2]);
                    break;
                case "list":
                    chat.getChannelsList().stream().forEach(System.out::println);
            }
        }
        chat.leaveChat();
    }

}
