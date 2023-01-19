import helpers.*;
import helpers.hillClimb.Cipher;
import helpers.hillClimb.Fitness;
import helpers.hillClimb.MonoAlphaCipher;

import java.lang.Math;
import java.util.*;

import static helpers.Text.getRandChar;

public class Main {
    public static void main(String[] args) {
        cv12();
    }

    private static void cv1() {
        String path = ".\\cv1\\ema.txt";
        Text.saveToFile("Hello darkness my old\nfriend?!", path);
        System.out.println(Text.readFromFile(path));
    }

    private static void cv2() {
        perm_1_11();
    }

    private static void cv3() {
        transposition("hello world", 3);
    }

    private static void cv4() {
        String keyPhrase = "priklad";
        DoubleColumnTransposition dct = new DoubleColumnTransposition(keyPhrase, keyPhrase);
        String encrypted = dct.encrypt("castopouzivanysposob");
        System.out.println(encrypted);
        System.out.println(dct.decrypt(encrypted));
    }

    private static void cv5() {
//        PolybiusSquare ps = new PolybiusSquare();
//        int[] enc = ps.encrypt("hello ema");
//        System.out.println(Arrays.toString(enc));
//        System.out.println(ps.decrypt(enc));

        MonoAlphaSubstCipher masc = new MonoAlphaSubstCipher();
        String enc = masc.encrypt("hello ema");
        System.out.println(enc);
        System.out.println(masc.decrypt(enc));
    }

    private static void cv6() {
//        HomophonicSubstCipher hbc = new HomophonicSubstCipher("abcdefghabcdeijklmoabcde");
//        Integer[] enc = hbc.encrypt("Hello ema");
//        System.out.println(Arrays.toString(enc));
//        System.out.println(hbc.decrypt(enc));

        VigenereCipher hbc = new VigenereCipher("lev");
        String enc = hbc.encrypt("Hello ema");
        System.out.println(enc);
        System.out.println(hbc.decrypt(enc));
    }

    private static void cv7() {
        VigenerePT vpt = new VigenerePT("heslo");
        String enc = vpt.encrypt("Hello ema");
        System.out.println(enc);
        System.out.println(vpt.decrypt(enc));
    }

    private static void cv8() {

    }

    private static void cv9() {
        Language lang = new Language();
        System.out.println(lang.guessLanguage("Ich habe eine katze"));
    }

    private static void cv10() {
        String str = "I am required to implement general dictionary using Java that will allow efficient O(logN) or better insertions, deletions and random access.";

        L1BigramDistance l1 = new L1BigramDistance();
        System.out.println(l1.evaluate(str));

        String dictPath = "dictionary_5000.txt";
        DictionaryNode root = DictionaryNode.loadDictionary(DictionaryNode.readDictionaryWords(dictPath));
        System.out.println(root.evaluate(str, 1, 10));
    }

    private static void cv11() {
//        String str = "Lipps qc reqi mw Iqe erh m eq jmri"; // Hello my name is Ema and i am fine +4
//        System.out.println(Solver.caesarSolver(str));

//        String input = "hellomynameisemaandiamfine";
//        String key = "levica";
//        VigenereCipher vc = new VigenereCipher(key);
//        String cipher = vc.encrypt(input);
//        int guessedKeyLen = Solver.guessVigenereKeyLen(cipher);
//        System.out.println(guessedKeyLen);
//        assert key.length() == guessedKeyLen;
    }

    private static void cv12() {
        Cipher c = new MonoAlphaCipher();
        // Fitness f =
    }

    // cv2 /////////////////////////////////////////////////////////////////////
    // ~2.1
    private static String writeEven(String x) {
        StringBuilder sb = new StringBuilder();
        x = x.toLowerCase().replaceAll("\\s+","");

        for (int i = 0, n = x.length(); i < n; i++) {
            sb.append(getRandChar()).append(x.charAt(i)).append(getRandChar());
            if (i % 2 == 1) {
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }

    private static String readEven(String y) {
        StringBuilder sb = new StringBuilder();
        y = y.toLowerCase().replaceAll("\\s+","");

        for (int i = 1, n = y.length(); i < n; i += 3) {
            sb.append(y.charAt(i));
        }

        return sb.toString().trim();
    }

    private static String writeOdd(String x) {
        StringBuilder sb = new StringBuilder();
        String[] words = x.toLowerCase().split(" ");

        for (String word : words) {
            for (int i = word.length()-1; i >= 0; i--) {
                sb.append(getRandChar()).append(word.charAt(i)).append(getRandChar());
                if (i % 2 == 0) {
                    sb.append(" ");
                }
            }
            sb.append(" ").append(getRandChar()).append(" ");
        }

        return sb.toString().trim();
    }

    private static String readOdd(String y) {
        StringBuilder sb = new StringBuilder();
        StringBuilder wordSb = new StringBuilder();
        String[] words = y.toLowerCase().split(" ");

        for (String word : words) {
            if (word.length() == 1) {
                sb.append(wordSb.reverse()).append(" ");
                wordSb.setLength(0);
            }

            for (int i = 1, n = word.length(); i < n; i += 3) {
                wordSb.append(word.charAt(i));
            }
        }

        return sb.toString().trim();
    }

    // ~1.11
    private static void perm_1_11() {
        Random rnd = new Random(Double.doubleToLongBits(Math.random()));
        Set<Integer> s = new HashSet<>();

        while (s.size() < 10) {
            s.add(rnd.nextInt(26) + 1);
        }

        List<Integer> l = new ArrayList<>(s);
        Object[] arr = l.toArray();
        System.out.println(Arrays.toString(arr));

        //Collections.shuffle(l, rnd);
        Permutations.rndPerm(arr);

        System.out.println(Arrays.toString(arr));
    }

    // ~1.15
    private static void allStrings(int lvl, int maxLvl, Character[] pwd, Set<Character[]> res) {
        if (lvl == maxLvl) {
            res.add(pwd.clone());
        } else {
            for (int i = 0; i < 26; i++) {
                pwd[lvl] = (char)(i + 'a');
                allStrings(lvl+1, maxLvl, pwd, res);
            }
        }
    }

    // cv3 /////////////////////////////////////////////////////////////////////
    // ~2.12
    private static Integer[] bellaso(String phrase) {
        phrase = phrase.toLowerCase();
        StringBuilder sorted = new StringBuilder(sortAlphabetically(phrase));
        Integer[] perm = new Integer[phrase.length()];
        int iofChar, i = 0;

        for (char ch : phrase.toCharArray()) {
            iofChar = sorted.indexOf(String.valueOf(ch));
            sorted.setCharAt(iofChar, (char) 0);
            perm[i++] = iofChar;
        }

        return perm;
    }

    private static String sortAlphabetically(String str) {
        char chArr[] = str.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }

    // ~2.13
    private static void transposition(String phrase, int blockSize) {
        phrase = phrase.toLowerCase().replaceAll("\\s+","");
        int phraseLen = phrase.length();
        int phraseSplitLen = (phraseLen + blockSize - 1) / blockSize;
        char ch;
        char[][] phraseSplit = new char[phraseSplitLen][blockSize];

        for(int i = 0, j = 0, n = phraseLen*blockSize; i < n;  i++, j++) {
            if (i >= phraseLen) {
                ch = 'x';
            } else {
                ch = phrase.charAt(i);
            }
            phraseSplit[i%phraseSplitLen][i%blockSize] = ch;
        }

        int k = 0;
        for(int i = 0; i < phraseSplitLen; i++) {
            for(int j = 0; j < blockSize; j++) {
                if (k >= phraseLen) {
                    ch = 'x';
                } else {
                    ch = phrase.charAt(k);
                }
                phraseSplit[i][j] = ch;
                k++;
            }
        }

        System.out.println(Arrays.deepToString(phraseSplit));
    }
}