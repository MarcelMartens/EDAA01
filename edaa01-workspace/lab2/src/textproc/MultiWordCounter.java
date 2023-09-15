package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {

    // note skapar ny HashMap för att spara orden och antal gånger
    private Map<String, Integer> m = new HashMap<String, Integer>();

    // note konstruktor, skapar m där varje k-v är (stringArray[]:0)
    MultiWordCounter(String[] stringArray) {
        for (String string : stringArray) {
            this.m.put(string, 0);
        }
    }

    @Override
    // note kör k.equals(w) på varje element, k, i m.
    // note Om likhet ökar värdet kopplat till k
    public void process(String w) {
        this.m.forEach((k, v) -> {
            if (k.equals(w)) {
                this.m.put(k, v + 1);
            }
        });
    }

    @Override
    // note skriver ut varje k-v i m
    public void report() {
        this.m.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

}
