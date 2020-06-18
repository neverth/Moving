package fun.neverth.io;

import java.net.Socket;
import java.util.Date;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/18 13:11
 */
public class BIOTestClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 6666);
                while (true){
                    try {
                        socket.getOutputStream().write((new Date() + "hello").getBytes());
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
