import java.util.HashMap;

public class FileCommand implements ICommand {

    private final HashMap<String, String> fileHashes;

    @Override
    public String getName() {
        return "file";
    }

    @Override
    public String getResult(String[] args) {
        if(args.length != 2 || !fileHashes.containsKey(args[1])) return "No such file in directory or incorrect command format";
        else return fileHashes.get(args[1]);
    }

    public FileCommand(){
        String dirPath = System.getProperty("user.dir");
        FileWorker<String> fw = new FileWorker<>(dirPath);
        MD5Command cmd = new MD5Command();
        fileHashes = fw.Execute(cmd);
    }
}
