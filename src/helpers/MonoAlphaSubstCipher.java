package helpers;

public class MonoAlphaSubstCipher {
    private final char offset = 'a';
    Character[] key, inverseKey;

    public MonoAlphaSubstCipher() {
        randomKey();
    }

    public MonoAlphaSubstCipher(Character[] key) {
        this.key = key;
        this.inverseKey = (Character[])Permutations.inversePerm(key);
    }

    public String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        text = Text.toLowerCaseNoWhitespace(text);

        for (char c : text.toCharArray()) {
            sb.append(key[c - offset]);
        }

        return sb.toString();
    }

    public String decrypt(String enc) {
        StringBuilder sb = new StringBuilder();

        for (char c : enc.toCharArray()) {
            sb.append(inverseKey[c - offset]);
        }

        return sb.toString();
    }

    private void randomKey() {
        Character[] alphabet = new Character[26];

        for (int i = 0; i < 26; i++) {
            alphabet[i] = (char)(i + offset);
        }

        Permutations.rndPerm(alphabet);
        key = alphabet;
        inverseKey = (Character[])Permutations.inversePerm(alphabet);
    }
}
