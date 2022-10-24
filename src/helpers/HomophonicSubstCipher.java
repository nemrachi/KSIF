package helpers;

import java.util.*;

public class HomophonicSubstCipher {
    int num = 5;
    Map<Character, List<Integer>> encKey;
    Map<Integer, Character> decKey;
    Random rnd = new Random(Double.doubleToLongBits(java.lang.Math.random()));

    public HomophonicSubstCipher() {
        rndKey();
    }

    public HomophonicSubstCipher(String text) {
        keyBenjaminFranklin(text);
    }

    public Integer[] encrypt(String text) {
        text = Text.toLowerCaseNoWhitespace(text);
        Integer[] encrypted = new Integer[text.length()];

        for (int i = 0, n = text.length(); i < n; i++) {
            encrypted[i] = getIntFromList(this.encKey.get(text.charAt(i)));
        }

        return encrypted;
    }

    public String decrypt(Integer[] enc) {
        StringBuilder sb = new StringBuilder();

        for(Integer i : enc) {
            sb.append(this.decKey.get(i));
        }
        return sb.toString();
    }

    private void rndKey() {
        this.encKey = new HashMap<>();
        this.decKey = new HashMap<>();

        List <Integer> nums = new ArrayList<>();
        for (int i = 0, n = 26 * this.num; i < n; i++) {
            nums.add(i);
        }
        Collections.shuffle(nums);

        List<ArrayList<Integer>> subs = partition(nums, this.num); // homophones

        int idx = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            encKey.put(c, subs.get(idx));
            decKey.put(getIntFromList(subs.get(idx++)), c);
        }
    }

    private void keyBenjaminFranklin(String text) {
        this.encKey = new HashMap<>();
        this.decKey = new HashMap<>();
        int i = 1;
        for (Character c : text.toCharArray()) {
            if (!this.encKey.containsKey(c)) {
                this.encKey.put(c, new ArrayList<>());
            }
            this.encKey.get(c).add(i++);
        }
        for (Map.Entry<Character, List<Integer>> entry : this.encKey.entrySet()) {
            this.decKey.put(getIntFromList(entry.getValue()), entry.getKey());
        }
        System.out.println(this.encKey);
        System.out.println(this.decKey);
    }

    private <T> List<ArrayList<T>> partition(List<T> l, int partSize) {
        List<ArrayList<T>> newL = new ArrayList<>();
        for (int i = 0, n = l.size(); i < n; i += partSize) {
            if (i+partSize > n) {
                partSize = n - i;
            }
            newL.add(new ArrayList<>(l.subList(i, i+partSize)));
        }
        return newL;
    }

    private Integer getIntFromList(List<Integer> l) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : l) {
            sb.append(i);
        }
        return Integer.parseInt(sb.toString());
    }
}
