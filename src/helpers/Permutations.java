package helpers;

import java.util.Arrays;

// ~1.13
public class Permutations {
    public Permutations() {}

    public static Integer[] inversePerm(Integer perm[]) {
        int size = perm.length;
        Integer[] inverse = new Integer[size];

        for (int i = 0; i < size; i++) {
            inverse[perm[i]] = i;
        }

        return inverse;
    }

    public static Integer[] getPermFromString(String phrase) {
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

    public static String applyPerm(String input, Integer[] perm) {
        char output[] = new char[perm.length];
        for (int i = 0; i < perm.length; i++) {
            output[perm[i]] = input.charAt(i);
        }
        return new String(output);
    }

    private static String sortAlphabetically(String str) {
        char chArr[] = str.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }
}
