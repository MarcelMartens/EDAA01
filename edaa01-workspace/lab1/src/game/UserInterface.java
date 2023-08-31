package game;

import javax.swing.JOptionPane;

class UserInterface {
    /** Visar en dialogruta med texten msg. */
    static void printMessage(String msg) {
        // din egen kod
        JOptionPane.showMessageDialog(null, msg);

    }

    /**
     * Visar en dialogruta med texten msg och och läser in ett positivt heltal. Om
     * användaren skriver något som inte kan tolkas som ett positivt heltal ska -1
     * returneras. Om användaren klickar på "Avbryt" ska -2 returneras.
     * Om användaren skriver in ett positivt heltal som inte
     * är 1 eller 2 returneras -3
     */
    static int askForInt(String msg) {
        // din egen kod
        String inputString = JOptionPane.showInputDialog(null, msg);
        int parsedInput;
        try {
            if (inputString == null) {
                return -2;
            }
            parsedInput = Integer.parseInt(inputString);
            if (parsedInput == 2 || parsedInput == 1) {
                return parsedInput;
            } else {
                return -3;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    static String askForString(String msg) {
        String inputString = JOptionPane.showInputDialog(null, msg);
        if (inputString == null) {
            return null;
        }
        return inputString;
    }
}
