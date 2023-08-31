package game;

import java.util.Random;

/**
 * subklass till Player. Vid skapelse väljs om den spelar smart eller normalt
 * Normal väljer slumpmässiga tal mellan 1 och 2, Smart försöker alltid ge
 * mänskliga spelaren ett jämnt antal pins att välja ifrån förutom då
 * totala antalet är 3, då väljer den alltid 2.
 */
class ComputerPlayer extends Player {
    boolean smartOrNormal;

    ComputerPlayer(String ID, boolean smartOrNot) {
        super(ID);
        this.smartOrNormal = smartOrNot;
    }

    /**
     * huvudmetod för att välja antal pins att ta. Beroende på
     * objektets variabel @smartOrNormal spelar den antingen smart eller normalt
     */
    int takePins(Board board) {
        if (this.smartOrNormal) {
            return normalTakePins(board);
        } else {
            return smartTakePins(board);
        }
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

    /**
     * väljer så att antalet alltid blir jämnt, förutom om totalt antal är 3.
     * Om totalt antal pins är 3 väljer den alltid att ta 2 st
     */
    int smartTakePins(Board board) {
        int totPins = board.getTotPins();
        int nbrOfPins;
        if (((totPins % 2) == 0 || totPins == 3) && (totPins != 2)) {
            nbrOfPins = 2;
        } else {
            nbrOfPins = 1;
        }
        board.takePins(nbrOfPins);
        return nbrOfPins;
    }
}
