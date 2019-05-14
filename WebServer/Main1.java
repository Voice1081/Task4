public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        WebServer server = new WebServer(8080);
        server.registerCommand(new ListCommand());
        server.registerCommand(new FileCommand());
        server.run();
    }
}
