import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LetterFrequency {
    public static Map<Character, Float> letterFrequency = new HashMap<Character, Float>() {{
        put('e', 11.16F);
        put('a', 8.4966F);
        put('r', 7.5809F);
        put('i', 7.16F);
        put('o', 7.16F);
        put('t', 6.95F);
        put('n', 6.65F);
        put('s', 5.73F);
        put('l', 5.48F);
        put('c', 4.53F);
        put('u', 3.63F);
        put('d', 3.38F);
        put('p', 3.16F);
        put('m', 3.01F);
        put('h', 3.00F);
        put('g', 2.47F);
        put('b', 2.072F);
        put('f', 1.812F);
        put('y', 1.777F);
        put('w', 1.289F);
        put('k', 1.1016F);
        put('v', 1.0074F);
        put('x', 0.2902F);
        put('z', 0.2722F);
        put('j', 0.1965F);
        put('q', 0.1962F);
    }};

    public static float numLetterWeight = 0.5f, totLetterDiffWeight = 0.25f, endLetterDiffWeight = 0.25f; //MUST sum to 1.0
}
