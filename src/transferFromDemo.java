import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class transferFromDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile inFile = new RandomAccessFile("data/test.txt","rw");
        FileChannel inChannel = inFile.getChannel();

        RandomAccessFile outFile = new RandomAccessFile("data/out.txt","rw");
        FileChannel outChannel = outFile.getChannel();
        long position = 0;
        long count = inChannel.size();
        outChannel.transferFrom(inChannel, position, count);
        inFile.close();
        outFile.close();
    }
}
