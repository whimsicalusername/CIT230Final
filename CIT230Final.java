/**
 * Nikki Middleton
 * CIT230
 * Professor Reynolds
 * Final
 * 15 December 2020
 */
package cit230.pkgfinal;

import java.util.*;
import java.io.*;

public class CIT230Final {

    public static void main(String[] args) {

        runMenu();

    }

    private static Scanner runMenu() throws InputMismatchException {
        Scanner input = new Scanner(System.in);
        int menuChoice = 0;
        System.out.println("Menu (choose #)");
        System.out.print("\n1:\tStart a new game\n2:\tLoad a saved game\n3:\tSave your game\n4:\tQuit\nMenu Choice: ");

        menuChoice = input.nextInt();

        switch (menuChoice) {
            case 1:
                newGame();
                break;
            case 2:
                System.out.println("Load a game (INCOMPLETE)");
                break;
            case 3:
                System.out.println("Save a game (INCOMPLETE)");
                break;
            case 4:
                System.out.println("Quit program (INCOMPLETE)");
                break;
            default:

        }

        return input;

    }

    private static void newGame() {
        Game game = new Game();

        while (game.gameStarted()) {
            game.printBoard();
            game.movePiece();
        }
    }
}
