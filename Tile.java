public class Tile {
    public Unit unitOnTile;
    public Vector2 framePosition;
    public Vector2 gridPosition;

    public Tile(Vector2 sPos, Vector2 gPos) {
        framePosition = sPos;
        gridPosition = gPos;
    }

    public boolean Equals(Tile otherTile) {
        boolean temp = true;
        if(otherTile.gridPosition.x != gridPosition.x) {temp = false;}
        if(otherTile.gridPosition.y != gridPosition.y) {temp = false;}
        return temp;
    }

    //-------------------------------------------------
    // INCOMPLETE
    // like unit, needs a jLabel to show the tile on the 
}
