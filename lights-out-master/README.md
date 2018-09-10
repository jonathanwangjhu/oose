Lights Out
==========

You will implement the game Lights Out. It is a game based on a 5x5 grid of lights. When it starts, some lights are on and some are off. When you press a light, it and its immediate four neighbors toggle. The goal is to turn all lights off. Play with the reference implementation at <https://oose-lights-out.herokuapp.com>. Even if you are bad at the game, you can win easily with the **Cheat** button. When you win, nothing will happen (see [Homework 2](#homework-2--client)), and all you can do is press **New Game** to start over.

The implementation is divided in two parts: [server](#homework-1--server) and [client](#homework-2--client). They communicate via the **Lights Out API**, specified in the Postman **Collection** at `docs/Lights Out API Specification.postman_collection.json`.

Homework 1 · Server
-------------------

**Goals**: Reading an API specification; setting up the development environment; web server programming in Java.

Import `docs/Lights Out API Specification.postman_collection.json` as a Postman **Collection**. Read the documentation and follow the instructions there.

When ready to start working on the server, import this project in IntelliJ as a Maven project and start at `src/main/java/edu/jhu/cs/pl/lights_out/Server.java`. You can store games in memory instead of using a database; games do not need to persist across server runs.

Homework 2 · Client
-------------------

**Goals**: Adding features to an existing code base (written in part by someone else); web client programming in JavaScript; programming creatively.

**Task 1 · Game Over**

Nothing happens when the game is over. The player receives no feedback, but the lights no and the **Cheat** button no longer work. Worse, the browser continues to poll the server for the game state, though it will never change again, and may even perform bad requests if the player tries to press a light or **Cheat**. Fix all these issues by editing the client JavaScript. After you are done, the browser must not make a request that the server would respond with HTTP Status 400 or 404.

Be creative on how to provide feedback. The following is a version that flashes the lights, says “YOU WIN”, and starts a new game:

<img alt="Game Over Feedback" src="docs/game-over-feedback.gif" width="512" height="643"></img>

You do not see this on the deployed version of the reference implementation because that would give away the answer to the homework.

Your version might use the light grid, sound, and so forth.

**Task 2 · Multiplayer**

Let multiple players play the same game together. Most of the heavy lifting is already done, players just need a way to share the `gameIdentifier`, and the client needs to be extended to pick up a given `gameIdentifier`, instead of always starting a new game.

When the page loads, check if a `gameIdentifier` is provided after the hash in the URL, for example, `https://oose-lights-out.herokuapp.com/#d66c5ab2-6e8d-4129-a648-7aa8ec93291a`. If it is, then do not start a new game, but use that `gameIdentifier`.

If no `gameIdentifier` is provided in the URL, or if the player starts a **New Game**, change the URL so it includes the new `gameIdentifier`. Players that want to play the game together should share that URL.

In JavaScript, `location.hash` allows you to read and write to the URL hash.
