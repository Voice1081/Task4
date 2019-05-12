import java.io.*;
import java.net.Socket;

public class WebWorker extends Threaded {

    private final WebServer server;
    private final Socket client;
    private BufferedReader in;
    private DataOutputStream out;
    private boolean quit;
    void setQuitTrue() {quit = true;}

    public WebWorker(WebServer server, Socket client) {
        this.server = server;
        this.client = client;
        quit = false;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            quit = true;
            out = null;
            in = null;
        }
    }

    @Override
    public void doRun() {
        StringBuilder input = new StringBuilder();
        String inputLine = "";
        try {
            while (true) {
                    while (!quit && !(inputLine = in.readLine().trim()).equals("end")) {
                        if (inputLine.equals("quit")) {
                            quit = true;
                            break;
                        }
                        input.append(inputLine);
                    }
                    if (quit) break;
                    String result = server.processCommand(input.toString().trim());
                    input.setLength(0);
                    out.writeUTF(result);
                    out.writeUTF("end\r\n");
                    out.flush();
            }
        }
        catch (IOException e) { e.printStackTrace();}
        finally {
            try {
                if(out != null && in != null) {
                    out.flush();
                    out.close();
                    in.close();
                }
                client.close();
            } catch (IOException e){ e.printStackTrace(); }
        }
    }
}
