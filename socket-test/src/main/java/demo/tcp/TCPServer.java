package demo.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * tcp server
 *
 * @author zyl
 */
public class TCPServer {
    private static final int BUFSIZE = 2014;
    private static final int SERVERPOER = 55535;

    public static void main(String[] args) throws IOException {
        //create server socket
        ServerSocket serverSocket = new ServerSocket(SERVERPOER);

        System.out.println("server ready");

        int recvMsgSize;
        byte[] receiveBuf = new byte[BUFSIZE];
        String response = "receive success";

        //accepting and receiving connection
        while (true) {
            Socket clientSocket = serverSocket.accept();

            SocketAddress remoteSocketAddress = clientSocket.getRemoteSocketAddress();
            System.out.println("handing client at: " + remoteSocketAddress);

            //get inputStream
            InputStream in = clientSocket.getInputStream();
            //get outputStream
            OutputStream out = clientSocket.getOutputStream();
            StringBuilder sb = new StringBuilder();
            while ((recvMsgSize = in.read(receiveBuf)) != -1) {
                sb.append(new String(receiveBuf, 0, recvMsgSize, "UTF-8"));
                out.write(response.getBytes());
            }
            System.out.println("get message from client: " + response);

            in.close();
            out.close();
            clientSocket.close();
        }
    }
}
