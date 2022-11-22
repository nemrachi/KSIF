package helpers;

import java.util.*;

public class Language {

    public enum LangEnum {
        English,
        German,
        Slovak,
        Unknown
    }

    public static final double ENGLISH_CI = 0.0665;
    public static final double GERMAN_CI = 0.076;
    public static final double SLOVAK_CI = 0.0603;
    public static final double UNKNOWN_CI = 0.039;

    private static final Map<LangEnum, Double> ciMap = new HashMap<LangEnum, Double>() {{
        put(LangEnum.English, ENGLISH_CI);
        put(LangEnum.German, GERMAN_CI);
        put(LangEnum.Slovak, SLOVAK_CI);
        put(LangEnum.Unknown, UNKNOWN_CI);
    }};

    public static LangEnum guessLanguage(String text) {
        Collection<Double> coll = Text.readNGram(text, 1, false).values();
        Double[] stat = new Double[coll.size()];
        coll.toArray(stat);
        double textCi = Text.coincidenceIndex(stat, text.length());
        LangEnum lang = LangEnum.Unknown;
        double minDist = 1;

        for (Map.Entry<LangEnum, Double> entry : ciMap.entrySet()) {
            double dist = java.lang.Math.abs(textCi - entry.getValue());
            if (dist < minDist) {
                minDist = dist;
                lang = entry.getKey();
            }
        }

        return lang;
    }
}
