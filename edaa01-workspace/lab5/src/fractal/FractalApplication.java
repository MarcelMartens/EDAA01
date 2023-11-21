package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Mountain(new Point(100, 20), new Point(20, 580), new Point(580, 480), 30);
		fractals[1] = new Koch(300);
		new FractalView(fractals, "Fraktaler", 600, 600);
	}
}
