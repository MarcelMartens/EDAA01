package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Mountain(new Point(200, 100), new Point(200, 450), new Point(400, 330), 23);
		fractals[1] = new Koch(300);
		new FractalView(fractals, "Fraktaler", 600, 600);
	}
}
