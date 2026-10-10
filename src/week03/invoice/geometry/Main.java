package week03.geometry;

public class Main {

    private static int failures = 0;

    // Prints PASS/FAIL for one check
    private static void check(String name, boolean condition) {
        System.out.println((condition ? "PASS  " : "FAIL  ") + name);
        if (!condition) failures++;
    }

    private static boolean close(float a, float b) {
        return Math.abs(a - b) < 1e-3f;
    }

    public static void main(String[] args) {

        // ===== Point =====
        System.out.println("=== Point ===");
        Point a = new Point();                  // (0, 0)
        Point b = new Point(3, 4);
        Point c = new Point(b);                 // copy of b

        check("default constructor gives (0,0)", a.getX() == 0 && a.getY() == 0);
        check("getX/getY", b.getX() == 3 && b.getY() == 4);

        c.setX(10);
        c.setY(20);
        check("setX/setY change the copy", c.getX() == 10 && c.getY() == 20);
        check("copy constructor: b is unchanged", b.getX() == 3 && b.getY() == 4);

        check("distance (0,0)-(3,4) = 5", close(a.distance(b), 5f));
        check("distance is symmetric", close(a.distance(b), b.distance(a)));

        Point t = new Point(1, 2);
        t.translate(2, 3);
        check("translate (1,2) by (2,3) = (3,5)", t.getX() == 3 && t.getY() == 5);

        check("equals: same coordinates", new Point(3, 4).equals(b));
        check("equals: different coordinates", !a.equals(b));
        check("equals: null is false", !a.equals(null));

        // ===== Segment =====
        System.out.println("\n=== Segment ===");
        Segment s = new Segment(0, 1, 4, 9);    // line y = 2x + 1
        System.out.println("s = " + s);

        check("length of (0,1)-(4,9) = sqrt(80)", close(s.length(), (float) Math.sqrt(80)));
        check("slope = 2", close(s.getSlope(), 2f));
        check("intercept = 1", close(s.getIntercept(), 1f));

        check("isOnLine (2,5)", s.isOnLine(new Point(2, 5)));
        check("isOnLine (2,6) is false", !s.isOnLine(new Point(2, 6)));
        check("isOnLine (10,21): on the line...", s.isOnLine(new Point(10, 21)));
        check("isOnSegment (10,21): ...but beyond p2", !s.isOnSegment(new Point(10, 21)));
        check("isOnSegment (2,5): between ends", s.isOnSegment(new Point(2, 5)));
        check("isOnSegment (4,9): the end point itself", s.isOnSegment(new Point(4, 9)));
        check("isOnSegment (-1,-1): on the line, before p1", !s.isOnSegment(new Point(-1, -1)));

        // Constructor with Points + copies
        Point q1 = new Point(0, 1);
        Point q2 = new Point(4, 9);
        Segment s2 = new Segment(q1, q2);
        check("equals: same ends", s.equals(s2));
        check("equals: reversed ends", s.equals(new Segment(q2, q1)));
        check("equals: different segment", !s.equals(new Segment(0, 0, 1, 1)));

        // Getters / setters
        s2.setP2(new Point(6, 13));             // (6,13) is also on y = 2x + 1
        check("setP2 / getP2", s2.getP2().equals(new Point(6, 13)));
        check("original point q2 untouched by setP2", q2.equals(new Point(4, 9)));
        s2.setP1(new Point(1, 3));
        check("setP1 / getP1", s2.getP1().equals(new Point(1, 3)));

        // translate: y = 2x + 1 shifted by (1,1) -> (1,2)-(5,10), intercept becomes 0
        s.translate(1, 1);
        System.out.println("s after translate(1,1) = " + s);
        check("translate moves p1", s.getP1().equals(new Point(1, 2)));
        check("translate moves p2", s.getP2().equals(new Point(5, 10)));
        check("new intercept = 0", close(s.getIntercept(), 0f));
        check("slope unchanged = 2", close(s.getSlope(), 2f));
        check("q1 (caller's point) NOT moved by translate", q1.equals(new Point(0, 1)));

        // Vertical segment: slope is infinite, no intercept
        Segment v = new Segment(3, 0, 3, 5);
        check("vertical: slope is Infinity", Float.isInfinite(v.getSlope()));
        check("vertical: intercept is NaN", Float.isNaN(v.getIntercept()));
        check("vertical: (3,9) on line", v.isOnLine(new Point(3, 9)));
        check("vertical: (4,1) not on line", !v.isOnLine(new Point(4, 1)));
        check("vertical: (3,2) on segment", v.isOnSegment(new Point(3, 2)));
        check("vertical: (3,9) not on segment", !v.isOnSegment(new Point(3, 9)));

        // Horizontal segment: slope 0
        Segment h = new Segment(0, 2, 5, 2);
        check("horizontal: slope 0, intercept 2", close(h.getSlope(), 0f) && close(h.getIntercept(), 2f));

        System.out.println("\n" + (failures == 0 ? "All checks passed." : failures + " check(s) FAILED."));
    }
}
