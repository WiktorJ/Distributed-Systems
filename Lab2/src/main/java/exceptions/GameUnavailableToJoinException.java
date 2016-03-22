package exceptions;

/**
 * Created by wiktor on 19/03/16.
 */
public class GameUnavailableToJoinException extends Exception {
    public GameUnavailableToJoinException() {
    }

    public GameUnavailableToJoinException(String s) {
        super(s);
    }

    public GameUnavailableToJoinException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public GameUnavailableToJoinException(Throwable throwable) {
        super(throwable);
    }

    public GameUnavailableToJoinException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
