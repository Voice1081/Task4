import java.io.File;
import java.util.HashMap;

public class FileHashWebServer extends WebServer {

    private final HashMap<String, String> fileHashes;
    private final String filesList;

    public FileHashWebServer(String dirPath, int port){
        super(port);
        FileWorker<String> fw = new FileWorker<>(dirPath);
        MD5Command cmd = new MD5Command();
        fileHashes = fw.Execute(cmd);
        String[] filesList = new File(dirPath).list();
        StringBuilder files = new StringBuilder();
        for(String f : filesList){
            files.append(f);
            files.append("\r\n");
        }
        this.filesList = files.toString();
    }

    protected String processCommand(String command) {
        if(command.equals("list")) return filesList;
        else if(command.startsWith("file")){
            String[] splited = command.split(" ");
            if(splited.length != 2 || !fileHashes.containsKey(splited[1])) return "No such file in directory or incorrect command format";
            else return fileHashes.get(splited[1]) + "\r\n";}
        else return "Invalid command";
    }
}
