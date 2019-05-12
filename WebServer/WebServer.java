import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class WebServer {

    private final ServerSocket server;
    private final HashMap<String, ICommand> commands;
    final ThreadDispatcher dispatcher;

    public WebServer(int port) {
        dispatcher = ThreadDispatcher.getInstance();
        commands = new HashMap<>();
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public void registerCommand(ICommand command){commands.put(command.getName(), command);}

    private void killConnections(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        ArrayList<Threaded> activeConnections = dispatcher.monitor.getThreads();
        if (activeConnections.size() > 1){
            for (Threaded th: activeConnections){
                if(th instanceof WebWorker){
                    WebWorker connection = (WebWorker)th;
                    connection.setQuitTrue();
                }
            }
        }
    }

    public void run() {
        try {
            while (true) {
                Socket client = server.accept();
                WebWorker wb = new WebWorker(commands, client);
                dispatcher.Add(wb);
            }
        } catch (IOException e) {
            killConnections();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
