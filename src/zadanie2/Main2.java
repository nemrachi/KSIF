package zadanie2;

import helpers.Solver;
import helpers.VigenereCipher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main2 {
    private static final int alphaNum = 26;
    private static final char offset = 'a';
    private static Set<String> keySet = new HashSet<String>() {{
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alphaNum; i++) {
            for (int j = 0; j < alphaNum; j++) {
                sb.append(Character.toChars(i+offset)).append(Character.toChars(j+offset));
                add(sb.toString());
                sb.setLength(0);
            }
        }
    }};

    public static void main(String[] args) throws IOException {
        String pt;
        Path path = Paths.get("./src/zadanie2/19.txt");
        String ct = Files.readAllLines(path).get(0);

        for (String key : keySet) {
            VigenereCipher vc = new VigenereCipher(key);
            pt = vc.decrypt(ct);
            if (pt.contains("q") || pt.contains("w") || pt.contains("x")) {
                continue;
            }
            System.out.println(key + " : " + pt);
        }
        // ky : vdruhomposchodiniektootvoriloknoadivalsa
    }
}
