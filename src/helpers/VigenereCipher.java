package helpers;

import java.util.List;
import java.util.Map;

public class VigenereCipher {
    private final int mod = 26;
    String key;

    public VigenereCipher(String key) {
        this.key = key;
    }

    public String encrypt(String text) {
        text = Text.toLowerCaseNoWhitespace(text);
        StringBuilder sb = new StringBuilder();

        for (int i = 0, n = text.length(); i < n; i++) {
            sb.append(charShiftPlus(text.charAt(i), key.charAt(i % key.length())));
        }

        return sb.toString();
    }

    public String decrypt(String enc) {
        enc = Text.toLowerCaseNoWhitespace(enc);
        StringBuilder sb = new StringBuilder();

        for (int i = 0, n = enc.length(); i < n; i++) {
            sb.append(charShiftMinus(enc.charAt(i), key.charAt(i % key.length())));
        }

        return sb.toString();
    }

    private Character charShiftPlus(Character toShift, Character offset) {
        int toShiftInt = toShift - 'a';
        int offsetInt = offset - 'a';
        return (char) (((toShiftInt + offsetInt) % mod) + 'a');
    }

    private Character charShiftMinus(Character toShift, Character offset) {
        int toShiftInt = toShift - 'a';
        int offsetInt = offset - 'a';
        return (char) (((toShiftInt - offsetInt + mod) % mod) + 'a');
    }
}
