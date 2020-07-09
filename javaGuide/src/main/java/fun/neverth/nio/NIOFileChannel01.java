package fun.neverth.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/8 16:46
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        ByteBuffer a = ByteBuffer.allocate(1024);
        a.putChar('1');
        a.putInt(1);
        a.put("1".getBytes());
        FileOutputStream fileOutputStream = new FileOutputStream("123.txt");

        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put("李洋123".getBytes());

        byteBuffer.flip();

        channel.write(byteBuffer);

        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("123.txt");

        FileChannel channel1 = fileInputStream.getChannel();

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);

        channel1.read(byteBuffer1);

        byteBuffer1.flip();

        System.out.println(new String(byteBuffer1.array()));

        fileInputStream.close();
    }
}
