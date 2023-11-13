package se.iths.tictactoe;

public record GameController(Model model) {

    public void handleMoves(int row, int col) {
        if (model.isgameEnded) {
            return;
        }
        model.makeMove(row, col);
        String winner = model.checkGameResult();

        if (winner != null) {
            handleWinner(winner);

        } else if (model.isBoardFull()) {
            handleDraw();
        } else {
            ComputerPlayer.makeMove(model);
            String computerWinner = model.checkGameResult();

            if (computerWinner != null) {
                handleWinner(computerWinner);

            } else if (model.isBoardFull()) {
                handleDraw();
            }
        }
    }

    void handleWinner(String winner) {
        model.incrementWins(winner);
        model.isgameEnded = true;
    }

    void handleDraw() {
        model.incrementDraws();
        model.isgameEnded = true;
    }
}