import java.util.ArrayList;

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
                grid[r][c] = new Tile(new Vector2(worldBottomLeft.x + (diameter * r) + radius, worldBottomLeft.y + (diameter * c) + radius), new Vector2(r, c));
            }
        }
    }

    public Tile FrameCoordToTile(Vector2 frameCoord) {
        int r = (int)(frameCoord.x / worldSize.x * gridSizeX);
        int c = (int)(frameCoord.y / worldSize.y * gridSizeY);
        return grid[r][c];
    }

    public Tile GridCoordToTile(int x, int y) {
        return grid[x][y];
    }

    public Tile GridCoordToTile(Vector2 coords) {
        return grid[(int)coords.x][(int)coords.y];
    }

    public ArrayList<Tile> GetTilesWithinDistance(int distance, Tile startTile) {
        // basic function:
        // 1. start with some tiles that are for sure in range in openset
        // 2. for every tile in openset, extend one tile in every cardinal direction, and add them to queuedset
        // 3. switch tiles in openset to closedset, switch tiles in queuedset to openset
        // 4. repeat steps 2 and 3 a distance amount of times

        // set that the function will check next
        ArrayList<Tile> openSet = new ArrayList<Tile>();

        // tiles we already have checked
        ArrayList<Tile> closedSet = new ArrayList<Tile>();

        // tiles we will check next loop
        ArrayList<Tile> queuedSet = new ArrayList<Tile>();

        openSet.add(startTile);

        // use distance to loop function
        // limiting this by distance will only repeat the inner breadth first function a distance amount of times
        for(int i = 0; i < distance; i++) {
            
            // loop to check every index in openSet
            for(int o = 0; o < openSet.size(); o++) {

                boolean isInvalid = false;

            // tile immediately up
                Tile up = GridCoordToTile(openSet.get(o).gridPosition.Add(new Vector2(0, 1)));
                isInvalid = false;

                // check if this tile is invalid, either by already being queued, or already being checked (in queuedSet or in closedSet)
                for(Tile tile : closedSet) {
                    if(up == tile) {isInvalid = false;}
                }
                for(Tile tile : queuedSet) {
                    if(up == tile) {isInvalid = false;}
                }

                // if not invalid for any reason, queue the tile
                if(isInvalid = false) {
                    queuedSet.add(up);
                }

            // tile immediately right
                Tile right = GridCoordToTile(openSet.get(o).gridPosition.Add(new Vector2(1, 0)));
                isInvalid = false;

                // check if this tile is invalid, either by already being queued, or already being checked (in queuedSet or in closedSet)
                for(Tile tile : closedSet) {
                    if(right == tile) {isInvalid = true;}
                }
                for(Tile tile : queuedSet) {
                    if(right == tile) {isInvalid = true;}
                }

                // if not invalid for any reason, queue the tile
                if(isInvalid = false) {
                    queuedSet.add(right);
                }

            // tile immediately down
                Tile down = GridCoordToTile(openSet.get(o).gridPosition.Add(new Vector2(0, -1)));
                isInvalid = false;

                // check if this tile is invalid, either by already being queued, or already being checked (in queuedSet or in closedSet)
                for(Tile tile : closedSet) {
                    if(down == tile) {isInvalid = true;}
                }
                for(Tile tile : queuedSet) {
                    if(down == tile) {isInvalid = true;}
                }

                // if not invalid for any reason, queue the tile
                if(isInvalid = false) {
                    queuedSet.add(down);
                }

            // tile immediately left
                Tile left = GridCoordToTile(openSet.get(o).gridPosition.Add(new Vector2(-1, 0)));
                isInvalid = false;

                // check if this tile is invalid, either by already being queued, or already being checked (in queuedSet or in closedSet)
                for(Tile tile : closedSet) {
                    if(left == tile) {isInvalid = true;}
                }
                for(Tile tile : queuedSet) {
                    if(left == tile) {isInvalid = true;}
                }

                // if not invalid for any reason, queue the tile
                if(isInvalid = false) {
                    queuedSet.add(left);
                }
                
            }

            // add openSet to closedSet, then clear openSet
            for(int o = 0; o < openSet.size(); o++) {
                closedSet.add(openSet.get(o));
            }
            openSet.clear();

            // add queuedSet to openSet, then clear queuedSet
            for(int q = 0; q < queuedSet.size(); q++) {
                openSet.add(queuedSet.get(q));
            }
            queuedSet.clear();
        }

        return closedSet;
    }
}
