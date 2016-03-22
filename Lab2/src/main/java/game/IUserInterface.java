package game;

import java.io.IOException;

/**
 * Created by wiktor on 20/03/16.
 */
public interface IUserInterface {
    BoardCell getUserMove() throws IOException;
}
