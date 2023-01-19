package helpers.hillClimb;

public interface Key {
    Key changeKey();
    void setScore(double score);
    double getScore();
    String toString();
}
