import java.util.ArrayList;
import java.util.List;

public class XOGame {
    private List<Player> players;
    private List<Board> boardHistory;
    private DisplayAgent screen;

    public XOGame() {
        screen = DisplayAgent.getInstance();
        players = new ArrayList<Player>();
        boardHistory = new ArrayList<Board>();
        boardHistory.add(new Board());

        String name, input;
        char symbol;

        for (int i = 1; i <= 2; i++) {
            while (true) {
                screen.displayMessage("Player " + i + ", type in your name: ");
                name = screen.getLine();
                screen.displayMessage("Player " + i + ", pick your symbol: ");
                symbol = screen.getChar();
                screen.displayMessage("Player " + i + ", your name is " + name + ", and you're using the symbol \'" +
                    symbol + "\'. Is this correct? [Y to confirm]: ");
                input = screen.getLine();
                if (input != "\n" && input.toUpperCase().toCharArray()[0] == 'Y') {
                    players.add(new HumanPlayer(name, symbol));
                    break;
                }
            }
            screen.setPlayers(players);
        }
    }

    public void play() {
        while (true) { // Main game loop
            boolean continuePlaying = true;

            screen.displayMessage("\n" + players.get(boardHistory.get(boardHistory.size() - 1).getCurrentPlayer() - 1).getName() +
                    ", you get to play first!\n");

            while (true) { // Round loop
                Board currentBoard = boardHistory.get(boardHistory.size() - 1);

                int status = Board.checkWinCondition(currentBoard);
                if (status != 0) {
                    if (status != 3) {
                        players.get(status - 1).increaseScore();
                    }

                    screen.drawBoard(currentBoard);
                    screen.displayMessage(players.get(0).getName() + " (" + players.get(0).getSymbol() + "): " +
                            players.get(0).getScore() + " vs. " + players.get(1).getName() + " (" +
                            players.get(1).getSymbol() + "): " + players.get(1).getScore() + "\n");

                    if (status != 3) {
                        screen.displayMessage("\n" + players.get(status - 1).getName() +
                                " wins!");
                    } else {
                        screen.displayMessage("\nIt's a tie!");
                    }
                    screen.displayMessage(" Play another round? [N to quit]: ");

                    char answer = screen.getChar();
                    if (answer == 'n' || answer == 'N') {
                        continuePlaying = false;
                        break;
                    }
                    else {

                        boardHistory.clear();
                        boardHistory.add(new Board(3 - status));
                        continue;
                    }
                }

                screen.drawBoard(currentBoard);
                Board next = null;
                while (next == null) {
                    int move = players.get(currentBoard.getCurrentPlayer() - 1).getMove();
                    if (move == -1) {
                        boardHistory.remove(boardHistory.size() - 1);
                        next = boardHistory.remove(boardHistory.size() - 1);
                    } else {
                        next = currentBoard.makeMove(move);
                    }
                }

                boardHistory.add(next);
            }

            if (!continuePlaying)
                break;

        }
    }
}
