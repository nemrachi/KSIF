import helpers.MonoAlphaSubstCipher;
import helpers.PolybiusSquare;

import java.util.Arrays;

public class Main_cv5 {
    public static void main(String[] args) {
//        PolybiusSquare ps = new PolybiusSquare();
//        int[] enc = ps.encrypt("hello ema");
//        System.out.println(Arrays.toString(enc));
//        System.out.println(ps.decrypt(enc));

        MonoAlphaSubstCipher masc = new MonoAlphaSubstCipher();
        String enc = masc.encrypt("hello ema");
        System.out.println(enc);
        System.out.println(masc.decrypt(enc));
    }
}
