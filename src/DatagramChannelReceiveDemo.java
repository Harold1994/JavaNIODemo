import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelReceiveDemo {
    public static void main(String[] args) throws IOException {
        // open a DatagramChannel
        DatagramChannel channel = DatagramChannel.open();
        //创建一个可以从9999UDP端口接收数据包的DatagramChannel
        channel.socket().bind(new InetSocketAddress(9999));
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        //  receive() will copy the content of a received packet of data into the Buffer
        while (true) {
            channel.receive(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char)buffer.get());
            }
            buffer.clear();
        }
    }
}
