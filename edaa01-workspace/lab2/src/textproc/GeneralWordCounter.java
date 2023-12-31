package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {

    // note deklarerar en Map och ett Set för att spara ord
    private Map<String, Integer> m = new HashMap<String, Integer>();
    private Set<String> s;

    // note konstruktor
    GeneralWordCounter(Set<String> stopWordSet) {
        this.s = stopWordSet;
    }

    // note om ordet w finns i s returnar funktionen (läggs ej till i m)
    /// lägger till, i m, (w:0) om w ej redan finns, annars (w:värde+1)
    @Override
    public void process(String w) {
        if (s.contains(w)) {
            return;
        }
        if (m.containsKey(w)) {
            this.m.put(w, m.get(w) + 1);
        } else {
            this.m.put(w, 1);
        }

    }

    // note gör om m till en lista av Map.Entry's och sorterar de
    /// sorteras i första hand på antal förekomster och alfabetiskt om lika
    /// Printar sedan ut de 5 mest förekommande orden, om index < 5
    /// printar den ut "index" antal st
    @Override
    public void report() {
        Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(wordSet);
        wordList.sort((e1, e2) -> {
            int comparedValue = e2.getValue().compareTo(e1.getValue());
            if (comparedValue != 0) {
                return comparedValue;
            } else {
                return e1.getKey().compareTo(e2.getKey());
            }
        });

        int maxIndex = Math.min(5, wordList.size());
        for (int i = 0; i < maxIndex; i++) {
            System.out.println(wordList.get(i).toString());
        }
    }

    public List<Map.Entry<String, Integer>> getWordList() {
        return new ArrayList<Map.Entry<String, Integer>>(m.entrySet());
    }

}
