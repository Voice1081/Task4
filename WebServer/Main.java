public class Main {
    public static void main(String[] args) throws InterruptedException {
        FileHashWebServer server = new FileHashWebServer("C:\\Users\\Константин\\Desktop\\учеба\\ООП\\Task2\\test", 8080);
        server.run();
    }
}
