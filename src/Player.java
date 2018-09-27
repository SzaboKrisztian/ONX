public abstract class Player {
    private String name;
    private char symbol;
    private int score;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getScore() {
        return this.score;
    }

    public void increaseScore() {
        this.score++;
    }

    public abstract int getMove();
}
