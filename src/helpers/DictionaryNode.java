package helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DictionaryNode {

    private final char nodeChar;
    private boolean word;
    private List<DictionaryNode> childs;
    private final int len;

    public DictionaryNode(char nodeChar, boolean word, int len) {
        this.nodeChar = nodeChar;
        this.word = word;
        this.childs = new ArrayList<>();
        this.len = len;
    }

    public char getNodeChar() {
        return nodeChar;
    }

    public boolean isWord() {
        return word;
    }

    public void addToChilds(DictionaryNode node) {
        childs.add(node);
    }

    public boolean containsChild(char c) {
        for (DictionaryNode node : childs)
            if (node.nodeChar == c)
                return true;
        return false;
    }

    public DictionaryNode getChild(char c) {
        for (DictionaryNode node : childs)
            if (node.nodeChar == c)
                return node;
        return null;
    }

    public static DictionaryNode loadDictionary(Collection<String> words) {
        DictionaryNode root = new DictionaryNode('*', false, 0);
        DictionaryNode node;
        int i;

        for (String word : words) {
            node = root;
            i = 1;

            for (char c : word.toCharArray()) {
                if (!node.containsChild(c)) {
                    DictionaryNode newNode = new DictionaryNode(c, false, i);
                    node.addToChilds(newNode);
                    node = newNode;
                } else {
                    node = node.getChild(c);
                }
                i++;
            }

            node.word = true;
        }

        return root;
    }

    public static List<String> readDictionaryWords(String filePath) {
        File f = new File(filePath);
        List<String> words = new ArrayList<>();
        String line;

        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                while ((line = br.readLine()) != null) {
                    words.add(line.trim().toLowerCase());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return words;
    }

    public double evaluate(String text, int minLen, int maxLen) {
        DictionaryNode node, max = null;
        String subString;
        double sum = 0;
        int i, upTo, textLen = text.length();

        for (i = 0; i < textLen; i++) {
            upTo = java.lang.Math.min(i + maxLen, textLen);
            subString = text.substring(i, upTo);
            node = this;
            max = null;

            for (char c : subString.toCharArray()) {
                if (node.containsChild(c)) {
                    node = node.getChild(c);
                    if (node.word) {
                        max = node;
                    }
                } else {
                    break;
                }
            }

            if (max != null && max.len >= minLen) {
                sum += max.len;
                i += max.len-1;
            }
        }

        return sum / textLen;
    }
}
