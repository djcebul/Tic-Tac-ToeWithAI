package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static String[][] grid = new String[5][5];
    static String[][] userInputArray= new String[3][3];
    static boolean xStarts = true;
    static  boolean endGame = true;
    static boolean xWins = false, oWins = false, draw = true;

    public static void main(String[] args) {


        String[] menuArgs;
        var scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            menuArgs = scanner.nextLine().split(" ");
            if (menuArgs[0].equals("exit")) return;
            else if (menuArgs.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }
            else {
                if (menuArgs[0].equals("start")) {
                    if (menuArgs[1].equals("user") && menuArgs[2].equals("user")) {
                        rysuj(1);
                        rysuj(2);
                        check();
                        game(1);
                        break;
                    }
                    else if ((menuArgs[1].equals("easy") && menuArgs[2].equals("user")) ||
                            ((menuArgs[1].equals("user") && menuArgs[2].equals("easy")))) {
                        rysuj(1);
                        rysuj(2);
                        check();
                        game(2);
                        break;
                    }
                    else if (menuArgs[1].equals("easy") && menuArgs[2].equals("easy")) {
                        rysuj(1);
                        rysuj(2);
                        check();
                        game(3);
                        break;
                    }
                }

            }
        }

        //game(1);

    }

    static void game(int gameType) {

        int player;
        int a = 0;
        int b = 0;
        if (xStarts) player = 1; //X
        else player = 2; //O

        if (gameType == 1) { //player vs player
            while (!endGame) {

                endGame = true;
                System.out.print("Enter the coordinates: ");
                var scanner = new Scanner(System.in);

                a = 0;
                b = 0;
                try {
                    a = scanner.nextInt();
                    b = scanner.nextInt();
                } catch (InputMismatchException e) {
                    String badInput = scanner.next();
                    System.out.println("You should enter numbers!");
                    endGame = false;
                    continue;
                }
                if (a < 0 || a > 3 || b < 0 || b > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    endGame = false;
                    continue;
                }


                if (!grid[a][b].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!");
                    endGame = false;
                    continue;
                } else {
                    if (player == 1) {
                        grid[a][b] = "X";
                        player = 2;
                        rysuj(2);
                        check();
                        continue;
                    } else {
                        grid[a][b] = "O";
                        player = 1;
                        rysuj(2);
                        check();
                        continue;
                    }
                }

            }
        }

        if (gameType == 2) { //player vs computer
            while (!endGame) {

                endGame = true;
                if (player == 1) {
                    System.out.print("Enter the coordinates: ");
                    var scanner = new Scanner(System.in);


                    try {
                        a = scanner.nextInt();
                        b = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        String badInput = scanner.next();
                        System.out.println("You should enter numbers!");
                        endGame = false;
                        continue;
                    }
                    if (a < 0 || a > 3 || b < 0 || b > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        endGame = false;
                        continue;
                    }


                    if (!grid[a][b].equals(" ")) {
                        System.out.println("This cell is occupied! Choose another one!");
                        endGame = false;
                        continue;
                    }
                    grid[a][b] = "X";
                    player = 2;
                    rysuj(2);
                    check();
                    continue;
                } else {
                    System.out.println("Making move level \"easy\"");
                    compMove(2);
                    player = 1;
                    rysuj(2);
                    check();
                    continue;

                }
            }
        }
        if (gameType == 3) {
            while (!endGame) {
                endGame = true;
                System.out.println("Making move level \"easy\"");

                if (player == 1) {
                    compMove(1);
                    player = 2;
                    rysuj(2);
                    check();
                    continue;
                } else {
                    compMove(2);
                    player = 1;
                    rysuj(2);
                    check();
                    continue;
                }

            }
        }
    }

    static void compMove(int player) {
        var random = new Random();


        while (true) {
            int a = random.nextInt(4);
            int b = random.nextInt(4);

            if (grid[a][b].equals(" ")) {
                if (player == 1) grid[a][b] = "X";
                if (player == 2) grid[a][b] = "O";
                break;
            }
            else continue;
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
//    private static void input() {
//        String userInput;
//        var scanner = new Scanner(System.in);
//        System.out.print("Enter the cells: ");
//        userInput = scanner.nextLine();
//        String[] splittedInput = userInput.split("", 9);
//
//        int xCounter = 0, oCounter = 0;
//
//        int counter = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (splittedInput[counter].equals("_")) userInputArray[i][j] = " ";
//                else userInputArray[i][j] = splittedInput[counter];
//                if (splittedInput[counter].equals("X")) xCounter++;
//                else if (splittedInput[counter].equals("O")) oCounter++;
//                counter++;
//            }
//        }
//        if (xCounter > oCounter) xStarts = false;
//
//    }

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
     *                      3 - rysuje pusta plansze
     */
    static void rysuj(int rodzajPlanszy) {
        if (rodzajPlanszy == 1) {

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    grid[0][i] = "-";
                    grid[4][i] = "-";
                    if ((i > 0 && i < 4) && (j > 0 && j < 4))
                        grid[i][j] = " ";
                }
                if (i > 0 && i < 4) grid[i][0] = "|";
                if (i > 0 && i < 4) grid[i][4] = "|";
            }
        }

        else if (rodzajPlanszy == 2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(grid[i][j] + " ");
                    }
                System.out.println();
                }
        }

//        else if (rodzajPlanszy == 3) {
//            String[][] gridEmpty = new String[5][5];
//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 5; j++) {
//                    gridEmpty[0][i] = "-";
//                    gridEmpty[4][i] = "-";
//                    if ((i > 0 && i < 4) && (j > 0 && j < 4))
//                        gridEmpty[i][j] = " ";
//                }
//                if (i > 0 && i < 4) gridEmpty[i][0] = "|";
//                if (i > 0 && i < 4) gridEmpty[i][4] = "|";
//            }
//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 5; j++) {
//                    System.out.print(gridEmpty[i][j] + " ");
//                }
//                System.out.println();
//            }
//        }
    }
    static void check() {
        int poziomoX = 0, pionowoX = 0, skos1X = 0, skos2X =0;
        int poziomoO = 0, pionowoO = 0, skos1O = 0, skos2O =0;
        draw = true;

        for (int i = 0; i < 5; i++) {
            poziomoX = 0; poziomoO = 0; pionowoX =0; pionowoO = 0;
            for (int j = 0; j < 5; j++) {
                if (grid[i][j].equals(" ")) {
                    draw = false;
                    endGame = false;
                }
                if ((i > 0 && i < 4) && (j > 0 && j < 4)) {
                    if (grid[i][j].equals("X")) poziomoX++; //sprawdzenie w poziomie
                    if (grid[i][j].equals("O")) poziomoO++;
                    if (grid[j][i].equals("X")) pionowoX++; //sprawdzenie w pionie
                    if (grid[j][i].equals("O")) pionowoO++;
                }
            }
            if (grid[i][i].equals("X")) skos1X++; //sprawdzenie pierwszego skosu
            if (grid[i][i].equals("O")) skos1O++;
            if (poziomoX == 3 || skos1X == 3 || pionowoX ==3) xWins = true;
            if (poziomoO == 3 || skos1O == 3 || pionowoO == 3) oWins = true;
        }

        for (int i = 1; i < 4; i++) { //sprawdzanie drugiego skosu
            for (int j = 3; j > 0; j--) {
                //deleteitSystem.out.println(grid[i][j] + ", i" + i + ", j" + j + "    skos2x-skos2o:"+ skos2X + skos2O);
                if (grid[i][j].equals("X")) skos2X++;
                if (grid[i][j].equals("O")) skos2O++;
                i++;
            }
        }
        if (skos2X == 3) xWins = true;
        if (skos2O == 3) oWins = true;
        if (xWins) {
            System.out.println("X wins");
            endGame = true;
        }
        if (oWins) {
            System.out.println("O wins");
            endGame = true;
        }
        if (!xWins && !oWins && draw) System.out.println("Draw");
    }
}


