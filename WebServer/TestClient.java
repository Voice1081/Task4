import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TestClient extends Threaded  {

    private Socket client;
    private BufferedReader in;
    private DataOutputStream out;
    private final String ip;
    private final int port;
    String result;

    public TestClient(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void doRun() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        try {
            try {
                client = new Socket(ip, port);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException();
            }
            out.writeUTF("list\r\n");
            out.writeUTF("end\r\n");
            out.flush();
            StringBuilder input = new StringBuilder();
            String inputLine = "";
            while (!(inputLine = in.readLine().trim()).equals("end")) {
                input.append(inputLine);
            }
            result = input.toString();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.interrupted();
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