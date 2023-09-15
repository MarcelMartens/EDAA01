package textproc;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {

    private Map<String, Integer> m = new TreeMap<String, Integer>();
    private Set<String> s;

    GeneralWordCounter(Set<String> stopWordSet) {
        this.s = stopWordSet;
    }

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

    @Override
    public void report() {
        Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(wordSet);
        // alt wordList.sort((e1, e2) -> e2.getValue()
        // alt .compareTo(e1.getValue()));
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

}
