import helpers.HomophonicSubstCipher;
import helpers.VigenereCipher;

import java.util.Arrays;

public class Main_cv6 {
    public static void main(String[] args) {
//        HomophonicSubstCipher hbc = new HomophonicSubstCipher("abcdefghabcdeijklmoabcde");
//        Integer[] enc = hbc.encrypt("Hello ema");
//        System.out.println(Arrays.toString(enc));
//        System.out.println(hbc.decrypt(enc));

        VigenereCipher hbc = new VigenereCipher("lev");
        String enc = hbc.encrypt("Hello ema");
        System.out.println(enc);
        System.out.println(hbc.decrypt(enc));
    }
}
