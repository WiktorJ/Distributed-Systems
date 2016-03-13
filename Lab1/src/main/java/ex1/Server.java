package ex1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wiktor on 13/03/16.
 */
public class Server {

    private static int backlog = 2;
    private static byte[] piDigits = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4,3,3,8,3,2,7,9,5,0,2,8,8,4,1,9,7,1,6,9,3,9,9,3,7,5,1,0,5,8,2,0,9,7,4,9,4,
            4,5,9,2,3,0,7,8,1,6,4,0,6,2,8,6,2,0,8,9,9,8,6,2,8,0,3,4,8,2,5,3,4,2,1,1,7,0,6,7,9,8,2,1,4,8,0,8,6,5,1,3,2,8,2,3,0,6,6,4,7,0,9,3,8,4,4,6,0,9,5,5,0,
            5,8,2,2,3,1,7,2,5,3,5,9,4,0,8,1,2,8,4,8,1,1,1,7,4,5,0,2,8,4,1,0,2,7,0,1,9,3,8,5,2,1,1,0,5,5,5,9,6,4,4,6,2,2,9,4,8,9,5,4,9,3,0,3,8,1,9,6,4,4,2,8,8,
            1,0,9,7,5,6,6,5,9,3,3,4,4,6,1,2,8,4,7,5,6,4,8,2,3,3};


    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Input parameters: <IP> <port>");
            System.exit(-1);
        }
        ServerSocket ssocket = new ServerSocket(Integer.parseInt(args[1]), backlog, InetAddress.getByName(args[0]));
        byte[] recBuffer = new byte[8];
        byte[] sendBuffer = new byte[1];

        while (true) {
            try {
                Socket accept = ssocket.accept();
                InputStream inputStream = accept.getInputStream();
                OutputStream outputStream = accept.getOutputStream();
                int read = inputStream.read(recBuffer);
                long nthDigit = recBuffer[0];
                for (int i = 1; i < read; i++) {
                    nthDigit = (nthDigit << 8) | (recBuffer[i] & 0xff);
                }
                sendBuffer[0] = getPiDigit(nthDigit);
                outputStream.write(sendBuffer);
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte getPiDigit(long n) {
        Long l = n % piDigits.length;
        return piDigits[l.intValue()];
    }

}
