import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.*;
import java.util.concurrent.Future;

public class WriteWithFuture {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data/test-write.txt");
        if (!Files.exists(path))
            Files.createFile(path);
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        buffer.put("test data".getBytes());
        buffer.flip();

        Future<Integer> operation = fileChannel.write(buffer, position);
        buffer.clear();

        while(!operation.isDone());

        System.out.println("Write done");
    }
}
