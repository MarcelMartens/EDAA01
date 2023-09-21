package textproc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.awt.Dimension;

// todo JPane-Controller
// (x) knappar: sortera alfabetisk, sortera antal
// (x) sökfält
// (x) huvud-doc för listan med ord
// (x) fixa import från annat projekt
// changed lade till "lab2/" i .classpath för att få bort felet.
// (x) titel på fönster
// (x) update-metod?
// (x) skapa en JList och knyt den till en listmodell (SortedListModel)
// (x) skapa JScrollPane, ska innehålla listmodellen och lägg till i fönstret
// () lägg till texten "sort by:" till vänster om knapparna
// (x) fixa bugg där listan ändras under tiden den ittererar.
// (x) I ditt användargränssnitt kan man skriva in ett ord att söka efter. 
/// Om användaren rå- kar inleda eller avsluta ordet med ett eller fler 
/// mellanslag fungerar inte sökningen. Om användaren på samma sätt råkar 
/// skriva in versaler fungerar inte heller sökningen.
/// Ändra programmet så att sökningen fungerar, även om det inmatade ordet 
/// börjar/slutar på mellanslag eller innehåller versaler.
/// Du har nytta av ett par lämpliga metoder i klassen String.
// (x) Gör så att knappen ”Find” aktiveras (trycks) automatiskt när man trycker Return.
// (x) När man söker efter ett ord som inte finns i boken vore det bra med 
/// en ruta som meddelar användaren detta. Använd klassen JOptionPane 
/// för att visa en sådan ruta.
// (x) Lägg till så att sökta ord blir highlightade efter sök (som ctrl+f)
// () Programmet vore mer användbart om man kunde välja vilken textfil som 
/// ska analyseras. Låt användaren välja en fil att analysera. 
/// Använd klassen JFileChooser.
// () För de två sorteringsknapparna passar det bra att använda 
/// s.k. radioknappar. En sådan markeras när den är intryckt, och bara en knapp 
/// i samma grupp kan vara intryckt i taget. 

// note klass för att kontrollera J-Objekt
public class BookReaderController {
    // alt private JList<Map.Entry<String, Integer>> list;

    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 400, 600));
    }

    // func metod för att skapa och visa ett JFrame-objekt med specificerat innehåll
    private void createWindow(GeneralWordCounter counter, String title,
            int width, int height) {

        // note skapar JFrame och pane och ställer in rätt inställningar
        JFrame frame = new JFrame(title);
        Container pane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // note skapar en List, Jlist, SortedListModel och tar bort alla ord som börjar
        /// med en siffra
        // todo kolla ifall det kan skapa fel att inte kolla om stringen är null
        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(counter.getWordList());
        wordList.removeIf(e -> Character.isDigit(e.getKey().charAt(0)));
        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(
                wordList);
        JList<Map.Entry<String, Integer>> list = new JList<Map.Entry<String, Integer>>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        // note deklarerar fönster-objekt och lägger de i ett JPane-objekt
        JPanel bottomPanel = new JPanel();
        JButton sortButton1 = new JButton("Alphabetic");
        JButton sortButton2 = new JButton("Frequency");
        JButton searchButton = new JButton("find");
        JTextField textField = new JTextField(10);
        JLabel bottomText = new JLabel("sort by:");

        // note definierar händelser vid knapptryck
        sortButton1.addActionListener(e -> listModel.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey())));
        sortButton2.addActionListener(e -> listModel.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())));
        searchButton.addActionListener(e -> {
            String searchTerm = textField.getText().trim().toLowerCase();
            for (int i = 0; i < listModel.getSize(); i++) {
                if (listModel.getElementAt(i).getKey().equals(searchTerm)) {
                    list.ensureIndexIsVisible(i);
                    list.setSelectedIndex(i);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame,
                    "The word does not appear in the text or is defined as ignored.");
        });

        // note definierar vad som händer när enter trycks
        /// sökrutan fokuseras eller inte
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }
        });
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if ("Search...".equals(textField.getText())) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Search...");
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        // note lägger till alla komponenter i rätt objekt
        bottomPanel.add(sortButton1);
        bottomPanel.add(sortButton2);
        bottomPanel.add(textField);
        bottomPanel.add(searchButton);

        pane.add(bottomPanel, BorderLayout.SOUTH);
        pane.add(scrollPane, BorderLayout.CENTER);

        // note ställer in utseendet av fönstret inför körning
        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
        // alt int windowWidth = (res.width / 3);
        // alt int windowHeight = ((res.height * 2) / 3);
        // alt int windowX = ((res.width / 2) - (windowWidth / 2));
        // alt int windowY = ((res.height / 2) - (windowHeight / 2));
        textField.setText("Search...");
        textField.setForeground(Color.GRAY);
        frame.setBounds(((res.width / 2) - ((res.width / 3) / 2)),
                ((res.height / 2) - (((res.height * 2) / 3) / 2)),
                (res.width / 3),
                ((res.height * 2) / 3));

        // note förbereder fönstret för visning och sätter det som synligt
        // frame.pack();
        frame.setVisible(true);
    }
}
