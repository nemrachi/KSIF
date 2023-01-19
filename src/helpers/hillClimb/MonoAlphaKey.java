package helpers.hillClimb;

import helpers.Permutations;

import java.util.Arrays;
import java.util.Random;

public class MonoAlphaKey implements Key {
    private final Random random = new Random(Double.doubleToLongBits(Math.random()));
    Character[] key, inverseKey;
    double score;

    public MonoAlphaKey() {}

    public MonoAlphaKey(Character[] key) {
        this.key = key;
        this.inverseKey = (Character[])Permutations.inversePerm(key);
    }

    public Key changeKey() {
        Character[] newK = key.clone();

        int i = random.nextInt(newK.length);
        int j = random.nextInt(newK.length);
        if (i == j) j++;

        Character tmp = newK[i];
        newK[i] = newK[j];
        newK[j] = tmp;

        return new MonoAlphaKey(newK);
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public String toString() {
        return Arrays.toString(key);
    }
}
