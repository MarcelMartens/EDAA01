package textproc;

public class SingleWordCounter implements TextProcessor {
	private String word;
	private int n;

	public SingleWordCounter(String word) {
		this.word = word;
		n = 0;
	}

	// fixed ändrade == operanden till String.equals(), == kollar efter samma objekt
	@Override
	public void process(String w) {
		if (w.equals(this.word)) {
			this.n++;
		}
	}

	@Override
	public void report() {
		System.out.println(word + ": " + n);
	}

}
