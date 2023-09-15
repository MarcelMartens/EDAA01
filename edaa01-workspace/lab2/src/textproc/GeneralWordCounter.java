package textproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {

    private Map<String, Integer> m = new HashMap<String, Integer>();
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
        wordList.sort((e1, e2) -> e2.getValue()
                .compareTo(e1.getValue()));

        int maxIndex = Math.min(5, wordList.size());
        for (int i = 0; i < maxIndex; i++) {
            System.out.println(wordList.get(i).toString());
        }
    }

}
