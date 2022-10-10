package helpers;

import java.util.Arrays;

public class Transposition {
    Integer[] key;
    Integer[] inverseKey;

    private Transposition() {

    }

    public Transposition(Integer[] key) {
        this.key = key;
        this.inverseKey = inversePerm(key);
    }

    public Transposition(String keyPhrase) {
        this(bellaso(keyPhrase));
    }

    public String encrypt(String input) {
        int inputLen = input.length();
        int keyLen = key.length;
        if (inputLen % keyLen != 0) {
            int missing = keyLen - (inputLen % keyLen);
            for (int i = 0; i < missing; i++) {
                input += 'x';
            }
        }
        return crypt(input, inverseKey);
    }

    public String decrypt(String input) {
        return crypt(input, inverseKey);
    }

    public String crypt(String input , Integer[] key) {
        int blockSize = key.length;
        int blocks =  input.length() / blockSize;
        StringBuilder retVal = new StringBuilder();

        for (int i = 0; i < blocks; i++) {
            int idx =  i * blockSize;
            String subStr = input.substring(idx, idx + blockSize);
            String reordered = applyPermutation(subStr, key);
            retVal.append(reordered);
        }

        return retVal.toString();
    }

    public static String applyPermutation(String input, Integer[] perm) {
        char output[] = new char[perm.length];
        for (int i = 0; i < perm.length; i++) {
            output[perm[i]] = input.charAt(i);
        }
        return new String(output);
    }

    private static Integer[] inversePerm(Integer perm[]) {
        int size = perm.length;
        Integer[] inverse = new Integer[size];

        for (int i = 0; i < size; i++) {
            inverse[perm[i]] = i;
        }

        return inverse;
    }

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
}