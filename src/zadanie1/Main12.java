package zadanie1;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main12 {
    private static HashMap<String, Double> slovakRelFreq = new HashMap<String, Double>() {{
        put("a", 0.0949);
        put("b", 0.019);
        put("c", 0.0345);
        put("d", 0.0409);
        put("e", 0.0916);
        put("f", 0.0031);
        put("g", 0.004);
        put("h", 0.0235);
        put("i", 0.0681);
        put("j", 0.0212);
        put("k", 0.0380);
        put("l", 0.0456);
        put("m", 0.0297);
        put("n", 0.0634);
        put("o", 0.0934);
        put("p", 0.0287);
//        put("q", 0.00000);
        put("r", 0.0512);
        put("s", 0.0594);
        put("t", 0.0506);
        put("u", 0.0370);
        put("v", 0.0485);
//        put("w", 0.0006);
//        put("x", 0.0003);
        put("y", 0.0257);
        put("z", 0.0272);
//        put(" ", 0.13489);
    }};
    private static HashMap<String, Double> czechRelFreq = new HashMap<String, Double>() {{
        put("a", 0.0899);
        put("b", 0.0186);
        put("c", 0.0304);
        put("d", 0.0414);
        put("e", 0.1013);
        put("f", 0.0033);
        put("g", 0.0048);
        put("h", 0.0206);
        put("i", 0.0692);
        put("j", 0.0210);
        put("k", 0.0344);
        put("l", 0.0420);
        put("m", 0.0299);
        put("n", 0.0664);
        put("o", 0.0839);
        put("p", 0.0354);
//        put("q", 0.000);
        put("r", 0.0533);
        put("s", 0.0574);
        put("t", 0.0498);
        put("u", 0.0394);
        put("v", 0.0450);
//        put("w", 0.006);
//        put("x", 0.004);
        put("y", 0.0272);
        put("z", 0.0344);
//        put(" ", 0.182);
    }};
    private static HashMap<String, Double> myRelFreq = new HashMap<>();

    private static void getMyRelFreq(String[] ct) {
        int len = ct.length;

        for (String ch : ct) {
            if (ch.equals("-")) { continue; }
            myRelFreq.put(ch, myRelFreq.containsKey(ch) ? myRelFreq.get(ch)+1 : 1);
        }

        for (Map.Entry<String, Double> entry : myRelFreq.entrySet()) {
            entry.setValue(entry.getValue() / len);
        }
    }

    private static void printRelFreq() {
        slovakRelFreq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.print(entry.getKey() + "=" + String.format("%.4f", entry.getValue()) + "\t"));
        System.out.println();

        czechRelFreq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.print(entry.getKey() + "=" + String.format("%.4f", entry.getValue()) + "\t"));
        System.out.println();

        myRelFreq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.print(entry.getKey() + "=" + String.format("%.4f", entry.getValue()) + "\t"));
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./src/zadanie1/19.txt");
        String[] ct = Files.readAllLines(path).get(0).split("\\.");

        getMyRelFreq(ct);
        printRelFreq();
    }
}
