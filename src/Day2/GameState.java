package Day2;

public class GameState {
    public int getScore(String[] match) {
        var opponentShape = getShape(match[0]);
        States playerState = getPlayerState(match[1]);
        int stateScore = 0;
        Shape playerShape = switch(playerState) {
            case LOSE -> getLowerShape(opponentShape);
            case WIN -> getUpperShape(opponentShape);
            case TIE -> opponentShape;
        };
        switch (playerState) {
            case WIN -> stateScore += 6;
            case TIE -> stateScore += 3;
        }
        return getPoint(playerShape) + stateScore;
    }

    private States getPlayerState(String symbol) {
        return switch (symbol) {
            case "X" -> States.LOSE;
            case "Y" -> States.TIE;
            default -> States.WIN;
        };
    }

    private int getPoint(Shape playerShape) {
        return switch (playerShape) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSORS -> 3;
        };
    }

    Shape getUpperShape(Shape shape) {
        return switch (shape) {
            case ROCK -> Shape.PAPER;
            case PAPER -> Shape.SCISSORS;
            case SCISSORS -> Shape.ROCK;
        };
    }

    Shape getLowerShape(Shape shape) {
        return switch (shape) {
            case ROCK -> Shape.SCISSORS;
            case PAPER -> Shape.ROCK;
            case SCISSORS -> Shape.PAPER;
        };
    }

    Shape getShape(String symbol) {
        return switch (symbol) {
            case "A" -> Shape.ROCK;
            case "B" -> Shape.PAPER;
            default -> Shape.SCISSORS;
        };
    }
}
