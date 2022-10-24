package helpers;

import javax.swing.*;
import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class Text {
    // ~1.4
    public static void getRndChars() {
        Random rnd = new Random(System.currentTimeMillis());
        char offset = 'a';
        List<Character> chars = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            char ch = (char) (rnd.nextInt(26) + offset);
            System.out.println(ch);
            chars.add(ch);
        }

        Collections.sort(chars);
        System.out.println(chars);

        Set<Character> uniq = new HashSet<>(chars);
        System.out.println(uniq.size());
    }

    // ~1.5
    public static void printArraysLists() {
        List<String> names = Arrays.asList("Ema", "Peter", "Xenia");
        System.out.println(names);

        String[] namesArr = new String[names.size()];
        names.toArray(namesArr);
        System.out.println(namesArr); // prints "[Ljava.lang.String;@6d03e736"
        System.out.println(Arrays.toString(namesArr));
    }

    // ~1.6
    public static void splitArrays() {
        Random rnd = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            int rndN = rnd.nextInt(100) + 1;
            if (rndN % 2 == 0) {
                sb.append(rndN).append(",");
            }
        }
        System.out.println(sb.toString());

        String[] strArr = sb.toString().split(",");
        Integer[] intArr = new Integer[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        System.out.println(Arrays.toString(intArr));
        System.out.println(Arrays.toString(intArr).replace("[", "").replace("]", ""));
    }

    // ~1.7
    public static void convertToTSA(String str, boolean keepSpace) {
        str = Normalizer.normalize(str.toLowerCase( ), Normalizer.Form.NFD); // !! important for usage of regexp below
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        String regexp;
        if (keepSpace) {
            regexp = "[^a-z ]";
        } else {
            regexp = "[^a-z]";
        }
        str = str.replaceAll(regexp, "");

        System.out.println(str);
    }

    // ~1.8
    public static File pickFromFileChooser() {
        JFileChooser fc = new JFileChooser(new File("."));
        fc.setDialogTitle("Select the file to open: ");
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int ret = fc.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }
        return null;
    }

    // ~1.8
    public static void writeText(File file, String content) {
        if (file.exists()) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                bw.write(content);
                bw.flush();
            } catch (IOException ioe) {
            } finally {
                try {
                    bw.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    // ~1.9
    public static String readText(File file) {
        StringBuilder sb = new StringBuilder();

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException ioe) {
                sb = null;
            } finally {
                try {
                    br.close();
                } catch (IOException ioe) {
                }
            }
        }

        return sb.toString();
    }

    // ~1.10
    public static Object readFromFile(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return ois.readObject();
        } catch (Exception e) {
            System.err.println(e.fillInStackTrace().toString());
        }

        return  null;
    }

    // ~1.10
    public static void saveToFile(Object o, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(o);
        } catch (Exception e) {
            System.err.println(e.fillInStackTrace().toString());
        }
    }

    // ~2.1
    public static char getRandChar() {
        Random rnd = new Random(Double.doubleToLongBits(java.lang.Math.random()));
        // 97-122 // a-z
        return (char) rnd.ints(97, 123).findFirst().getAsInt();
    }

    public static String toLowerCaseNoWhitespace(String text) {
        return text.toLowerCase().replaceAll("[\\s+]","");
    }
}
