package exceptions;

/**
 * Created by wiktor on 20/03/16.
 */
public class IllegalMoveException extends  Exception {
    public IllegalMoveException() {
    }

    public IllegalMoveException(String s) {
        super(s);
    }

    public IllegalMoveException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalMoveException(Throwable throwable) {
        super(throwable);
    }

    public IllegalMoveException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
