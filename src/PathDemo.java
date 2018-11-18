import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) {
        Path path = Paths.get("./data/test.txt");
        System.out.println(path.normalize());
    }
}
