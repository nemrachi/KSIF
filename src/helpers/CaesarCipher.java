package helpers;

public class CaesarCipher {
    private final int key;
    private final int mod = 26;

    public CaesarCipher(int key) {
        this.key = key % mod;
    }

    public String encrypt(String text) {
        text = text.toLowerCase();
        StringBuilder encrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            encrypted.append(getEncryptSub(ch));
        }

        return encrypted.toString();
    }

    public String decrypt(String text) {
        text = text.toLowerCase();
        StringBuilder decrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (ch == ' ') {
                decrypted.append(' ');
            } else {
                decrypted.append(getDecryptSub(ch));
            }
        }

        return decrypted.toString();
    }

    private char getEncryptSub(char ch) {
        return (char)((((ch - 'a') + key) % mod) + 'a');
    }

    private char getDecryptSub(char ch) { // java have problem with negative modulo
        return (char)((((ch - 'a') - key + mod) % mod) + 'a');
    }
}
