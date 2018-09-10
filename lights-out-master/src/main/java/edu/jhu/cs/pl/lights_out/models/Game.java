package edu.jhu.cs.pl.lights_out.models;


public class Game {
    private int[][] board = new int[5][5];

    public Game(int[][] board) {
        this.board = board;
    }

    public Game() {
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void move (int x, int y) {
        for (int i = -1; i <= 1; i++) {
            swap(x + i, y);
        }
        swap(x, y - 1);
        swap(x, y + 1);
    }

    private void swap (int x, int y) {
        if (!(x > 4 || x < 0 || y > 4 || y < 0)) {
            if (board[x][y] == 0) {
                board[x][y] = 1;
            } else {
                board[x][y] = 0;
            }
        }
    }
}
