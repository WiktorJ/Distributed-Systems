package ex2;

/**
 * Created by wiktor on 13/03/16.
 */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static int bufferChunk = 256;
    private static String filePath = "/home/wiktor/Documents/Studies/Distribute-Systems/lab1/";
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("Input parameters: <IP> <port>");
            System.exit(-1);
        }
        Socket socket = null;
        FileOutputStream fileOutputStream = null;
        try {
            socket = new Socket(args[0], Integer.parseInt(args[1]));

            InputStream in = socket.getInputStream();

            byte recvline[] = new byte[bufferChunk];
            ArrayList<Byte> fileInBytes = new ArrayList<>();

            int nameLeght = in.read();
            byte fileName[] = new byte[nameLeght];

            in.read(fileName, 0, nameLeght);

            while (in.read(recvline, 0, bufferChunk) > 0) {
                fileInBytes.addAll(primitiveToObject(recvline));
            }

            fileOutputStream = new FileOutputStream(filePath + new String(fileName));
            fileOutputStream.write(objectToPrimitive(fileInBytes));

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }

    }

    private static List<Byte> primitiveToObject(byte[] arr) {
        List<Byte> ret = new ArrayList<>();
        for (byte b : arr) {
            ret.add(b);
        }
        return ret;
    }

    private static byte[] objectToPrimitive(List<Byte> list) {
        byte[] ret = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}

