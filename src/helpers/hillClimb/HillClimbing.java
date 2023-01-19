package helpers.hillClimb;

public class HillClimbing {
    Key k;
    Fitness f;
    Cipher c;
    String ct;

    public HillClimbing(String ct, Cipher c, Fitness f) {
        this.ct = ct;
        this.c = c;
        this.f = f;
        this.k = c.generateKey();
        this.k.setScore(f.evaluate(c, k, ct));
    }

    public void start(int iterations) { // number of runs
        for (int i = 0; i < iterations; i++) {
            Key tmpK = k.changeKey();
            double score = f.evaluate(c, tmpK, ct);
            tmpK.setScore(score);

            if (tmpK.getScore() < k.getScore()) {
                k = tmpK;
            }
        }
        System.out.println("Score: " + k.getScore() + ", key: " + k);
        System.out.println(c.decrypt(k, ct));
    }
}
