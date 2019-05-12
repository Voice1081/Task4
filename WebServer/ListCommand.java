import java.io.File;

public class ListCommand implements ICommand {

    private final String files;

    @Override
    public String getName() {
        return "List";
    }

    @Override
    public String getResult(String[] args) {
        return files;
    }

    public ListCommand(){
        String dirPath = System.getProperty("user.dir");
        String[] filesList = new File(dirPath).list();
        StringBuilder files = new StringBuilder();
        for(String f : filesList){
            files.append(f);
            files.append("\r\n");
        }
        this.files = files.toString();
    }
}
