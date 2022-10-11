package helpers;

public class DoubleColumnTransposition {
    private SingleColumnTransposition sct1;
    private SingleColumnTransposition sct2;

    public DoubleColumnTransposition(String keyPhrase1, String keyPhrase2) {
        this(Permutations.getPermFromString(keyPhrase1),
                Permutations.getPermFromString((keyPhrase2)));
    }

    public DoubleColumnTransposition(Integer[] key1, Integer[] key2) {
        this.sct1 = new SingleColumnTransposition(key1);
        this.sct2 = new SingleColumnTransposition(key2);
    }

    public String encrypt(String text) {
        String firstEnc = sct1.encrypt(text);
        return sct2.encrypt(firstEnc);
    }

    public String decrypt(String text) {
        String firstDec = sct2.decrypt(text);
        return sct1.decrypt(firstDec);
    }
}
