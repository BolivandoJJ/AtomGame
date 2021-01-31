package com.example.atomgame;
/**
 * game field is hexagonal map
 * example 5x5 map:
 * ⟨0,0⟩    ⟨2,0⟩    ⟨4,0⟩
 *     ⟨1,1⟩    ⟨3,1⟩
 * ⟨0,2⟩    ⟨2,2⟩    ⟨4,2⟩
 *     ⟨1,3⟩    ⟨3,3⟩
 * ⟨0,4⟩    ⟨2,4⟩    ⟨4,4⟩
 *     ⟨1,5⟩    ⟨3,5⟩
 * ⟨0,6⟩    ⟨2,6⟩    ⟨4,6⟩
 *     ⟨1,7⟩    ⟨3,7⟩
 * ⟨0,8⟩    ⟨2,8⟩    ⟨4,8⟩
 *     ⟨1,9⟩    ⟨3,9⟩
 */
public class Field {
    private final Tile[][] tiles;

    public Field(short xSize, short ySize) {
        // params validation
        if ((xSize < 1) || (ySize < 1)) {
            throw new IllegalArgumentException("Field height and width must be bigger than zero");
        }
        // creating game field - array of tiles
        tiles = new Tile[xSize][ySize * 2];
        for (short x = 0; x < xSize; x++) {
            for (short y = 0; y < ySize; y++) {
                short yCoord = (short) (y * 2 + x % 2);
                tiles[x][yCoord] = new Tile(x, yCoord);
            }
        }
    }

    public Tile getTile(short x, short y) {
        return tiles[x][y];
    }
}
