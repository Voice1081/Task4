import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        ArrayList<TestClient> clients = new ArrayList<>();
        ThreadDispatcher dispatcher = ThreadDispatcher.getInstance();
        for(int i = 0; i < 10; i++){
            TestClient client = new TestClient("localhost", 8080);
            clients.add(client);
            dispatcher.Add(client);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        for(TestClient client: clients){
            client.setQuitTrue();
        }
    }
}
