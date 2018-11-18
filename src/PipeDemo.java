import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;



public class PipeDemo {
    static Pipe pipe;

    static {
        try {
            pipe = Pipe.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipeDemo() throws IOException {
    }

    static class RunnableA implements Runnable {
        String newData = "New String to write to ThraedB...";
        public RunnableA() throws IOException {
        }

        @Override
        public void run() {
            Pipe.SinkChannel sc = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocate(48);
            buffer.put(newData.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                try {
                    sc.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class RunnableB implements Runnable{
        @Override
        public void run() {
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer buf = ByteBuffer.allocate(48);
            try {
                int bytesRead = sourceChannel.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char)buf.get());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        RunnableA a = new RunnableA();
        RunnableB b = new RunnableB();
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        t1.start();
        t2.start();
    }
}
