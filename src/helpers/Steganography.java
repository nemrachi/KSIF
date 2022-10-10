package helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static helpers.Math.getRndIndex;

// ~2.2
public class Steganography {
    public Steganography() {}

    public static String cipher(String x) {
        HashMap<Character, List<String>> dict = getDictionary();
        StringBuilder sb = new StringBuilder();
        x = x.toLowerCase().replaceAll("\\s+","");

        for (char ch : x.toCharArray()) {
            List<String> wordsList = dict.get(ch);
            if (wordsList == null) { continue; }

            sb.append(wordsList.get(getRndIndex(wordsList.size())));
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static String decipher(String y) {
        StringBuilder sb = new StringBuilder();
        String[] words = y.split(" ");

        for (String word : words) {
            sb.append(word.charAt(0));
        }

        return sb.toString();
    }

    private static HashMap<Character, List<String>> getDictionary() {
        HashMap<Character, List<String>> dict = new HashMap<>();
        String[] dictWords = Text.readText(new File(".\\cv2\\dictionary.txt")).split("\n");

        for (String word : dictWords) {
            char firstCh = word.charAt(0);
            if (dict.get(firstCh) != null) {
                dict.get(firstCh).add(word);
            } else {
                dict.put(word.charAt(0), new ArrayList<>(Arrays.asList(word)));
            }
        }

        return dict;
    }
}
