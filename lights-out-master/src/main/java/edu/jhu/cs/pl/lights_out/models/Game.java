package edu.jhu.cs.pl.lights_out.models;

import java.util.UUID;

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
}
