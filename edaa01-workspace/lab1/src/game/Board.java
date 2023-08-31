package game;

class Board {
	private int totPins;

	Board(int nbrOfPins) {
		this.totPins = nbrOfPins;
	}

	void setUp(int nbrOfPins) {
		this.totPins = nbrOfPins;
	}

	void takePins(int pinsToTake) {
		this.totPins -= pinsToTake;
	}

	int getTotPins() {
		return this.totPins;
	}

}
