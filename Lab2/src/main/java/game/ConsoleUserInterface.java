package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wiktor on 20/03/16.
 */
public class ConsoleUserInterface implements IUserInterface {
    @Override
    public BoardCell getUserMove() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n\nEnter field to put figure");
        boolean inputCorrect = false;
        int row = 0;
        int column = 0;
        while (!inputCorrect) {
            try {
                System.out.print("row: ");
                row = Integer.parseInt(in.readLine());
                System.out.print("\ncolumn: ");
                column = Integer.parseInt(in.readLine());
                inputCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("Pleas type correct number");
            }
        }
        return new BoardCell(row, column);

    }
}
