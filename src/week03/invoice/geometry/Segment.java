package week03.geometry;

/**
 * A line segment defined by two end points p1 and p2.
 */
public class Segment {

    private Point p1;
    private Point p2;

    // ----- Constructors -----
    // The segment keeps its OWN copies of the points (via the copy constructor),
    // so moving the segment never moves the caller's Point objects.
    public Segment(Point p1, Point p2) {
        this.p1 = new Point(p1);
        this.p2 = new Point(p2);
    }

    public Segment(float x1, float y1, float x2, float y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    // ----- Accessors / mutators -----
    public Point getP1() { return p1; }
    public void setP1(Point p) { this.p1 = new Point(p); }

    public Point getP2() { return p2; }
    public void setP2(Point p) { this.p2 = new Point(p); }

    // ----- Behavior -----
    // Moves BOTH end points by (dX, dY)
    public void translate(float dX, float dY) {
        p1.translate(dX, dY);
        p2.translate(dX, dY);
    }

    public float length() {
        return p1.distance(p2);
    }

    // Equal if they have the same end points (in either order: AB is the same segment as BA)
    public boolean equals(Segment s) {
        if (s == null) {
            return false;
        }
        return (p1.equals(s.p1) && p2.equals(s.p2))
            || (p1.equals(s.p2) && p2.equals(s.p1));
    }

    // Slope k in y = k*x + b.
    // Vertical line -> +Infinity; both ends identical -> NaN (no line is defined)
    public float getSlope() {
        float dx = p2.getX() - p1.getX();
        float dy = p2.getY() - p1.getY();
        if (Math.abs(dx) < Point.EPSILON) {
            return (Math.abs(dy) < Point.EPSILON) ? Float.NaN : Float.POSITIVE_INFINITY;
        }
        return dy / dx;
    }

    // Intercept b in y = k*x + b, from b = y1 - k*x1.
    // Vertical line (or degenerate segment) has no intercept -> NaN
    public float getIntercept() {
        float k = getSlope();
        if (Float.isNaN(k) || Float.isInfinite(k)) {
            return Float.NaN;
        }
        return p1.getY() - k * p1.getX();
    }

    // True if p lies on the infinite line through p1 and p2 (satisfies y = k*x + b)
    public boolean isOnLine(Point p) {
        if (p == null) {
            return false;
        }
        float k = getSlope();
        if (Float.isNaN(k)) {                       // p1 == p2: only that point
            return p.equals(p1);
        }
        if (Float.isInfinite(k)) {                  // vertical line: x = constant
            return Math.abs(p.getX() - p1.getX()) < Point.EPSILON;
        }
        float expectedY = k * p.getX() + getIntercept();
        return Math.abs(p.getY() - expectedY) < Point.EPSILON;
    }

    // True if p is on the line AND between the two end points
    public boolean isOnSegment(Point p) {
        if (!isOnLine(p)) {
            return false;
        }
        boolean xInRange = p.getX() >= Math.min(p1.getX(), p2.getX()) - Point.EPSILON
                        && p.getX() <= Math.max(p1.getX(), p2.getX()) + Point.EPSILON;
        boolean yInRange = p.getY() >= Math.min(p1.getY(), p2.getY()) - Point.EPSILON
                        && p.getY() <= Math.max(p1.getY(), p2.getY()) + Point.EPSILON;
        return xInRange && yInRange;
    }

    @Override
    public String toString() {
        return "[" + p1 + " -> " + p2 + "]";
    }
}
