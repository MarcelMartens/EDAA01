package game;

/** subklass till Player. Motsvarar mänskliga spelaren */
class HumanPlayer extends Player {
    HumanPlayer(String ID) {
        super(ID);
    }

    /**
     * metod för att låta användaren ta ett antal pins med getInt().
     * Anropar sedan board.takePins för det valda antalet
     */
    int takePins(Board board) {
        int nbrOfPins = getInt(board);
        while (nbrOfPins <= 0) {
            nbrOfPins = getInt(board);
        }
        board.takePins(nbrOfPins);
        return nbrOfPins;
    }

    /**
     * metod för att låta användaren välja mellan 1 och 2.
     * Om input ej kan göras om till int, om det ej är 1/2 eller om
     * användaren tryckte på cancel visas passande felmeddelande och
     * användaren får välja igen
     */
    int getInt(Board board) {
        int input = UserInterface
                .askForInt("Input a positive integer, 1 or 2\nRemaining pins: " + board.getTotPins());
        if (input == -1) {
            UserInterface.printMessage("input cannot be parsed as a positive integer");
        } else if (input == -2) {
            UserInterface.printMessage("user pressed declined");
        } else if (!(input == 1 || input == 2)) {
            UserInterface.printMessage("Input does not match 1 or 2");
        } else {
            return input;
        }
        return 0;
    }
}
