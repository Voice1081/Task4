public class PrintWorker extends Threaded {

    private final FileHashWebServer server;
    public PrintWorker(FileHashWebServer server){
        this.server = server;
    }
    @Override
    public void doRun() {
        while (true){
            System.out.println(server.dispatcher.monitor.getThreads());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
    }
}
