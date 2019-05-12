import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        FileWorker<String> fw = new FileWorker<>("C:\\Users\\Константин\\Desktop\\учеба\\ООП\\Task2\\test");
        MD5Command cmd = new MD5Command();
        HashMap<String, String> res = fw.Execute(cmd);
        System.out.println(res);
    }
}
