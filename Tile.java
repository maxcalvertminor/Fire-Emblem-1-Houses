public class Tile {
    public Unit unitOnTile;
    public Vector2 screenPosition;
    public Vector2 gridPosition;

    public Tile(Vector2 sPos, Vector2 gPos) {
        screenPosition = sPos;
        gridPosition = gPos;
    }
}
