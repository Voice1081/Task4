import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        WebServer server = new WebServer(8080);
        server.registerCommand(new ListCommand());
        server.registerCommand(new FileCommand());
        //ArrayList<TestClient> clients = new ArrayList<>();
        ThreadDispatcher dispatcher = ThreadDispatcher.getInstance();
        PrintWorker pw = new PrintWorker(server);
        dispatcher.Add(pw);
        for(int i = 0; i < 10; i++){
            TestClient client = new TestClient("localhost", 8080);
            //clients.add(client);
            dispatcher.Add(client);
        }
        server.run();
    }
}
