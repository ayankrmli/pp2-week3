package week03.geometry;

/**
 * A point in the 2D plane.
 */
public class Point {

    // Tolerance for comparing floats (floats are not exact, so never use == on results of arithmetic)
    static final float EPSILON = 1e-4f;

    private float x;
    private float y;

    // ----- Constructors -----
    public Point() {                    // default: the origin (0, 0)
        this(0f, 0f);
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {             // copy constructor
        this(p.x, p.y);
    }

    // ----- Accessors (getters) and mutators (setters) -----
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    // ----- Behavior -----
    // Moves the point by dX horizontally and dY vertically
    public void translate(float dX, float dY) {
        this.x += dX;
        this.y += dY;
    }

    // Euclidean distance: sqrt((x2-x1)^2 + (y2-y1)^2)
    public float distance(Point p) {
        float dx = p.x - this.x;
        float dy = p.y - this.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    // Two points are equal when both coordinates match (within EPSILON)
    public boolean equals(Point p) {
        if (p == null) {
            return false;
        }
        return Math.abs(this.x - p.x) < EPSILON
            && Math.abs(this.y - p.y) < EPSILON;
    }

    // Not in the diagram; added so points print nicely in tests
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
