let gameIdentifier = null;
let gameOver = false;
let c = 1;

window.addEventListener("load", async () => {
    document.querySelector("body").innerHTML = await (await fetch("/lights-out.svg")).text();
    if (location.hash == "") {
        newGame();
    } else {
        gameIdentifier = location.hash.substr(1);
    }
    poll();
});

const newGame = async () => {
    gameOver = false;
    gameIdentifier = await (await fetch("/games", { method: "POST" })).text();
    location.hash = gameIdentifier;
};

const poll = async () => {
    if (gameIdentifier !== null) {
        if (true) { //if game exists, then render. if not, start a new game
            render((await (await fetch(`/games/${gameIdentifier}`)).json()).board);
        } else {
            newGame();
        }
    }
    await sleep(200);
    poll();
};

const render = board => {
    let count = 0;
    for (const row in board) {
        for (const column in board[row]) {
            document.getElementById(`button-${row}-${column}`).style.fill = ["rgb(131, 148, 150)", "rgb(211, 54, 130)"][board[row][column]];
            if (board[row][column] == 0) {
                count++;
            }
        }
    }
    if (count == 25) {
        gameOver = true;
    }
    if (gameOver) {
        endAnimation();
        newGame();
    }
};

const move = async (row, column) => {
    if (!gameOver) {
        await fetch(`/games/${gameIdentifier}`, {method: "PUT", body: JSON.stringify({row, column})});
    }
};

const cheat = async () => {
    if (!gameOver) {
        await fetch(`/games/${gameIdentifier}/cheat`, {method: "PUT"});
    }
};

const sleep = duration => new Promise(resolve => window.setTimeout(resolve, duration));

const endAnimation = async() => {
    var audio = new Audio('johncena.mp3');
    audio.play()
    console.log("G A M E O V E R " + c);
    c++;
    for (i = 0; i < 5; i++) {
        for (j = 0; j < 5; j++) {
            document.getElementById(`button-${i}-${j}`).style.fill = ["rgb(131, 148, 150)", "rgb(211, 54, 130)"][1];
        }
    }
};
