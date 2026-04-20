public class Vector2 {
    // 2D position/vector coordinate object
    public double x;
    public double y;

    public Vector2(double nx, double ny) {
        x = nx;
        y = ny;
    }

    public Vector2() {
        x = 0;
        y = 0;
    }

    // adding two vectors together
    public static Vector2 Add(Vector2 first, Vector2 second) {
        Vector2 temp = new Vector2();
        temp.x = first.x + second.x;
        temp.y = first.y + second.y;
        return temp;
    }

    // multiply a vector by a constant multiplier
    public Vector2 ScaleFactor(double scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
        return this;
    }
}
