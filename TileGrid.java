public class TileGrid {
    // how big the grid is in screen size
    public Vector2 worldSize;

    // center of grid in screen coords
    public Vector2 gridCenter;

    // (not set by user) amount of tiles per row
    public int gridSizeX;

    // (not set by user) amount of tiles per column
    public int gridSizeY;

    // radius of one tile in screen coords
    public int radius;

    // diameter of one tile in screen coords
    public int diameter;

    // grid of tiles 
    public Tile[][] grid;

    public TileGrid(Vector2 nWorldSize, Vector2 center, int nRadius) {
        worldSize = nWorldSize;
        gridCenter = center;
        radius = nRadius;
        diameter *= radius;

        // determining amount of tiles per row/column
        gridSizeX = (int)worldSize.x / diameter;
        gridSizeY = (int)worldSize.y / diameter;

        grid = new Tile[gridSizeX][gridSizeY];

        // determining position of the bottom left of the grid in screen coords
        Vector2 worldBottomLeft = gridCenter;
        worldBottomLeft.x -= worldSize.x / 2;
        worldBottomLeft.y -= worldSize.y / 2;

        // setting up grid
        for(int r = 0; r < gridSizeX; r++) {
            for(int c = 0; c < gridSizeY; c++) {
                // creating and setting position of tiles
                grid[r][c] = new Tile(new Vector2(worldBottomLeft.x + (diameter * r) + radius, worldBottomLeft.y + (diameter * c) + radius));
            }
        }
    }

    public Tile ScreenCoordToTile(Vector2 screenCoord) {
        int r = (int)(screenCoord.x / worldSize.x * gridSizeX);
        int c = (int)(screenCoord.y / worldSize.y * gridSizeY);
        return grid[r][c];
    }
}
