import java.util.*;

public class Main_cv3 {
    public static void main(String[] args) {
        transposition("hello world", 3);
    }

    // ~2.12
    private static Integer[] bellaso(String phrase) {
        phrase = phrase.toLowerCase();
        StringBuilder sorted = new StringBuilder(sortAlphabetically(phrase));
        Integer[] perm = new Integer[phrase.length()];
        int iofChar, i = 0;

        for (char ch : phrase.toCharArray()) {
            iofChar = sorted.indexOf(String.valueOf(ch));
            sorted.setCharAt(iofChar, (char) 0);
            perm[i++] = iofChar;
        }

        return perm;
    }

    private static String sortAlphabetically(String str) {
        char chArr[] = str.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }

    // ~2.13
    private static void transposition(String phrase, int blockSize) {
        phrase = phrase.toLowerCase().replaceAll("\\s+","");
        int phraseLen = phrase.length();
        int phraseSplitLen = (phraseLen + blockSize - 1) / blockSize;
        char ch;
        char[][] phraseSplit = new char[phraseSplitLen][blockSize];

        for(int i = 0, j = 0, n = phraseLen*blockSize; i < n;  i++, j++) {
            if (i >= phraseLen) {
                ch = 'x';
            } else {
                ch = phrase.charAt(i);
            }
            phraseSplit[i%phraseSplitLen][i%blockSize] = ch;
        }

        int k = 0;
        for(int i = 0; i < phraseSplitLen; i++) {
            for(int j = 0; j < blockSize; j++) {
                if (k >= phraseLen) {
                    ch = 'x';
                } else {
                    ch = phrase.charAt(k);
                }
                phraseSplit[i][j] = ch;
                k++;
            }
        }

        System.out.println(Arrays.deepToString(phraseSplit));
    }
}