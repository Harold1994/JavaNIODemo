import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8088));
//        while (true) {
//            SocketChannel channel = ssc.accept();
//            if (channel.finishConnect()) {
//                ByteBuffer buffer = ByteBuffer.allocate(48);
//                channel.read(buffer);
//                buffer.flip();
//                while (buffer.hasRemaining()) {
//                    System.out.println((char) buffer.get());
//                }
//            }
//        }
        ssc.configureBlocking(false);
        while (true) {
            SocketChannel channel = ssc.accept();
            if (channel != null) {
                //...
            }
        }
    }
}
