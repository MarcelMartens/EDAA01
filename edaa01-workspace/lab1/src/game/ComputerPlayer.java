package game;

import java.util.Random;

/**
 * subklass till Player. Väljer slumpmässiga tal mellan 1 och 2 och tar det
 * antalet pins från brädet
 */
class ComputerPlayer extends Player {
    ComputerPlayer(String ID) {
        super(ID);
    }

    /**
     * Väljer slumpmässigt tal 1 eller 2 och callar board.takePins med den summan
     */
    int normalTakePins(Board board) {
        Random r = new Random();
        int nbrOfPins = r.nextInt(2) + 1;
        board.takePins(nbrOfPins);
        return nbrOfPins;
    }
}
