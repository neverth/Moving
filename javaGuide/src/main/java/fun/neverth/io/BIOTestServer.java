package fun.neverth.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/18 13:17
 */
public class BIOTestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);

        new Thread(()->{
            while (true){
                try {
                    Socket socket = serverSocket.accept();

                    new Thread(()->{
                        try{
                            int len;

                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();

                            while((len = inputStream.read(data)) != -1){
                                System.out.println(new String(data, 0, len));

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
