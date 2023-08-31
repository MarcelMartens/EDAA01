package game;

import java.util.Random;

class ComputerPlayer extends Player {
    ComputerPlayer(String ID) {
        super(ID);
    }

    int takePins(Board board) {
        Random r = new Random();
        int nbrOfPins = r.nextInt(2) + 1;
        board.takePins(nbrOfPins);
        return nbrOfPins;
    }
}
