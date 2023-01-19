package skuska24h;

public class CaesarCipher {
    private final int key;

    public CaesarCipher(int key) {
        this.key = key % Constants.ALPHA_NUM;
    }

    public String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            encrypted.append(getEncryptSub(ch));
        }

        return encrypted.toString();
    }

    public String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            decrypted.append(getDecryptSub(ch));
        }

        return decrypted.toString();
    }

    private char getEncryptSub(char ch) { // get char substitute after shift for encryption
        return (char)((((ch - Constants.OFFSET) + key) % Constants.ALPHA_NUM) + Constants.OFFSET);
    }

    private char getDecryptSub(char ch) { // get char substitute after shift for decryption
        // java have problem with negative modulo (solution: 1. +mod, 2. %mod)
        return (char)((((ch - Constants.OFFSET) - key + Constants.ALPHA_NUM) % Constants.ALPHA_NUM) + 'a');
    }
}
