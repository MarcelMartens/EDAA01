package game;

import java.util.Random;

/**
 * subklass till Player. Vid skapelse väljs om den spelar smart eller normalt
 * Normal väljer slumpmässiga tal mellan 1 och 2, Smart försöker alltid ge
 * mänskliga spelaren ett jämnt antal pins att välja ifrån förutom då
 * totala antalet är 3, då väljer den alltid 2.
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
