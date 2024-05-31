package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {
    static final char SIGN_X = 'x';
    static final char SIGN_O = 'o';
    static final char SIGN_EMPTY = '.';
    static char[][] table;
    static Random random;
    static Scanner scanner;

    public Game() {
        random = new Random();
        scanner = new Scanner(System.in);
        table = new char[3][3];
    }
    public void game() throws InterruptedException {
        createTable();
        System.out.println("You play for X \nComputer plays for O\n");
        while (true) {
            yourTurn();
            if (checkWin(SIGN_X)) {
                System.out.println("You win!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Draw!");
                break;
            }
            printTable();
            compTurn();
            Thread.sleep(1000);
            printTable();
            if (checkWin(SIGN_O)) {
                System.out.println("Computer wins!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Draw!");
                break;
            }
        }
        System.out.println("GAME OVER.");
        printTable();
    }
    private static void createTable() {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                table[row][column] = SIGN_EMPTY;
    }
    private static void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++)
                System.out.print(table[row][column] + " ");
            System.out.println();
        }
    }
    private static void yourTurn() {
        int x, y;
        do {
            System.out.println("Your turn: enter coordinates by X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (isCellValid(x, y));
        System.out.println("Your move:");
        table[y][x] = SIGN_X;
    }
    private static void compTurn() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (isCellValid(x, y));
        System.out.println("Computers move:");
        table[y][x] = SIGN_O;

    }
    private static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3)
            return true;
        return table[y][x] != SIGN_EMPTY;
    }
    private static boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot))
                return true;
        return (table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot);
    }
    private static boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                if (table[row][column] == SIGN_EMPTY)
                    return false;
        return true;
    }

}
