package game;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by wiktor on 19/03/16.
 */
public class GameSetup implements Serializable{
    private String title;
    private BoardFigure creatorFigure;
    private int boardSize;
    private int winCondition;

    private GameSetup(String title, BoardFigure creatorFigure, int boardSize, int winCondition) {
        this.title = title;
        this.creatorFigure = creatorFigure;
        this.boardSize = boardSize;
        this.winCondition = winCondition;
    }

    public static class GameSetupBuilder {
        private String title;
        private BoardFigure creatorFigure;
        private int boardSize;
        private int winCondition;

        public GameSetupBuilder() {
            this.title = "";
            this.creatorFigure = BoardFigure.EMPTY;
            this.boardSize = 3;
            this.winCondition = 3;
        }

        public GameSetupBuilder title(String title) {
            this.title = title;
            return this;
        }

        public GameSetupBuilder figure(BoardFigure figure) {
            this.creatorFigure = figure;
            return this;
        }

        public GameSetupBuilder boardSize(int size) {
            this.boardSize = size;
            return this;
        }

        public GameSetupBuilder winCondition(int winCondition) {
            this.winCondition = winCondition;
            return this;
        }

        public GameSetup build() {
            if (creatorFigure.equals(BoardFigure.EMPTY)) {
                return new GameSetup(title, randomFigure(), boardSize, winCondition);
            } else {
                return new GameSetup(title, creatorFigure, boardSize, winCondition);
            }
        }

        private BoardFigure randomFigure() {
            Random random = new Random();
            if (random.nextInt(2) == 0) {
                return BoardFigure.CIRCLE;
            } else {
                return BoardFigure.CROSS;
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public BoardFigure getCreatorFigure() {
        return creatorFigure;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getWinCondition() {
        return winCondition;
    }
}
