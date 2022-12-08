package helpers;

import java.util.*;

public class Solver { // cv 11
    private static final int alphaNum = 26;

    public static String caesarSolver(String input) {
        input = input.toLowerCase().replaceAll("[^a-z]", "");
        L1MonogramFitness lFitness = new L1MonogramFitness();
        TreeMap<Double, String> res = new TreeMap<>();

        for (int i = 0; i < alphaNum; i++) {
            CaesarCipher cc = new CaesarCipher(i);
            String decr = cc.decrypt(input);
            double score = lFitness.evaluate(decr);
            res.put(score, "shift: +" + i + ", text: " + decr);
        }

        for (Map.Entry<Double, String> entry : res.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        return res.firstEntry().getValue(); // smallest key/score -> best
    }

    public static int guessVigenereKeyLen(String cipherText) {
        // friedman test
        boolean okFlag;
        StringBuilder sb = new StringBuilder();

        for (int n = 2; n <= 10; n++) {
            okFlag = true;

            List<String> parts = new ArrayList<>();
            for (int i = 0; i < cipherText.length(); i++) {
                sb.append(cipherText.charAt(i));
                if ((i+1) % n == 0 || i == cipherText.length()-1) {
                    parts.add(sb.toString());
                    sb.setLength(0);
                }
            }

            for (String part : parts) {
                Language.LangEnum guessed = Language.guessLanguage(part);
                if (guessed == Language.LangEnum.Unknown) {
                    okFlag = false;
                    break;
                }
            }

            if (okFlag) {
                System.out.println(parts);
                return n;
            }
        }
        return  0;
    }

    public static void vigenereSolver(String input, Set<String> kSet) {
        input = input.toLowerCase().replaceAll("[^a-z]", "");
        L1MonogramFitness lFitness = new L1MonogramFitness();
        double scoreBi = Double.MAX_VALUE;
        double scoreDict = 0;
        String resBi = "";
        String resDict = "";
        String dictPath = "dictionary_5000.txt";
        List<String> words = DictionaryNode.readDictionaryWords(dictPath);
        DictionaryNode root = DictionaryNode.loadDictionary(words);

        for (String kStr: kSet) {
            VigenereCipher vc = new VigenereCipher(kStr);
            String tmp = vc.decrypt(input);
            double score1 = lFitness.evaluate(tmp);
            if (score1 < scoreBi) {
                scoreBi = score1;
                resBi = score1 + "key: " + kStr + ", text: " + tmp;
            }
            double score2 = root.evaluate(tmp, 3, 8);
            if (score2 > scoreDict) {
                scoreDict = score2;
                resDict = score2 + "key: " + kStr + ", text: " + tmp;
            }
        }

        System.out.println("2-gram: " +resBi);
        System.out.println("Dictionary: " +resDict);
    }

    public static int guessKeyLen(String cipherText) {
        // friedman test
        boolean okFlag;
        StringBuilder sb = new StringBuilder();

        for (int n = 2; n <= 10; n++) {
            okFlag = true;

            List<String> parts = new ArrayList<>();
            for (int i = 0; i < cipherText.length(); i++) {
                sb.append(cipherText.charAt(i));
                if ((i+1) % n == 0 || i == cipherText.length()-1) {
                    parts.add(sb.toString());
                    sb.setLength(0);
                }
            }

            for (String part : parts) {
                Language.LangEnum guessed = Language.guessLanguage(part);
                if (guessed == Language.LangEnum.Unknown) {
                    okFlag = false;
                    break;
                }
            }

            if (okFlag) {
                return n;
            }
        }
        return  0;
    }
}
