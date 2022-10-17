package helpers;

import java.security.InvalidParameterException;

public class PolybiusSquare {
    private char[][] tab = {
            {'a','b','c','d','e'},
            {'f','g','h','i','k'},
            {'l','m','n','o','p'},
            {'q','r','s','t','u'},
            {'v','w','x','y','z'},
    };

    public PolybiusSquare() {
    }

    public int[] encrypt(String text) {
        text = text.toLowerCase().replaceAll("[j\\s+]","");
        int i = 0;
        int[] encrypted = new int[text.length()];

        for (char ch : text.toCharArray()) {
            encrypted[i++] = getEncryptSub(ch);
        }

        return encrypted;
    }

    public String decrypt(int[] text) {
        StringBuilder decrypted = new StringBuilder();

        for (int num : text) {
            decrypted.append(getDecryptSub(num));
        }

        return decrypted.toString();
    }

    private int getEncryptSub(char ch) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (tab[row][col] == ch) {
                    return ((row+1)*10)+col+1;
                }
            }
        }
        throw new InvalidParameterException("Invalid input");
    }

    private char getDecryptSub(int pos) {
        int row = pos / 10;
        int col = pos % 10;
        row--;
        col--;
        return tab[row][col];
    }
}
