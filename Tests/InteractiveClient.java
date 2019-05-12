import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InteractiveClient {
    private final Socket client;
    private final BufferedReader in;
    private final DataOutputStream out;
    private final BufferedReader br;

    public InteractiveClient(String ip, int port) {
        try {
            client = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new DataOutputStream(client.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void run() {
        try {
            while (true) {
                if (br.ready()) {
                    String clientCommand = br.readLine() + "\r\n";
                    out.writeUTF(clientCommand);
                    if (clientCommand.equals("quit")) {
                        break;
                    }
                    out.writeUTF("end\r\n");
                    out.flush();
                    String input = "";
                    while (!(input = in.readLine().trim()).equals("end")) {
                        System.out.println(input);
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        finally {
            try {
                in.close();
                out.close();
                br.close();
                client.close();
            } catch (IOException e1) {e1.printStackTrace();}
        }
    }
}
