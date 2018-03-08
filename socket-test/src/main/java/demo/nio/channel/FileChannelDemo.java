package demo.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zyl
 */
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        channelRead();
    }

    private static void channelRead() throws IOException {
        RandomAccessFile file = new RandomAccessFile("data/nio-data1.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buff = ByteBuffer.allocate(48);
        //写入数据到buff
        int read = channel.read(buff);
        while (read != -1) {
            System.out.println("read : " + buff);
            //必须要有，反转缓冲区，，从写模式改为读模式
            buff.flip();

            while (buff.hasRemaining()) {
                System.out.println((char) buff.get());
            }
            //清空缓冲区
            buff.clear();
            read = channel.read(buff);
        }

        file.close();
    }
}
