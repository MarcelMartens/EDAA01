package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
    private Point[] p;
    private Point p1;
    private Point p2;
    private Point p3;

    public Mountain(Point point1, Point point2, Point point3) {
        super();
        this.p1 = point1;
        this.p2 = point2;
        this.p3 = point3;
        this.p = new Point[] { point1, point2, point3 };
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    @Override
    public void draw(TurtleGraphics turtle) {
        turtle.moveTo(p[0].getX(), p[0].getY());
        fractalLine(turtle, order, p);
    }

    private void fractalLine(TurtleGraphics turtle, int order, Point[] p) {
        if (order == 0) {
            turtle.forwardTo(p[1].getX(), p[1].getY());
            turtle.forwardTo(p[2].getX(), p[2].getY());
            turtle.forwardTo(p[0].getX(), p[0].getY());
        } else {
            Point[] halfway = new Point[3];
            fractalLine(turtle, order - 1, p);
        }
    }

}
