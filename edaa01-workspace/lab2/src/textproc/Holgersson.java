package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	// note skapar en vektor med landskap som ska matchas med texten
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		// note deklarerar scanners och sätter starttid
		long t0 = System.nanoTime();
		Scanner s1 = new Scanner(new File("edaa01-workspace/lab2/nilsholg.txt"));
		// alt Scanner s1 = new Scanner(new File("edaa01-workspace/lab2/test1.txt"));
		Scanner s2 = new Scanner(new File("edaa01-workspace/lab2/undantagsord.txt"));

		// note konfigurerar scanners för rätt inläsning
		s1.findWithinHorizon("\uFEFF", 1);
		s1.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		s2.useDelimiter("\\s");

		// note Skapar ett HashSet med alla undantagsord
		Set<String> stopWordSet = new HashSet<>();
		while (s2.hasNext()) {
			String word = s2.next().toLowerCase();
			stopWordSet.add(word);
		}

		// note skapar listan med WordCounters
		ArrayList<TextProcessor> pList = new ArrayList<>(
				Arrays.asList(
						new SingleWordCounter("nils"),
						new SingleWordCounter("norge"),
						new MultiWordCounter(REGIONS),
						new GeneralWordCounter(stopWordSet)));

		// note kör .process() på alla WordCounters
		while (s1.hasNext()) {
			String word = s1.next().toLowerCase();
			pList.forEach((p) -> {
				p.process(word);
			});
		}

		// note stänger scanners
		s1.close();
		s2.close();

		// note kör .report() på alla WordCounters
		pList.forEach((p) -> {
			p.report();
			System.out.println("    --------");
		});
		System.out.println("total runtime: " + ((System.nanoTime() - t0) / (1000000)) + "ms");
	}

	// note runtimes:293,285,297,284,287 -> Median:287ms

	@Deprecated
	// note metod för att köra main-metoden flera gånger och räkna ut mediantid
	private static float calculateRunTime(int nbrOfTimes) {
		try {
			int runNbr = nbrOfTimes;
			float[] runTimeArray = new float[runNbr];
			for (int i = 0; i < runNbr; i++) {
				long t0 = System.nanoTime();
				Holgersson.main(new String[0]);
				float runTime = (t0 - System.nanoTime()) / (10 ^ 6);
				runTimeArray[i] = runTime;
			}
			Arrays.sort(runTimeArray);
			float medianRunTime;
			if ((runNbr % 2) == 0) {
				medianRunTime = (runTimeArray[(nbrOfTimes / 2) - 1]
						+ runTimeArray[nbrOfTimes / 2])
						/ 2;
			} else {
				medianRunTime = runTimeArray[nbrOfTimes / 2];
			}
			return medianRunTime;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}