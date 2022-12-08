package zadanie1;

import helpers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main1 {
    private static final int alphaNum = 26;
    private static final char offset = 'a';
    private static List<String> alphabet = new ArrayList<>();

    private static void printAlphabet() {
        System.out.print("---> ");
        for (int i = 0; i < alphaNum; i++) {
            System.out.print(i+1 + ":" + alphabet.get(i) + ", ");
        }
        System.out.print("\n");
    }
    private static void generateAlphabet() {
        alphabet = new ArrayList<>();
        for (int i = 0; i < alphaNum; i++) {
            if ((char)(i+offset) == 'q' || (char)(i+offset) == 'w' || (char)(i+offset) == 'x') {
                continue;
            }
            alphabet.add(String.valueOf((char)(i+offset)));
        }
        Collections.shuffle(alphabet);

        alphabet.add(3, "q");
        alphabet.add(6, "w");
        alphabet.add(22, "x");
    }

    private static String solver(String[] ct) {
        StringBuilder pt = new StringBuilder();
        for (String letter : ct) {
            if (letter.equals("-")) {
                pt.append(' ');
            } else {
                pt.append(alphabet.get((Integer.parseInt(letter)-1)));
            }
        }
        return pt.toString();
    }

    public static void main(String[] args) throws IOException {
        int count = 0;
        String pt;
//        Path path = Paths.get("./src/zadanie1/19.txt");
//        String[] ct = Files.readAllLines(path).get(0).split("\\.");
        String[] ct = "18.25.22.2.20.2.22.25".split("\\.");

        while (count < 100) {
            generateAlphabet();
            pt = solver(ct);
//            if (Arrays.asList("na", "do", "od", "on", "my", "mi", "ty", "ku", "za", "sa", "si", "vy", "no", "to", "aj", "ja", "im", "ma", "da", "vo", "so", "zo").contains(pt.substring(0, 2))) {
//
//            }
            printAlphabet();
            System.out.println(pt);
            count++;
        }
    }
}
