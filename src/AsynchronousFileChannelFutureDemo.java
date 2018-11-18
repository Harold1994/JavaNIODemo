import jdk.management.resource.internal.CompletionHandlerWrapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousFileChannelFutureDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/harold/Documents/Code/JavaNIODemo/data/out.txt");
        // 通过open()方法打开一个AsynchronousFileChannel
        // The second parameter is one or more open options which tell the
        // AsynchronousFileChannel what operations is to be performed on the underlying file.
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(2000);
        Future<Integer> operation = fileChannel.read(buf,0);
        // 通过调用read()方法返回的Future实例的isDone()方法，可以检查读取操作是否完成。
        while (!operation.isDone());
        buf.flip();
        byte[] data = new byte[buf.limit()];
        buf.get(data);
        System.out.println(new String(data));
        buf.clear();
    }
}
