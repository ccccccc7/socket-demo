package server;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zyl
 */
public class ReceiveServer {
    private static final int PORT = 44180;
    private static final int BUFFSIZE = 1024;

    public static ReceiveServer create() {
        return new ReceiveServer();
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while (true) {
            Socket client = serverSocket.accept();
            Runnable runnable = () -> {
                try {
                    InputStream inputStream = client.getInputStream();
                    byte[] buffer = new byte[BUFFSIZE];
                    int len;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((len = inputStream.read(buffer)) != -1) {
                        stringBuilder.append(new String(buffer, 0, len, "UTF-8"));
                    }
                    System.out.println("Get ip : " + stringBuilder);
                    handleIp(stringBuilder.toString());
                    inputStream.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(runnable);
        }
    }

    @Nullable
    private String handleIp(String ipData) {
        if (!("IP:").equals(ipData.substring(0, 3)) && !ipData.startsWith(".")) {
            String ip = ipData.substring(3);
//            System.out.println("curl 'http://api.ip138.com/query/?ip=%s&datatype=txt&callback=find' -H 'token:abae1686e46fe8441c5815353886264a' | sed s/[0-9]*.[0-9]*.[0-9]*.[0-9]*.// | sed s/[\\ ]*$// > %s");
            return ip;
        } else {
            throw new IllegalArgumentException("ip error");
        }
    }
}
