import java.util.*;

import static helpers.Text.getRandChar;
import helpers.Steganography;

public class Main_cv2 {
    public static void main(String[] args) {
        perm_1_11();
    }

    // ~2.1
    private static String writeEven(String x) {
        StringBuilder sb = new StringBuilder();
        x = x.toLowerCase().replaceAll("\\s+","");

        for (int i = 0, n = x.length(); i < n; i++) {
            sb.append(getRandChar()).append(x.charAt(i)).append(getRandChar());
            if (i % 2 == 1) {
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }

    private static String readEven(String y) {
        StringBuilder sb = new StringBuilder();
        y = y.toLowerCase().replaceAll("\\s+","");

        for (int i = 1, n = y.length(); i < n; i += 3) {
            sb.append(y.charAt(i));
        }

        return sb.toString().trim();
    }

    private static String writeOdd(String x) {
        StringBuilder sb = new StringBuilder();
        String[] words = x.toLowerCase().split(" ");

        for (String word : words) {
            for (int i = word.length()-1; i >= 0; i--) {
                sb.append(getRandChar()).append(word.charAt(i)).append(getRandChar());
                if (i % 2 == 0) {
                    sb.append(" ");
                }
            }
            sb.append(" ").append(getRandChar()).append(" ");
        }

        return sb.toString().trim();
    }

    private static String readOdd(String y) {
        StringBuilder sb = new StringBuilder();
        StringBuilder wordSb = new StringBuilder();
        String[] words = y.toLowerCase().split(" ");

        for (String word : words) {
            if (word.length() == 1) {
                sb.append(wordSb.reverse()).append(" ");
                wordSb.setLength(0);
            }

            for (int i = 1, n = word.length(); i < n; i += 3) {
                wordSb.append(word.charAt(i));
            }
        }

        return sb.toString().trim();
    }

    // ~1.11
    private static void perm_1_11() {
        Random rnd = new Random(Double.doubleToLongBits(Math.random()));
        Set<Integer> s = new HashSet<>();

        while (s.size() < 10) {
            s.add(rnd.nextInt(26) + 1);
        }

        List<Integer> l = new ArrayList<>(s);
        Object[] arr = l.toArray();
        System.out.println(Arrays.toString(arr));

        //Collections.shuffle(l, rnd);
        rndPerm(arr);

        System.out.println(Arrays.toString(arr));
    }

    // ~1.12
    private static void rndPerm(Object collection[]) {
        Random rnd = new Random(Double.doubleToLongBits(Math.random()));
        int size = collection.length;
        int j;
        Object tmp;

        for (int i = 0; i < size-1; i++) {
            j = rnd.nextInt(size - i) + i;
            tmp = collection[i];
            collection[i] = collection[j];
            collection[j] = tmp;
        }
    }

    // ~1.15
    private static void allStrings(int lvl, int maxLvl, Character[] pwd, Set<Character[]> res) {
        if (lvl == maxLvl) {
            res.add(pwd.clone());
        } else {
            for (int i = 0; i < 26; i++) {
                pwd[lvl] = (char)(i + 'a');
                allStrings(lvl+1, maxLvl, pwd, res);
            }
        }
    }
}