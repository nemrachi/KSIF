package helpers.hillClimb;

public interface Cipher {
    String encrypt(Key k, String pt);
    String decrypt(Key k, String ct);
    Key generateKey();
}
