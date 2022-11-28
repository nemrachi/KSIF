package helpers;

import java.util.Map;

import static java.lang.Math.abs;

public class Cryptosystem {

    public enum Type {
        Monoalphabetic,
        Transpotion,
        Polyalphabetic,
        Unknown
    }

    public static double[] ref = {
            0.08167, 0.01492, 0.02782, 0.04253, 0.12702,
            0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
            0.00772, 0.04025, 0.02406, 0.06749, 0.07507,
            0.01929, 9.5E-4, 0.05987, 0.063269, 0.0905599,
            0.02758, 0.00978, 0.0236, 0.0015, 0.01974, 7.4E-4
    };

    public static Type guess(String text) {
        Type guessedType;
        Map<String, Double> ngrams = Text.readNGram(text, 1, true);

        if (Language.guessLanguage(text) == Language.LangEnum.Unknown) {
            guessedType = Type.Polyalphabetic;
        } else {
            double distance = 0;

            for (Map.Entry<String, Double> entry : ngrams.entrySet()) {
                int idx = entry.getKey().charAt(0) - 'a';
                distance += abs(entry.getValue() - ref[idx]);
            }
            if (distance < 0.5) {
                guessedType = Type.Transpotion;
            } else {
                guessedType = Type.Monoalphabetic;
            }
        }

        return guessedType;
    }
}
