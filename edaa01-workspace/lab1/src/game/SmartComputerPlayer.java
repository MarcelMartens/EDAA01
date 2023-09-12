package game;

public class SmartComputerPlayer extends Player {
    SmartComputerPlayer(String ID) {
        super(ID);
    }

    /**
     * väljer så att antalet alltid blir jämnt, förutom om totalt antal är 3.
     * Om totalt antal pins är 3 väljer den alltid att ta 2 st
     */
    int takePins(Board board) {
        int totPins = board.getTotPins();
        int nbrOfPins;
        if ((totPins - 1) == 3) {
            nbrOfPins = 1;
        } else {
            nbrOfPins = 2;
        }
        board.takePins(nbrOfPins);
        return nbrOfPins;
    }
}
