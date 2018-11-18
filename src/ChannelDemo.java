import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        /**
         * RandomAccessFile是Java中输入，输出流体系中功能最丰富的文件内容访问类，
         * 它提供很多方法来操作文件，包括读写支持，与普通的IO流相比，
         * 它最大的特别之处就是支持任意访问的方式，程序可以直接跳到任意地方来读写数据。
         */
        RandomAccessFile file = new RandomAccessFile("data/test.txt","rw");
        FileChannel in = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = in.read(buf);
        // if the channel has reached end-of-stream,return -1
        while (bytesRead != -1) {
            System.out.println("read " + bytesRead);
            // Flips this buffer.  The limit is set to the current position and then
            // the position is set to zero.
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.println((char) buf.get());
            }
            buf.clear();
            bytesRead = in.read(buf);
        }
        buf.put("finish\ncxasdcsacascac".getBytes());
        buf.flip();
        in.write(buf);
        file.close();
    }
}
