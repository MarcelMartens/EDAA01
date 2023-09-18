package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

    // todo lägg till/skapa wordcounter
    // todo lägg till/skapa bookreadercontroller
    // todo läs in orden från .txt och
    // todo kör
    public static void main(String[] args) throws FileNotFoundException {
        // note deklarerar scanners
        Scanner s1 = new Scanner(new File("edaa01-workspace/lab3/nilsholg.txt"));
        Scanner s2 = new Scanner(new File("edaa01-workspace/lab3/undantagsord.txt"));

        // note konfigurerar scanners för rätt inläsning
        s1.findWithinHorizon("\uFEFF", 1);
        s1.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
        s2.useDelimiter("\\s");

        // note Skapar ett HashSet med alla undantagsord
        Set<String> stopWordSet = new HashSet<>();
        while (s2.hasNext()) {
            String word = s2.next().toLowerCase();
            stopWordSet.add(word);
        }

        // note deklarerar en GeneralWordCounter
        GeneralWordCounter p = new GeneralWordCounter(stopWordSet);

        // note deklarerar ett BookReaderController objekt och callar createWindow
        BookReaderController brc = new BookReaderController(p);

        // note kör .process() på GeneralWordCounter p
        while (s1.hasNext()) {
            String word = s1.next().toLowerCase();
            p.process(word);
        }

        // note stänger scanners
        s1.close();
        s2.close();

    }
}
