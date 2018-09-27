import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int getMove() {
        Scanner scn = new Scanner(System.in);
        int move = 0;
        while (move == 0) {
            System.out.print(super.getName() + ", please enter your move [1-9]: ");
            try {
                move = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
        return move;
    }
}
