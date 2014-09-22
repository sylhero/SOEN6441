package TEST;

import java.util.Arrays;

import tilemap.Tile;
import tilemap.TileMap;

public class Shortestpath {
	final static int TRIED = 8;
    final static int PATH = 9;
    private TileMap tileMap;
    private int[][] gridMap;

    private int row;
    private int col;

    public static void main(String[] args) {
        Shortestpath maze = new Shortestpath(GRID);
        boolean solved = maze.solve();
        System.out.println("Solved: " + solved);
        System.out.println(maze.toString());
    }


    public Shortestpath(TileMap tileMap) {
        this.tileMap = tileMap;
        this.row     = tileMap.getMapRow();
        this.col     = tileMap.getMapCol();

        this.map = new int[height][width];
    }

    public boolean solve() {
        return traverse(0,0);
    }

    private boolean traverse(int i, int j) {
        if (!isValid(i,j)) {
            return false;
        }

        if ( isEnd(i, j) ) {
            map[i][j] = PATH;
            return true;
        } else {
            map[i][j] = TRIED;
        }

        // North
        if (traverse(i - 1, j)) {
            map[i-1][j] = PATH;
            return true;
        }
        // East
        if (traverse(i, j + 1)) {
            map[i][j + 1] = PATH;
            return true;
        }
        // South
        if (traverse(i + 1, j)) {
            map[i + 1][j] = PATH;
            return true;
        }
        // West
        if (traverse(i, j - 1)) {
            map[i][j - 1] = PATH;
            return true;
        }

        return false;
    }

    private boolean isEnd(int i, int j) {
        return i == height - 1 && j == width - 1;
    }

    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
            return true;
        }

        return false;
    }

    private boolean isOpen(int i, int j) {
        return grid[i][j] == 1;
    }

    private boolean isTried(int i, int j) {
        return map[i][j] == TRIED;
    }

    private boolean inRange(int i, int j) {
        return inHeight(i) && inWidth(j);
    }

    private boolean inHeight(int i) {
        return i >= 0 && i < height;
    }

    private boolean inWidth(int j) {
        return j >= 0 && j < width;
    }

    public String toString() {
        String s = "";
        for (int[] row : map) {
            s += Arrays.toString(row) + "\n";
        }

        return s;
    }


	
}
