import helpers.DoubleColumnTransposition;

public class Main_cv4 {
    public static void main(String[] args) {
        String keyPhrase = "priklad";
        DoubleColumnTransposition dct = new DoubleColumnTransposition(keyPhrase, keyPhrase);
        String encrypted = dct.encrypt("castopouzivanysposob");
        System.out.println(encrypted);
        System.out.println(dct.decrypt(encrypted));
    }
}