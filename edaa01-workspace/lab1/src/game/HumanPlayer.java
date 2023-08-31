package game;

class HumanPlayer extends Player {
    HumanPlayer(String ID) {
        super(ID);
    }

    int takePins(Board board) {
        int nbrOfPins = getInt();
        board.takePins(nbrOfPins);
        return nbrOfPins;
    }

    int getInt() {
        int input = UserInterface.askForInt("skriv ett positivt heltal, 1 eller 2");
        if (input == -1) {
            UserInterface.printMessage("kan ej tolkas som positivt heltal");
        }
        if (input == -2) {
            UserInterface.printMessage("Användaren tryckte på avbryt");
        }
        if (input == -3) {
            UserInterface.printMessage("Angivet tal är inte 1 eller 2");
        } else {
            return input;
        }
        return 0;
    }
}
