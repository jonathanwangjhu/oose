package edu.jhu.cs.pl.lights_out.controllers;

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
        var moveParameter = Server.getJson().readTree(ctx.body());
        if (moveParameter == null || moveParameter.size() != 2)
            throw new BadRequestResponse();
        int row = moveParameter.get("row").asInt();
        int col = moveParameter.get("column").asInt();
        game.move(row, col);
        Server.getGamesRepository().update(game);
        ctx.status(204);
    }

    public static void cheat (Context ctx) {
        ctx.status(204);
    }

    private static Game getGame (Context ctx) {
        String gameIdentifier;
        try {
            gameIdentifier = ctx.pathParam("game-identifier");
        } catch (NumberFormatException e) {
            throw new NotFoundResponse();
        }
        return Server.getGamesRepository().getGame(gameIdentifier);
    }


}
