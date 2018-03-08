package demo.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zyl
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        server1();
    }

    private static void server1() throws IOException, InterruptedException {
        int port = 9999;
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //not block
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("start wait client at : " + System.currentTimeMillis());

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                Thread.sleep(1000);
            } else {
                System.out.println("client " + socketChannel.socket().getRemoteSocketAddress() + " come at : " + System.currentTimeMillis());
                ByteBuffer buff = ByteBuffer.allocate(1024);
                socketChannel.read(buff);
                buff.flip();
                while (buff.hasRemaining()) {
                    System.out.print((char) buff.get());
                }

                socketChannel.close();
            }
        }
    }
}
