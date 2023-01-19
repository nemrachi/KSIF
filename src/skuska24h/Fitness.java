package skuska24h;

import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Fitness {
    private double[][] ref;

    public Fitness() {
        try (ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(Paths.get(Constants.PATH_BIGRAMS)))) {
            ref = (double[][]) stream.readObject();
        } catch (Exception e) {
            System.err.println(e.fillInStackTrace().toString());
            System.exit(1);
        }
    }

    public double evaluate(String text) { // returns quality of text
        char a, b;
        double sum = 0, length = text.length() - 1;
        double[][] tab = new double[Constants.ALPHA_NUM][Constants.ALPHA_NUM];

        for (int i = 0; i < length; i++) { // get bigrams from text
            a = text.charAt(i);
            b = text.charAt(i+1);
            tab[a-Constants.OFFSET][b-Constants.OFFSET]++;
        }

        for (int i = 0; i < Constants.ALPHA_NUM; i++) {
            for (int j = 0; j < Constants.ALPHA_NUM; j++) {
                // get distance of text bigrams from original english bigrams and sum them
                sum += Math.abs(ref[i][j] - (tab[i][j] / length));
            }
        }

        return sum;
    }
}
