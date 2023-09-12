package game;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Font;

class UserInterface {

    /** Visar en dialogruta med texten msg. */
    static void printMessage(String msg) {
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null, msg);

    }

    /**
     * Visar en dialogruta med två knappval, yesOption och noOption.
     * ovanför knappval står texten msg. Returnar true om yesOption
     * valdes av användare och false alla andra gånger.
     */
    static boolean askForBool(String msg, String yesOption, String noOption) {
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
        int choice = JOptionPane.showOptionDialog(
                null,
                msg,
                "Game Options",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[] { yesOption, noOption },
                yesOption);

        if (choice == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Visar en dialogruta med texten msg och och läser in ett positivt heltal. Om
     * användaren skriver något som inte kan tolkas som ett positivt heltal ska -1
     * returneras. Om användaren klickar på "Avbryt" ska -2 returneras.
     */
    // todox enlarge font size and window size
    // todo add more intuitive way of choosing number of pins to take
    static int askForInt(String msg) {
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
        String inputString = JOptionPane.showInputDialog(null, msg);
        int parsedInput;
        // Try-Catch för att identifiera och hantera fel när något annat än int valdes
        // Returnerar -1 ifall inputen inte kan göras om till en int.
        try {
            if (inputString == null) {
                return -2;
            }
            parsedInput = Integer.parseInt(inputString);
            return parsedInput;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Visar en dialogruta med texten msg. Returnar användarens input som en String
     * om användaren trycker avbryt returnas null.
     */
    static String askForString(String msg) {
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
        String inputString = JOptionPane.showInputDialog(null, msg);
        if (inputString == null) {
            return null;
        }
        return inputString;
    }
}
