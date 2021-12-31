package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[][] grid = new String[5][5];
    static String[][] userInputArray= new String[3][3];
    static boolean xStarts = true;
    static  boolean endGame = true;

    public static void main(String[] args) {
        input();
        rysuj(1);
        rysuj(2);
        game();

    }

    static void game() {
        int player;
        if (xStarts) player = 1; //X
        else player = 2; //O

        while (!endGame) {
            endGame = true;
            System.out.print("Enter the coordinates: ");
            var scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            int b = scanner.nextInt();


            if (!grid[a][b].equals(" ")) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            else {
                if (player == 1) {
                    grid[a][b] = "X";
                    player = 2;
                    rysuj(2);
                    continue;
                } else {
                    grid[a][b] = "O";
                    player = 1;
                    rysuj(2);
                    continue;
                }
            }
        }
    }

    /**
     * Przyjmuje dane od uzytkownika w jednym wierszu
     * i zamienia je na tablice wielowymiarowa, np.
     * _XXOO_OX_ ->  XX
     *              OO
     *              OX
     *  _ zamieniane sa na  (space)
     *  Tablica wynikowa to userInputArray[3][3].
     *  Metoda ustala tez kto wykonuje nastepny ruch.
     */
    private static void input() {
        String userInput;
        var scanner = new Scanner(System.in);
        System.out.print("Enter the cells: ");
        userInput = scanner.nextLine();
        String[] splittedInput = userInput.split("", 9);

        int xCounter = 0, oCounter = 0;

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (splittedInput[counter].equals("_")) userInputArray[i][j] = " ";
                else userInputArray[i][j] = splittedInput[counter];
                if (splittedInput[counter].equals("X")) xCounter++;
                else if (splittedInput[counter].equals("O")) oCounter++;
                counter++;
            }
        }
        if (xCounter > oCounter) xStarts = false;
    }

    /**
     * Rysuje plansze, w wyniku działania tej fukncji otrzymujemy wypełnioną planszę, np.
     * - - - - -
     * | _ X X |
     * | O O _ |
     * | O X _ |
     * - - - - -
     * @param rodzajPlanszy 1 - tworzy plansze, konieczne jest wywołanie zawsze przed
     *                          pierwszym rysowaniem
     *                      2 - rysuje aktualny stan planszy, sprawdza czy wszystkie pola sa wypelnione
     */
    static void rysuj(int rodzajPlanszy) {
        if (rodzajPlanszy == 1) {

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    grid[0][i] = "-";
                    grid[4][i] = "-";
                    if ((i > 0 && i < 4) && (j > 0 && j < 4))
                        grid[i][j] = userInputArray[i - 1][j - 1];
                }
                if (i > 0 && i < 4) grid[i][0] = "|";
                if (i > 0 && i < 4) grid[i][4] = "|";
            }
        }
        else if (rodzajPlanszy == 2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(grid[i][j] + " ");
                    if (grid[i][j].equals(" ")) endGame = false;
                }
                System.out.println();
            }
        }
    }
}

