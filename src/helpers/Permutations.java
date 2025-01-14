package helpers;

import java.util.Arrays;
import java.util.Random;

// ~1.13
public class Permutations {
    public Permutations() {}

    public static Object[] inversePerm(Object perm[]) {
        int size = perm.length;
        Object[] inverse = new Object[size];

        for (int i = 0; i < size; i++) {
            inverse[(int)perm[i]] = i;
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

    // ~1.12
    public static void rndPerm(Object collection[]) {
        Random rnd = new Random(Double.doubleToLongBits(java.lang.Math.random()));
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

    private static String sortAlphabetically(String str) {
        char chArr[] = str.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }
}
