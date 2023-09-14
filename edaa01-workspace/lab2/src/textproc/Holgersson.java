package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	// todox fixa så lista av TextProcessor-objekt funkar som input
	// todox varje gång ett nytt ord läses in ska process anropas på alla i listan
	// todo ändra sista TextProcessor objektet till multi och lägg i landskap

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<TextProcessor> pList = new ArrayList<>(
				Arrays.asList(
						new SingleWordCounter("nils"),
						new SingleWordCounter("norge"),
						new SingleWordCounter("borta")));

		Scanner s = new Scanner(new File("edaa01-workspace/lab2/nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			pList.forEach((p) -> {
				p.process(word);
			});
		}

		s.close();
		pList.forEach((p) -> {
			p.report();
		});
	}
}