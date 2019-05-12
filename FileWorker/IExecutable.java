import java.io.File;

public interface IExecutable<T> {
    T Process(File f);
}
