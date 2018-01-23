package tcp;

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
    private static final int BUFSIZE = 32;
    private static final int serverPort = 8040;
    private static final String server = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        String content = "hello world";
        byte[] data = content.getBytes();
        byte[] rcvdBuf = new byte[BUFSIZE];

        //create socket
        Socket socket = new Socket(server, serverPort);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        out.write(data);
    }
}
