package game;

import java.io.Serializable;

/**
 * Created by wiktor on 19/03/16.
 */
public class GameDescription implements Serializable {

    private long id;
    private String playerNick;
    private String gameTitle;
    private BoardFigure creatorFigure;

    public GameDescription(long id, String playerNick, String gameTitle, BoardFigure creatorFigure) {
        this.id = id;
        this.playerNick = playerNick;
        this.gameTitle = gameTitle;
        this.creatorFigure = creatorFigure;
    }

    public long getId() {
        return id;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public BoardFigure getCreatorFigure() {
        return creatorFigure;
    }

    @Override
    public String toString() {
        return "GameDescription{" +
                "id=" + id +
                ", playerNick='" + playerNick + '\'' +
                ", gameTitle='" + gameTitle + '\'' +
                ", creatorFigure=" + creatorFigure +
                '}';
    }
}
