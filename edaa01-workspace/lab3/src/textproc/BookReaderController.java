package textproc;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import java.awt.Container;

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
// () 

// bug metod från annat projekt kanske inte är bäst att lösa genom
/// att ändra .classpath, kolla så det inte skapar kaos me han
// note klass för att kontrollera J-Objekt
public class BookReaderController {

    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
    }

    // note metod för att skapa och visa ett JFrame-objekt
    private void createWindow(GeneralWordCounter counter, String title,
            int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        // pane är en behållarkomponent till vilken de övriga komponenterna
        // (listvy, knappar etc.) ska läggas till.
        frame.pack();
        frame.setVisible(true);
    }
}
