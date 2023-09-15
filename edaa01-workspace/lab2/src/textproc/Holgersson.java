package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	// todox fixa så lista av TextProcessor-objekt funkar som input
	// todox varje gång ett nytt ord läses in ska process anropas på alla i listan
	// todox ändra sista TextProcessor objektet till multi och lägg i landskap

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		Scanner s1 = new Scanner(new File("edaa01-workspace/lab2/nilsholg.txt"));
		// alt Scanner s1 = new Scanner(new File("edaa01-workspace/lab2/test1.txt"));
		Scanner s2 = new Scanner(new File("edaa01-workspace/lab2/undantagsord.txt"));
		s1.findWithinHorizon("\uFEFF", 1);
		s1.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		s2.useDelimiter("\\s");

		// note Skapar ett HashSet med alla undantagsord
		Set<String> stopWordSet = new HashSet<>();
		while (s2.hasNext()) {
			String word = s2.next().toLowerCase();
			stopWordSet.add(word);
		}

		ArrayList<TextProcessor> pList = new ArrayList<>(
				Arrays.asList(
						new SingleWordCounter("nils"),
						new SingleWordCounter("norge"),
						new MultiWordCounter(REGIONS),
						new GeneralWordCounter(stopWordSet)));

		while (s1.hasNext()) {
			String word = s1.next().toLowerCase();
			pList.forEach((p) -> {
				p.process(word);
			});
		}

		s1.close();
		s2.close();

		pList.forEach((p) -> {
			p.report();
		});
		System.out.println("total runtime: " + ((System.nanoTime() - t0) / (1000000)) + "ms");
	}
	// note runtimes: 333,319,301,306,416 -> Median: 319ms

	@Deprecated
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