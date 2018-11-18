import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8088));
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(newData.getBytes());
        buffer.flip();
//        while (! socketChannel.finishConnect()) {
//            Thread.sleep(3000);
//        }
//        while (true) {
//            int byteRead = socketChannel.read(buffer);
//            buffer.flip();
//            if (byteRead != -1) {
//                while (buffer.hasRemaining()) {
//                    System.out.print((char)buffer.get());
//                }
//            }
//            buffer.clear();
//        }


        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
        socketChannel.close();
    }
}
