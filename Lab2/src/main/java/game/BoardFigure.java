package game;

/**
 * Created by wiktor on 19/03/16.
 */
public enum BoardFigure {
    CROSS("X"),
    CIRCLE("0"),
    EMPTY("_");


    private String s;

    BoardFigure(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public static BoardFigure getOpposite(BoardFigure figure) {
        if (figure.equals(CROSS)) {
            return CIRCLE;
        } else if (figure.equals(CIRCLE)) {
            return CROSS;
        } else {
            return EMPTY;
        }
    }
}
