package helpers;

public class VigenerePT { // I think VigenereCT in 2.11 is the same as my decrypt
    private final int mod = 26;
    String key;
    String autoKey;

    public VigenerePT(String key) {
        this.key = key;
    }

    public String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        text = Text.toLowerCaseNoWhitespace(text);
        createAutoKey(text);

        for (int i = 0, n = text.length(); i < n; i++) {
            sb.append(charShiftPlus(text.charAt(i), autoKey.charAt(i)));
        }

        return sb.toString();
    }

    public String decrypt(String enc) {
        int index;
        StringBuilder sb = new StringBuilder();
        // autokey is only length of key, because I am not saving whole autokey,
        // but just part of it, that will be used (because every letter is used just once)
        StringBuilder autoKey = new StringBuilder(key);
        enc = Text.toLowerCaseNoWhitespace(enc);

        for (int i = 0, n = enc.length(); i < n; i++) {
            index = i % autoKey.length();
            sb.append(charShiftMinus(enc.charAt(i), autoKey.charAt(index)));
            autoKey.setCharAt(index, sb.charAt(i));
        }

        return sb.toString();
    }

    private void createAutoKey(String text) {
        autoKey = key.concat(text);
        autoKey = autoKey.substring(0, autoKey.length()-key.length());
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
