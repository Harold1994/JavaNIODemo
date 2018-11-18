import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AsynchronousFileChannelCompletionHandlerDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("data/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1300);
        long position = 0;
        channel.read(buffer, position, buffer,
                new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        System.out.println("read done");
                        System.out.println(result + "");
                        attachment.flip();
                        byte [] data = new byte[attachment.limit()];
                        attachment.get(data);
                        System.out.println(new String(data));
                        attachment.clear();
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        System.out.println("read fail");
                    }
                });
        // 给终端显示留一些时间，不然会来不及显示读取的信息进程就结束了
        Thread.sleep(3000);
    }
}
