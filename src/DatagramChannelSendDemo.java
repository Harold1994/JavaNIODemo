import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelSendDemo {
    public static void main(String[] args) throws IOException {
        // open a DatagramChannel
        DatagramChannel channel = DatagramChannel.open();
//        channel.socket().bind(new InetSocketAddress(9999));
        String newData = "New String to write to file..."
                + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        int bytesSent = channel.send(buf, new InetSocketAddress("127.0.0.1", 9999));
        channel.close();
    }
}
