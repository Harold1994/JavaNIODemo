import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterRead {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("data/test.txt","rw");
        FileChannel inChannel = file.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body   = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = {header, body};
        inChannel.read(bufferArray);
        header.flip();
        while (header.hasRemaining()) {
            System.out.print((char) header.get());
        }
        System.out.println( header.array().length + "");
        body.flip();
        while (body.hasRemaining()) {
            System.out.print((char) body.get());
        }
        System.out.println(body.array().length + "");
        file.close();
    }
}
