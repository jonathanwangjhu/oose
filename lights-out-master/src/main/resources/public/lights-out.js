let gameIdentifier = null;

window.addEventListener("load", async () => {
    document.querySelector("body").innerHTML = await (await fetch("/lights-out.svg")).text();
    newGame();
    poll();
});

const newGame = async () => {
    gameIdentifier = await (await fetch("/games", { method: "POST" })).text();
};

const poll = async () => {
    if (gameIdentifier !== null) render((await (await fetch(`/games/${gameIdentifier}`)).json()).board);
    await sleep(200);
    poll();
};

const render = board => {
    for (const row in board) {
        for (const column in board[row]) {
            document.getElementById(`button-${row}-${column}`).style.fill = ["rgb(131, 148, 150)", "rgb(211, 54, 130)"][board[row][column]];
        }
    }
};

const move = async (row, column) => {
    await fetch(`/games/${gameIdentifier}`, { method: "PUT", body: JSON.stringify({row, column}) });
};

const cheat = async () => {
    await fetch(`/games/${gameIdentifier}/cheat`, { method: "PUT" });
};

const sleep = duration => new Promise(resolve => window.setTimeout(resolve, duration));
