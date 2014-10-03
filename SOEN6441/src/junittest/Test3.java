package junittest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test3 {
	final static int TRIED = 2;
    final static int PATH = 3;

    // @formatter:off
    private static int[][] GRID = { 
        { 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 },
        { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1 },
        { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
        { 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1 },
        { 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 },
        { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3 } 
    };
    // @formatter:off

    public static void main(String[] args) {
        Test3 maze = new Test3(GRID);
        boolean solved = maze.solve();
        System.out.println("Solved: " + solved);
        System.out.println(maze.toString());
    }

    private int[][] grid;
    private int height;
    private int width;

    private int[][] map;

    public Test3(int[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;

        this.map = new int[height][width];
    }

    public boolean solve() {
        return traverse(3,3);
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
