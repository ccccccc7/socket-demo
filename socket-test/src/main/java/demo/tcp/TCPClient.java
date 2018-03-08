package demo.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * tcp client
 *
 * @author zyl
 */
public class TCPClient {
    private static final int BUFSIZE = 1024;
    private static final int serverPort = 55535;
    private static final String server = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        String message = "hello world";

        //create socket
        Socket socket = new Socket(server, serverPort);

        OutputStream out = socket.getOutputStream();

        out.write(message.getBytes());
        socket.shutdownOutput();

        InputStream in = socket.getInputStream();
        byte[] rcvdBuf = new byte[BUFSIZE];
        int len;
        StringBuilder sb = new StringBuilder();

        while ((len = in.read(rcvdBuf) )!= -1) {
            sb.append(new String(rcvdBuf, 0, len));
        }

        System.out.println("get message from server: " + sb);
        in.close();
        out.close();
        socket.close();
    }
}
