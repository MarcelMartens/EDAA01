package textproc;

public class SingleWordCounter implements TextProcessor {

	// note attribut
	private String word;
	private int n;

	// note konstruktor
	public SingleWordCounter(String word) {
		this.word = word;
		n = 0;
	}

	// fixed ändrade == operanden till String.equals(), == kollar efter samma objekt
	@Override
	// note om w är samma som ordet ökar n med 1
	public void process(String w) {
		if (w.equals(this.word)) {
			this.n++;
		}
	}

	@Override
	// note skriver ut ordet och antal förekomster
	public void report() {
		System.out.println(word + ": " + n);
	}

}
