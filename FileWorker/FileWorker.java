import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.File;
import java.util.HashMap;

public class FileWorker<T> {
    public final File directory;
    public boolean IsRecursive;

    public boolean isRecursive() {
        return IsRecursive;
    }

    public void setRecursive(boolean recursive) {
        IsRecursive = recursive;
    }

    public FileWorker(String path, boolean isRecursive) {
        this.directory = new File(path);;
        IsRecursive = isRecursive;
    }

    public FileWorker(String path) {
        this(path, false);
    }
    public HashMap<String, T> Execute(IExecutable<T> command) {
        return ExecuteCommand(command, directory);
    }

    private HashMap<String, T> ExecuteCommand(IExecutable<T> command, File directory) {
        HashMap<String, T> result = new HashMap<String, T>();
        File[] filesAndDirectories = directory.listFiles();
        for(File f : filesAndDirectories)
        {
            result.put(f.getName(), command.Process(f));
            if (IsRecursive && f.isDirectory())
            {
                HashMap<String, T> res = ExecuteCommand(command, f);
                result.putAll(res);
            }
        }
        return result;
    }
}
