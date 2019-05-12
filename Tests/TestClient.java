import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TestClient extends Threaded  {

    private final Socket client;
    private final BufferedReader in;
    private final DataOutputStream out;
    private boolean quit;
    String result;
    void setQuitTrue() {quit = true;}

    public TestClient(String ip, int port){
        try {
            client = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException();
        }
        quit = false;
    }

    @Override
    public void doRun() {
        try {
            out.writeUTF("list\r\n");
            out.writeUTF("end\r\n");
            out.flush();
            StringBuilder input = new StringBuilder();
            String inputLine = "";
            while (!(inputLine = in.readLine().trim()).equals("end")) {
                input.append(inputLine);
            }
            result = input.toString();
            while (!quit) {
            }
        }
        catch (IOException e){e.printStackTrace();}
        finally {
            try {
                out.writeUTF("quit\r\n");
                out.flush();
                in.close();
                out.close();
                client.close();
            }
            catch (IOException e1) {e1.printStackTrace();}
        }
    }
}