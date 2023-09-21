package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

// todo JPane-Controller
// () knappar: sortera alfabetisk, sortera antal, sök (för sökfält)
// () sökfält
// () huvud-doc för listan med ord
// (x) fixa import från annat projekt
// changed lade till "lab2/" i .classpath för att få bort felet.
// () set-metod för ordlista
// () get-metod för sorterade listor
// () titel på fönster
// () update-metod?
// () skapa en JList och knyt den till en listmodell (SortedListModel)
// () skapa JScrollPane, ska innehålla listmodellen och lägg till i fönstret
// () lägg till texten "sort by:" till vänster om knapparna
// (x) fixa bugg där listan ändras under tiden den ittererar.

// bug metod från annat projekt kanske inte är bäst att lösa genom
/// att ändra .classpath, kolla så det inte skapar kaos me han
// note klass för att kontrollera J-Objekt
public class BookReaderController {
    // alt private JList<Map.Entry<String, Integer>> list;

    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 200, 600));
    }

    // func metod för att skapa och visa ett JFrame-objekt med specificerat innehåll
    private void createWindow(GeneralWordCounter counter, String title,
            int width, int height) {

        // note skapar JFrame och pane och ställer in rätt inställningar
        JFrame frame = new JFrame(title);
        Container pane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // note skapar en JList och definierar attributet list
        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(
                counter.getWordList());
        JList<Map.Entry<String, Integer>> list = new JList<Map.Entry<String, Integer>>(listModel);

        // note deklarerar fönster-objekt och lägger de i ett JPane-objekt
        JPanel buttonPanel = new JPanel();
        JButton button1 = new JButton("Alphabetic");
        JButton button2 = new JButton("Frequency");
        button1.addActionListener(e -> {

        button2.addActionListener(e -> {
            System.out.println("test2 clicked!");
        });

        // note lägger till alla komponenter i rätt objekt
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        pane.add(buttonPanel, BorderLayout.SOUTH);
        pane.add(new JScrollPane(list), BorderLayout.NORTH);

        // note förbereder fönstret för visning och sätter det som synligt
        frame.pack();
        frame.setVisible(true);
    }
}
