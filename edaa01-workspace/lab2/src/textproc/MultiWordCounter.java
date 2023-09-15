package textproc;

import java.util.Map;
import java.util.TreeMap;

// todox konstruktor ska ta en String-vektor som parameter
// todox endast ett attribut av typen map (key-value par)
// todox atributet ska ha typen map men objektet ska ha typen hashmap
// todox "hashmap" ska bara förekomma 1 gång vid deklarering (förutom import)
// todox report ska skriva ut alla keys och resp. value 
public class MultiWordCounter implements TextProcessor {

    private Map<String, Integer> m = new TreeMap<String, Integer>();

    MultiWordCounter(String[] stringArray) {
        for (String string : stringArray) {
            this.m.put(string, 0);
        }
    }

    // note behövs "this." här? är det onödigt eller bäst att ha med?
    @Override
    public void process(String w) {
        this.m.forEach((k, v) -> {
            if (k.equals(w)) {
                this.m.put(k, v + 1);
            }
        });
    }

    @Override
    public void report() {
        this.m.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

}
