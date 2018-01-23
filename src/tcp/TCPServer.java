package tcp;

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
    private static final int BUFSIZE = 32;
    private static final int SERVERPOER = 8040;

    public static void main(String[] args) throws IOException {
        //create server socket
        ServerSocket serverSocket = new ServerSocket(SERVERPOER);

        int recvMsgSize;
        byte[] receiveBuf = new byte[BUFSIZE];

        //accepting and receiving connection
        while (true) {
            Socket clientSocket = serverSocket.accept();

            SocketAddress remoteSocketAddress = clientSocket.getRemoteSocketAddress();
            System.out.println("handing client at: " + remoteSocketAddress);

            //get inputStream
            InputStream in = clientSocket.getInputStream();
            //get outputStream
            OutputStream out = clientSocket.getOutputStream();

            while ((recvMsgSize = in.read(receiveBuf)) != -1) {
                out.write(receiveBuf, 0, recvMsgSize);
            }
            clientSocket.close();
        }
    }
}
