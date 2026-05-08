public class Vector2 {
    // 2D position/vector coordinate object
    // use a Vector2 to represent any two coordinate thing, mainly a point on the tileGrid or frame space, or a two dimensional vector on the tileGrid or frame space.

    public double x;
    public double y;

    public double sqrMagnitude;

    public static Vector2 zero = new Vector2(0, 0);

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
        sqrMagnitude = Math.pow(x, 2) + Math.pow(y, 2);
    }

    public Vector2() {
        x = 0;
        y = 0;
    }

    // adding two vectors together
    public Vector2 Add(Vector2 addend) {
        Vector2 temp = new Vector2();
        temp.x = x + addend.x;
        temp.y = y + addend.y;
        return temp;
    }

    // multiply a vector by a constant multiplier
    public Vector2 ScaleFactor(double scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
        return this;
    }

    public boolean Equals(Vector2 vect2) {
        boolean xtrue = false, ytrue = false;
        if(x == vect2.x) {xtrue = true;}
        if(y == vect2.y) {ytrue = true;}
        return xtrue && ytrue;
    }
}
