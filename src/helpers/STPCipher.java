package helpers;

public class STPCipher {
    private MonoAlphaSubstCipher s;
    private SingleColumnTransposition t;
    private VigenereCipher p;

    private STPCipher() {}

    public STPCipher(MonoAlphaSubstCipher s, SingleColumnTransposition t, VigenereCipher p) {
        this.s = s;
        this.t = t;
        this.p = p;
    }

    public STPCipher(Character[] k1, Integer[] k2, String k3) {
        this.s = new MonoAlphaSubstCipher(k1);
        this.t = new SingleColumnTransposition(k2);
        this.p = new VigenereCipher(k3);
    }

    public String encrypt(String text) {
        String ct1 = s.encrypt(text);
        String ct2 = t.encrypt(ct1);
        return p.encrypt(ct2);
    }

    public String decrypt(String enc) {
        String enc1 = p.decrypt(enc);
        String enc2 = t.decrypt(enc1);
        return s.decrypt(enc2);
    }
}
