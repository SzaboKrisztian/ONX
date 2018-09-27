import java.util.List;
import java.util.Scanner;

public class DisplayAgent {
    private static DisplayAgent instance;
    private static Scanner scanner;
    private static List<Player> players;

    private DisplayAgent() {
        scanner = new Scanner(System.in);
    }

    public static DisplayAgent getInstance() {
        if (instance == null)
            instance = new DisplayAgent();
        return instance;
    }

    public static void setPlayers(List<Player> playerList) {
        players = playerList;
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void drawBoard(Board board) {
        char[] boardArr = board.getBoardState().toCharArray();
        System.out.print('\n');
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (boardArr[i * 3 + j]) {
                    case '0':
                        System.out.print(i * 3 + j + 1);
                        break;
                    case '1':
                        System.out.print(players.get(0).getSymbol());
                        break;
                    case '2':
                        System.out.print(players.get(1).getSymbol());
                }
                if (j < 2) {
                    System.out.print('|');
                } else {
                    System.out.print('\n');
                }
            }
            if (i < 2)
                System.out.println("-----");
        }
        System.out.print('\n');
    }

    public char getChar() {
        char result = 0;
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            result = input.toCharArray()[0];
        }
        return result;
    }

    public String getLine() {
        return scanner.nextLine();
    }
}
