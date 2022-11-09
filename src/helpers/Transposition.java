package helpers;

import static helpers.Permutations.applyPerm;
import static helpers.Permutations.getPermFromString;

// ~?.?
public class Transposition {
    Integer[] key;
    Integer[] inverseKey;

    private Transposition() {

    }

    public Transposition(Integer[] key) {
        this.key = key;
        this.inverseKey = (Integer[])Permutations.inversePerm(key);
    }

    public Transposition(String keyPhrase) {
        this(getPermFromString(keyPhrase));
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
            String reordered = applyPerm(subStr, key);
            retVal.append(reordered);
        }

        return retVal.toString();
    }
}