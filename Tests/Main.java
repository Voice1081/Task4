import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InteractiveClient client = new InteractiveClient("localhost", 8080);
        client.run();
    }
}
