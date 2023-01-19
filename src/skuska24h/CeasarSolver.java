package skuska24h;

import java.util.TreeMap;

public class CeasarSolver {
    public Integer solve(String text) {
        CaesarCipher cc;
        String pt;
        Fitness fitness = new Fitness();
        TreeMap<Double, Integer> scores = new TreeMap<>(); // treemap is automatically sorted // <score, shift>

        for (int i = 0; i < Constants.ALPHA_NUM; i++) {
            cc = new CaesarCipher(i);
            pt = cc.decrypt(text);
            double score = fitness.evaluate(pt);
            scores.put(score, i);
        }

        System.out.println("score: " + scores.firstEntry().getKey() + "\tshift: " + (char)(scores.firstEntry().getValue() + Constants.OFFSET));
        return scores.firstEntry().getValue(); // smallest key/score -> best
    }
}
