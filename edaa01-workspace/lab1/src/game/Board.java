package game;

public class Board {
	private int totPins;

	public Board(int nbrOfPins) {
		totPins = nbrOfPins;
	}

	public void setUp(int i) {
	}

	public void takePins(int pinsToTake) {
		this.totPins -= pinsToTake;
	}

	public int getTotPins() {
		return this.totPins;
	}

}
