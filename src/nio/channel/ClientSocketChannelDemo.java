package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zyl
 */
public class ClientSocketChannelDemo {
    private static final String REMOTE_IP = "127.0.0.1";

    public static void main(String[] args) throws IOException, InterruptedException {
        String str = "hello";
        int port = 9999;

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(REMOTE_IP, port));

        while (!socketChannel.finishConnect()) {
            System.out.println("client is connecting " + REMOTE_IP);
            Thread.sleep(100);
        }
        System.out.println("client connected at " + System.currentTimeMillis());

        ByteBuffer buff = ByteBuffer.allocate(str.length());
        buff.put(str.getBytes());
        buff.flip();

        socketChannel.write(buff);
        buff.clear();
        socketChannel.close();
    }
}
