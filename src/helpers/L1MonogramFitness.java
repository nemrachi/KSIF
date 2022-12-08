package helpers;

public class L1MonogramFitness {
    private final char offset = 'a';
    private final int alphaNum = 26;
    static final double [] ref = {
            0.08167, 0.01492, 0.02782, 0.04253, 0.12702,
            0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
            0.00772, 0.04025, 0.02406, 0.06749, 0.07507,
            0.01929, 9.5e-4, 0.05987, 0.063269, 0.0905599,
            0.02758, 0.00978, 0.0236, 0.0015, 0.01974, 7.4E-4
    };

    public double evaluate(String decr) {
        double[] statistics = statisticsCount(decr);
        double sum = 0;

        for (int i = 0; i < alphaNum; i++) {
            sum += java.lang.Math.abs(ref[i]-statistics[i]);
        }

        return sum;
    }

    public double[] statisticsCount(String str) {
        double[] res = new double[alphaNum];
        double len = str.length();
        int i;

        for (i = 0; i < len; i++) {
            res[str.charAt(i)-offset]++;
        }
        for (i = 0; i < alphaNum; i++) {
            res[i] /= len;
        }

        return res;
    }
}
