package edu.jhu.cs.pl.lights_out.repositories;

import edu.jhu.cs.pl.lights_out.models.Game;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class GamesRepository {
    private HashMap<UUID, Game> database;

    public GamesRepository (HashMap database) {
        this.database = database;
    }

    public String create(Game game) {
        UUID uuid = UUID.randomUUID();
        for (int i = 0; i < rand(5, 20); i++) {
            move(rand(0, 4), rand(0, 4), game);
        }
        database.put(uuid, game);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(game.getBoard()[i][j]);
            }
            System.out.println("");
        }
        return uuid.toString();
    }

    public HashMap<UUID, Game> getGames() {
        HashMap<UUID, Game> games = new HashMap<UUID, Game>();
        for (UUID key : database.keySet()){
            games.put(key, database.get(key));
        }
        return games;
    }

    public Game getGame(String gameIdentifier) {
        Game result = database.get(UUID.fromString(gameIdentifier));
        return result;
    }

    public void update(Game game) {
        //database.put()
    }

    private int rand(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private void move(int x, int y, Game game) {
        int[][] board = game.getBoard();
        if (board[x][y] == 0) {
            board[x][y] = 1;
        } else {
            board[x][y] = 0;
        }
    }
}
