package edu.jhu.cs.pl.lights_out.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import edu.jhu.cs.pl.lights_out.Server;
import edu.jhu.cs.pl.lights_out.models.Game;
import edu.jhu.cs.pl.lights_out.repositories.GamesRepository;

import io.javalin.BadRequestResponse;
import io.javalin.Context;
import io.javalin.NotFoundResponse;

import java.io.IOException;
import java.util.UUID;


public class GamesController {
    public static void newGame(Context ctx) {
        String str = Server.getGamesRepository().create(new Game());
        ctx.status(201);
        ctx.result(str);
    }


    public static void getGames(Context ctx) {
        ctx.json(Server.getGamesRepository().getGame(ctx.pathParam("game-identifier")));
    }

    public static void move (Context ctx) throws IOException {
        var game = getGame(ctx);
        if (game == null) {
            ctx.status(404);
        } else if (!gameEnded(game)) {
            try {
                var moveParameter = Server.getJson().readTree(ctx.body());
                if (moveParameter == null || moveParameter.size() != 2)
                    throw new BadRequestResponse();
                int row = moveParameter.get("row").asInt();
                int col = moveParameter.get("column").asInt();
             game.move(row, col);
             Server.getGamesRepository().update(game);
             ctx.status(204);
            } catch (JsonParseException j) {
                ctx.status(400);
            }
        } else {
            ctx.status(400);
        }

    }

    public static void cheat (Context ctx) {
        var game = getGame(ctx);
        if (game == null) {
            ctx.status(404);
        } else if (!gameEnded(game)) {
            game.cheat();
            ctx.status(204);
        } else {
            ctx.status(400);
        }
    }

    private static Game getGame (Context ctx) {
        String gameIdentifier;
        try {
            gameIdentifier = ctx.pathParam("game-identifier");
        } catch (NumberFormatException e) {
            ctx.status(404);
            throw new NotFoundResponse();
        }
        //return Server.getGamesRepository().getGame(gameIdentifier);
        try {
            Game result = Server.getGamesRepository().getGame(gameIdentifier);
            return result;
        } catch (IllegalArgumentException e) {
            ctx.status(404);
        }
        return null;
    }

    private static boolean gameEnded (Game game) {
        int ones = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (game.getBoard()[i][j] == 1) {
                    ones++;
                }
            }
        }
        if (ones == 0) {
            return true;
        }
        return false;
    }


}
