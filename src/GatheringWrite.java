import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GatheringWrite {
    public static void main(String[] args) throws IOException {
//
//        RandomAccessFile inFile = new RandomAccessFile("data/test.txt","rw");
//        FileChannel inChannel = inFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer [] bufferArray = {header, body};

//        inChannel.read(bufferArray);
//        header.flip();
//        body.flip();
        header.put("Only the data between position and limit of the buffers is written. ".toString().getBytes());
        body.put("The array of buffers are passed into the write() method, which writes the content of the buffers in the sequence they are encountered in the array. Only the data between position and limit of the buffers is written. Thus, if a buffer has a capacity of 128 bytes, but only contains 58 bytes, only 58 bytes are written from that buffer to the channel. Thus, a gathering write works fine with dynamically sized message parts, in contrast to scattering reads.".toString().getBytes());
        header.flip();
        body.flip();
        RandomAccessFile outFile = new RandomAccessFile("data/out.txt","rw");
        FileChannel outChannel = outFile.getChannel();
        outChannel.write(bufferArray);
//        inFile.close();
        outFile.close();
    }
}
