import helpers.HomophonicSubstCipher;
import helpers.VigenereCipher;
import helpers.VigenerePT;

import java.util.Arrays;

public class Main_cv7 {
    public static void main(String[] args) {
        VigenerePT vpt = new VigenerePT("heslo");
        String enc = vpt.encrypt("Hello ema");
        System.out.println(enc);
        System.out.println(vpt.decrypt(enc));
    }
}
