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
            game.move(rand(0, 4), rand(0, 4));
        }
        database.put(uuid, game);
        return uuid.toString();
    }

    public Game getGame(String gameIdentifier) {
        return database.get(UUID.fromString(gameIdentifier));
    }

    private int rand(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }


}
