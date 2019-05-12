import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        FileHashWebServer server = new FileHashWebServer("C:\\Users\\Константин\\Desktop\\учеба\\ООП\\Task2\\test", 8080);
        ArrayList<TestClient> clients = new ArrayList<>();
        ThreadDispatcher dispatcher = ThreadDispatcher.getInstance();
        PrintWorker pw = new PrintWorker(server);
        dispatcher.Add(pw);
        for(int i = 0; i < 10; i++){
            TestClient client = new TestClient("localhost", 8080);
            clients.add(client);
            dispatcher.Add(client);
        }
        server.run();
    }
}
