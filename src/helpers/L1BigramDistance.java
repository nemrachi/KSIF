package helpers;

public class L1BigramDistance {

    private double ref[][];
    private final char offset = 'a';
    private final char alphaNum = 26;

    public L1BigramDistance() {
        ref = (double[][]) Text.readFromFile("_bigrams");
    }

    public double evaluate(String decr) { // returns quality of text
        decr = decr.toLowerCase().replaceAll("[^a-z]", "");
        double[][] tab = new double[alphaNum][alphaNum];
        double sum = 0;
        double len = decr.length() - 1;

        for (int i = 0; i < len; i++) {
            char a = decr.charAt(i);
            char b = decr.charAt(i+1);
            tab[a-offset][b-offset]++;
        }

        for (int i = 0; i < alphaNum; i++) {
            for (int j = 0; j < alphaNum; j++) {
                sum += java.lang.Math.abs(ref[i][j] - (tab[i][j] / len));
            }
        }

        return sum;
    }
}
