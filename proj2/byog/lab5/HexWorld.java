package byog.lab5;
import org.junit.Test;

import static java.lang.System.exit;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    /**
     * Define the position of a hexagon
     */
    private static class Position {
        int x;
        int y;
        public Position() {
            x = 0;
            y = 0;
        }
        public Position(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    /**
     * Caiculate the width of a hexagon
     */
    public static int calcW(int s) {
        int width = s;
        for (int i = 1; i < s; i++) {
            width += 2;
        }
        return width;
    }

    /**
     * Adds a hexagon of side length s to a given position in the world
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        Random r = new Random(1900);
        if (s == 0) {
            exit(0);
        }
        int height = s;
        int width = calcW(s);
        for (int i = 1; i <= height; i++) {
            for (int j = height - i; j < height - i + s + 2 * (i - 1); j++) {
                world[p.x + j][p.y + i] = TETile.colorVariant(t, 100, 0, 100, r);
            }
        }
        for (int i = height + 1; i <= 2 * height; i++) {
            for (int j = i - height - 1; j < i - height - 1 + s + 2 * (2 * height - i); j++) {
                world[p.x + j][p.y + i] = TETile.colorVariant(t, 100, 0, 100, r);
            }
        }
    }

    /**
     * Fills the given 2D array of tiles with RANDOM tiles.
     * @param tiles
     */
    public static void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Calculate total number of hexagons according to the number of hexagons on a single edge.
     * The number of hexagons on a single edge should equal to the size of the hexagon itself.
     */
    public static int calcTotalNumber(int e) {
        int num = 0;
        if (e == 1) {
            return 1;
        }
        for (int i = 1; i <= e - 1; i++) {
            num += i * 2;
        }
        num += e;
        for (int i = 1; i <= e - 1; i++) {
            num += 2 * e - 1;
        }
        return num;
    }


    /**
     * Calculate the positions of hexagons according to the size of every hexagon.
     */
    public static Position[] calcPosition(int e) {
        Position[] Positions = new Position[calcTotalNumber(e)];
        int index = 0;
        if (e == 1) {
            Positions[0] = new Position(0, 0);
            return Positions;
        }
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < i + 1; j++) {
                Positions[index] = new Position((e - i - 1 + j * 2) * (2 * e - 1), e * i);
                index += 1;
            }
        }
        for (int i = e; i < e * 2 - 1; i++) {
            for (int j = 0; j < e - 1; j++) {
                Positions[index] = new Position((j * 2 + 1) * (2 * e - 1), e * (2 * i - e));
                index += 1;
            }
        }
        for (int i = e + 1; i < e * 2 - 1; i++) {
            for (int j = 0; j < e; j++) {
                Positions[index] = new Position((j * 2) * (2 * e - 1), e * (2 * i - e - 1));
                index += 1;
            }
        }
        for (int i = 3 * e - 3; i < 4 * e - 3; i++) {
            for (int j = 0; j < 4 * e - 3 - i; j++) {
                Positions[index] = new Position((i - 3 * e + 3 + j * 2) * (2 * e - 1), e * i);
                index += 1;
            }
        }
        return Positions;
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile(Random r) {
        int tileNum = r.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WATER;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.GRASS;
            case 4: return Tileset.SAND;
            default: return Tileset.NOTHING;
        }
    }

    /**
     * Draw a big hexagon which consists of several small hexagons.
     */
    public static void drawBigHex(TETile[][] world, int s) {
        Position[] Positions = calcPosition(s);
        Random RANDOM = new Random(2022);
        for (Position p:Positions) {
            addHexagon(world, p, s, randomTile(RANDOM));
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] hexagonTiles = new TETile[WIDTH][HEIGHT];
        fillWithRandomTiles(hexagonTiles);
        drawBigHex(hexagonTiles, 4);
        ter.renderFrame(hexagonTiles);
    }
}
