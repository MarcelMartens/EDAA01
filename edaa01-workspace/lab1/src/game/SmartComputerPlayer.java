package game;

/** subklass till Player, en bättre version av ComputerPlayer */
public class SmartComputerPlayer extends Player {
    SmartComputerPlayer(String ID) {
        super(ID);
    }

    /**
     * metod för att ta pins med smartare val än att ta slumpmässiga tal.
     * tar 1 om totala antalet pins minus 1 är jämnt delbart med 3,
     * om det ej stämmer tar SmartComputerPlayer alltid 2.
     */
    int takePins(Board board) {
        int totPins = board.getTotPins();
        int nbrOfPins;
        if (((totPins - 1) % 3) == 0) {
            nbrOfPins = 1;
        } else {
            nbrOfPins = 2;
        }
        board.takePins(nbrOfPins);
        return nbrOfPins;
    }
}
