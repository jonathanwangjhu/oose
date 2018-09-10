package edu.jhu.cs.pl.lights_out.repositories;

import edu.jhu.cs.pl.lights_out.models.Game;

import java.util.HashMap;
import java.util.UUID;

public class GamesRepository {
    private HashMap<UUID, Game> database;

    public GamesRepository (HashMap database) {
        this.database = database;
    }

    public String create(Game game) {
        UUID uuid = UUID.randomUUID();
        database.put(uuid, game);
        return uuid.toString();
    }

    public HashMap<UUID, Game> getGames() {
        HashMap<UUID, Game> games = new HashMap<UUID, Game>();
        for (UUID key : database.keySet()){
            games.put(key, database.get(key));
        }
        return games;
    }

    public Game getGame(String itemIdentifier) {
        Game result = database.get(UUID.fromString(itemIdentifier));
        return result;
    }

    public void update(Game game) {
        //database.put()
    }
}
