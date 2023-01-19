package skuska24h;

public class VigenereCipher {

    String key;

    public VigenereCipher(String key) {
        this.key = key;
    }

    public String encrypt(String pt) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0, n = pt.length(); i < n; i++) {
            sb.append(charShiftPlus(pt.charAt(i), key.charAt(i % key.length())));
        }

        return sb.toString();
    }

    public String decrypt(String ct) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0, n = ct.length(); i < n; i++) {
            sb.append(charShiftMinus(ct.charAt(i), key.charAt(i % key.length())));
        }

        return sb.toString();
    }

    private Character charShiftPlus(Character toShift, Character offset) {
        int toShiftInt = toShift - Constants.OFFSET;
        int offsetInt = offset - Constants.OFFSET;
        return (char) (((toShiftInt + offsetInt) % Constants.ALPHA_NUM) + Constants.OFFSET);
    }

    private Character charShiftMinus(Character toShift, Character offset) {
        int toShiftInt = toShift - Constants.OFFSET;
        int offsetInt = offset - Constants.OFFSET;
        return (char) (((toShiftInt - offsetInt + Constants.ALPHA_NUM) % Constants.ALPHA_NUM) + Constants.OFFSET);
    }
}
