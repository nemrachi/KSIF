import helpers.Text;

public class Main_cv1 {
    public static void main(String[] args) {
        String path = ".\\cv1\\ema.txt";
        Text.saveToFile("Hello darkness my old\nfriend?!", path);
        System.out.println(Text.readFromFile(path));
    }
}