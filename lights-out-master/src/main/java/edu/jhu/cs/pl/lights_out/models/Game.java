package edu.jhu.cs.pl.lights_out.models;

public class Game {
    private int id = 0;
    private int[][] board = null;

    public Game(int id, int[][] board) {
        this.id = id;
        this.board = board;
    }

    public Game() {

    }

    public int getId() {
        return id;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
