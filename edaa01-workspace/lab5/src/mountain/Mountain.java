package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
    private HashMap<Side, Point> sides = new HashMap<>();
    private Point p1;
    private Point p2;
    private Point p3;
    private double dev;

    public Mountain(Point p1, Point p2, Point p3, double dev) {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;
        sides.put(new Side(p1, p2), halfWay(p1, p2, dev));
        sides.put(new Side(p2, p3), halfWay(p2, p3, dev));
        sides.put(new Side(p3, p1), halfWay(p3, p1, dev));
    }

    @Override
    public String getTitle() {
        return "Mountain fractal";
    }

    @Override
    public void draw(TurtleGraphics turtle) {
        turtle.moveTo(p3.getX(), p3.getY());
        fractalTriangle(turtle, order, p1, p2, p3, dev);
    }

    private void fractalTriangle(TurtleGraphics turtle, int order, Point point1, Point point2, Point point3,
            double dev) {
        if (order == 0) {
            turtle.moveTo(point3.getX(), point3.getY());
            turtle.forwardTo(point1.getX(), point1.getY());
            turtle.forwardTo(point2.getX(), point2.getY());
            turtle.forwardTo(point3.getX(), point3.getY());
        } else {
            Point hp1 = halfWay(point1, point2, dev);
            Point hp2 = halfWay(point2, point3, dev);
            Point hp3 = halfWay(point1, point3, dev);

            fractalTriangle(turtle, order - 1, point1, hp1, hp3, dev / 2);
            fractalTriangle(turtle, order - 1, hp1, point2, hp2, dev / 2);
            fractalTriangle(turtle, order - 1, hp3, hp2, point3, dev / 2);
            fractalTriangle(turtle, order - 1, hp1, hp2, hp3, dev / 2);
        }
    }

    private Point halfWay(Point point1, Point point2, double dev) {
        Side side = sides.keySet().stream().filter(s -> s.equals(new Side(point1, point2))).findFirst().orElse(null);
        if (side != null) {
            return sides.remove(side);
        }

        Point halfWay = new Point((point1.getX() + point2.getX()) / 2,
                ((point1.getY() + point2.getY()) / 2) + (int) RandomUtilities.randFunc(dev));
        this.sides.put(new Side(point1, point2), halfWay);
        return halfWay;
    }
}
