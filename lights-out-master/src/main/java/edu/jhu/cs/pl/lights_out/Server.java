package edu.jhu.cs.pl.lights_out;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jhu.cs.pl.lights_out.models.Game;
import edu.jhu.cs.pl.lights_out.repositories.GamesRepository;
import io.javalin.Javalin;
import io.javalin.JavalinEvent;
import io.javalin.staticfiles.Location;

import edu.jhu.cs.pl.lights_out.controllers.GamesController;
import java.util.HashMap;
import java.util.UUID;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Server {
    private static HashMap database = new HashMap<UUID, Game>();
    private static GamesRepository gamesRepository;
    private static ObjectMapper json = new ObjectMapper();

    public static void main(String[] args) {
        Javalin.create()

                .routes(() -> {
                    path("games", () -> {
                        post(GamesController::newGame);
                        path(":game-identifier", () -> {
                            put(GamesController::move);
                            get(GamesController::getGames);
                            path ("cheat", () -> {
                                put(GamesController::cheat);
                            });
                        });
                    });
                })

                .enableStaticFiles("/public")
                .enableStaticFiles(System.getProperty("user.dir") + "/src/main/resources/public", Location.EXTERNAL)

                .event(JavalinEvent.SERVER_STARTING, () -> { gamesRepository = new GamesRepository(database);})

                .start(System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 7000);
    }

    public static GamesRepository getGamesRepository() {
        return gamesRepository;
    }

    public static ObjectMapper getJson() {
        return json;
    }
}
