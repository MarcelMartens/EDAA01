package mountain;

public class Side {
    private Point p1;
    private Point p2;

    public Side(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean isEqual(Side other) {
        return (this.p1 == other.p1 && this.p2 == other.p2 || this.p1 == other.p2 && this.p2 == other.p1);
    }

}
