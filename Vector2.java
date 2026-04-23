public class Vector2 {
    // 2D position/vector coordinate object
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
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
