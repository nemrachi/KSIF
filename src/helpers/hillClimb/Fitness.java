package helpers.hillClimb;

public abstract class Fitness {
    public double evaluate(Cipher c, Key k, String ct) {
        String decrypted = c.decrypt(k, ct);
        return evaluateFitness(decrypted);
    }

    public abstract double evaluateFitness(String decrypted);
}
