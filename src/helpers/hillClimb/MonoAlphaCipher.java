package helpers.hillClimb;

import helpers.Permutations;
import helpers.Text;

public class MonoAlphaCipher implements Cipher {
    private final char offset = 'a';
    Key k;

    public MonoAlphaCipher() {
        k = generateKey();
    }

    public String encrypt(Key k, String pt) {
        MonoAlphaKey key = (MonoAlphaKey) k;
        StringBuilder ct = new StringBuilder();
        pt = Text.toLowerCaseNoWhitespace(pt);

        for (char c : pt.toCharArray()) {
            ct.append(key.key[c - offset]);
        }

        return ct.toString();
    }

    public String decrypt(Key k, String ct) {
        MonoAlphaKey key = (MonoAlphaKey) k;
        StringBuilder pt = new StringBuilder();

        for (char c : ct.toCharArray()) {
            pt.append(key.inverseKey[c - offset]);
        }

        return pt.toString();
    }

    public Key generateKey() {
        Character[] alphabet = new Character[26];

        for (int i = 0; i < 26; i++) {
            alphabet[i] = (char)(i + offset);
        }

        Permutations.rndPerm(alphabet);
        return new MonoAlphaKey(alphabet);
    }

}
