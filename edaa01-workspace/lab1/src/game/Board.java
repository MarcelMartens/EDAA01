package game;

/**
 * Klass för att hålla koll på antal pins
 * Hanterar även samspelet mellan spelares val och spelplanen
 */
class Board {
	private int totPins;

	Board(int nbrOfPins) {
		this.totPins = nbrOfPins;
	}

	/** Väljer nytt antal pins och sätter ändrar nuvarande antal till @nbrOfPins */
	void setUp(int nbrOfPins) {
		this.totPins = nbrOfPins;
	}

	/** Tar bort antalet @pinsToTake från spelets totala antal pins */
	void takePins(int pinsToTake) {
		this.totPins -= pinsToTake;
	}

	/** returnar totalt antal pins i spelet */
	int getTotPins() {
		return this.totPins;
	}

}
