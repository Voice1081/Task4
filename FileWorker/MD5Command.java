import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Command implements IExecutable<String> {
    private MessageDigest MD5;

    public MD5Command() {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String Process(File f) {
        String md5hash;
        if (!f.isDirectory()){
            byte[] file = null;
            try {
                file = Files.readAllBytes(Paths.get(f.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            md5hash = DatatypeConverter.printHexBinary(MD5.digest(file));
        }
        else
            md5hash = GetDirectoryHash(f);
        return md5hash;
    }

    private String GetDirectoryHash(File d)
    {
        File[] files = d.listFiles();
        StringBuilder dirhash = new StringBuilder();
        for(File file : files)
        {
            if (!file.isDirectory()){
                byte[] f = null;
                try {
                    f = Files.readAllBytes(Paths.get(file.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dirhash.append(DatatypeConverter.printHexBinary(MD5.digest(f)));
            }
            else
                dirhash.append(GetDirectoryHash(file));
        }
        return DatatypeConverter.printHexBinary(MD5.digest(dirhash.toString().getBytes()));
    }
}
