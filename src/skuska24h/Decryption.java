package skuska24h;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Decryption {
    int keyLength;
    String ct; // cipher text
    CeasarSolver ceasarSolver = new CeasarSolver();
    // ct is divided to [keyLen] offsets of letters
    ArrayList<StringBuilder> offsetList = new ArrayList<>();

    public Decryption(int keyLength, String ct) {
        this.ct = ct;
        this.keyLength = keyLength;
    }

    public void decrypt() {
        createOffsetList();

        StringBuilder key = new StringBuilder();

        for(StringBuilder offset : offsetList) {
            Integer bestShift = ceasarSolver.solve(offset.toString());
            key.append(getCharShift(bestShift));
        }

        System.out.println("\nkey: " + key + "\nplain text: " + new VigenereCipher(key.toString()).decrypt(ct));
    }

    private char getCharShift(int shift) {
        return (char)(shift + Constants.OFFSET);
    }

    private void createOffsetList() { // define and fill offset list
        for (int i = 0; i < keyLength; i++) {
            offsetList.add(new StringBuilder());
        }

        for (int i = 0; i < ct.length(); i++) {
            offsetList.get(i % keyLength).append(ct.charAt(i));
        }
    }
}
