import javax.swing.*;

public class Tile extends JLabel{
    public Unit unitOnTile;
    public Vector2 framePosition;
    public Vector2 gridPosition;
    public TileGrid grid;

    public Tile(Vector2 sPos, Vector2 gPos, TileGrid g, ImageIcon icon) {
        framePosition = sPos;
        gridPosition = gPos;
        grid = g;

        // setting up UI
        grid.frame.add(this);
        setBounds((int)framePosition.x - grid.radius, (int)framePosition.y - grid.radius, grid.diameter, grid.diameter);
        //this.setText("tile");
        this.setIcon(icon);

    }

    public boolean Equals(Tile otherTile) {
        boolean temp = true;
        if(otherTile.gridPosition.x != gridPosition.x) {temp = false;}
        if(otherTile.gridPosition.y != gridPosition.y) {temp = false;}
        return temp;
    }
}
