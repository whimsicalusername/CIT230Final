package cit230.pkgfinal;

import java.util.*;
import java.io.*;

public class Game {

    private static Boolean gameRunning;
    private static Piece[][] gB = new Piece[8][8];
    private static Stack<String> lostPieces = new Stack<>();
    char a = 'A';
    private static String move;

    Scanner input = new Scanner(System.in);

    // Source rows and columns
    private static int sRow, sCol;
    // Destination rows and columns
    private static int dRow, dCol;

    // Starting scores for (b)lack and (w)hite
    private static int rScore = 0;
    private static int bScore = 0;

    private static Boolean illegalMove = false;
    private static Boolean redMove;

    public Game() {
        initializeGame();
        gameRunning = true;
    }

    public Boolean gameStarted() {
        return Game.gameRunning;
    }

    // Method to initialize board **COMPLETE**
    public static void initializeGame() {
        for (Piece[] gB1 : gB) {
            for (int col = 0; col < gB1.length; col++) {
                switch (col) {
                    case 1:
                        gB1[col] = new Red(true, false);
                        break;
                    case 6:
                        gB1[col] = new Blue(false, true);
                        break;
                    default:
                        gB1[col] = null;
                        break;
                }
            }
        }

        // Pick first player randomly
        Random rand = new Random();
        redMove = rand.nextBoolean();
    }

    // Method to print the board **COMPLETE**
    public void printBoard() {
        // Label rows A-H
        char rowLbls = 'A';

        // Label columns 1-8
        System.out.println("   " + Color.BG_PURPLE + " |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  | " + Color.RESET);

        // Print the GameBoard
        for (Piece[] gB1 : gB) {
            System.out.println(
                    Color.BG_PURPLE + "---" + Color.RESET + Color.PURPLE + "---------------------------------------------------" + Color.RESET);
            System.out.print(
                    Color.BG_PURPLE + " " + rowLbls++ + " " + Color.PURPLE + " | " + Color.RESET);
            for (Piece gB11 : gB1) {
                if (gB11 != null) {
                    gB11.placePiece();
                    System.out.print(Color.PURPLE + " | " + Color.RESET);
                } else {
                    System.out.print(Color.PURPLE + "    | " + Color.RESET);
                }
            }
            System.out.println();
        }
        System.out.println(Color.BG_PURPLE + "¨¨¨" + Color.RESET + Color.PURPLE + "ˋ*-._.-*-._.-*-._.-*-._.-*-._.-*-._.-*-._.-*-._.-* " + Color.RESET);
        System.out.println();
        if (redMove == true) {
            System.out.println(Color.RED + "★·.¸¸.•´¯`•¸¸.•..>> Red's   Move! <<..•.¸¸•´¯`•.¸¸.·★" + Color.RESET);
        } else {
            System.out.println(Color.BLUE + "★·.¸¸.•´¯`•¸¸.•..>> Blue's  Move! <<..•.¸¸•´¯`•.¸¸.·★" + Color.RESET);
        }
        System.out.println();
    }

    // Method to test legal moves **IN PROGRESS**
    private boolean legalMove() {
        // Cannot move outside of the board
        if (sRow < 0 || sRow > 8 || sCol < 0 || sCol > 8 || dRow < 0
                || dRow > 8 || dCol < 0 || dCol > 8) {
            System.out.println("You cannot move your piece off of the board");
            return false;
        }

        // You must move from a space that contains your piece
        if (gB[sRow][sCol] == null) {
            System.out.println("There is no piece to move from this space");
            return false;
        }

        // Cannot move another player's piece
        if ((gB[sRow][sCol].rPiece && !redMove)
                || (!gB[sRow][sCol].rPiece && redMove)) {
            if (Game.redMove == true) {
                System.out.println("It is not Blue's turn to move");
            } else if (!(Game.redMove)) {
                System.out.print("It's not Red's turn to move");
            } else {
                return false;
            }
        }

        // Allow multidirectional movement when touching an opponents cell
        if (gB[dRow][dCol].rPiece && !redMove && sCol == (dCol - 1)) || (gB[dRow][dCol].bPiece && redMove &&  sCol == dCol && Math.abs(sRow - dRow) == 1) {
                System.out.print("Red has attacked Blue and gained a point!\n");
                bScore--;
                rScore++;
                return true;
            
        }

        /**
         * Instructions unclear: Is friendly fire allowed when not popping out
         * of a stock? if ((gB[dRow][sCol].bPiece && !redMove) && ((sCol ==
         * (dCol - 1)) && (Math.abs(sRow - dRow) == 1) || (Math.abs(sRow - dRow)
         * == 1))) { System.out.print("Blue attacked its own! Red gained a
         * point.\n"); bScore--; rScore++; return true; }
         *
         */
        // Allow multidirectional movement when touching an opponents cell
        if (gB[dRow][dCol].rPiece && !redMove && sCol == (dCol + 1)) || (gB[dRow][dCol].bPiece && redMove &&  sCol == dCol && Math.abs(sRow - dRow) == 1) {
            System.out.print("Red has attacked Blue and gained a point!\n");
            rScore--;
            bScore++;
            return true;
        }

        /**
         * Instructions unclear: Is friendly fire allowed when not popping out
         * of a stock? if ((gB[dRow][dCol].rPiece && redMove) && ((sCol == (dCol
         * + 1)) && (Math.abs(sRow - dRow) == 1) || (Math.abs(sRow - dRow) ==
         * 1))) { System.out.print("Red has attacked its own! Blue gained a
         * point.\n"); rScore--; bScore++; return true; }
         *
         */
        // Must only move legal directions otherwise
        if (!gB[sRow][sCol].direction(sRow, sCol, dRow, dCol)) {
            System.out.println("You cannot move in that direction");
            return false;
        }
        return true;
    }

    public void movePiece() {

        System.out.println("    ▁ ▂ ▄ ▅ ▆ ▇ █   Score   █ ▇ ▆ ▅ ▄ ▂ ▁");
        System.out.println("                Red: " + rScore + "          " + bScore + " :Blue");

        if (illegalMove) {
            System.out.print("\nIllegal move.  Please try again: ");
            illegalMove = false;
        } else if (redMove) {
            System.out.print("\nCommand Format: [Row Letter][Column #] to [Row Letter][Column #]\nExample Command: A2 to A3\nText Commands: 'score', 'lostpieces', 'moves'\nRed's move: ");
        } else {
            System.out.print("\nCommand Format: [Row Letter][Column #] to [Row Letter][Column #] \nExample Command: A2 to A3\nText Commands: 'score', 'lostpieces', 'moves'\nBlue's move: ");
        }

        move = input.nextLine();

        if (move.equalsIgnoreCase("quit")) {
            gameRunning = false;
            System.out.println("You've ended your game.");
            System.out.println("Red's score: " + rScore + "\nBlue's score: " + bScore);
        }

        String lower = move.toLowerCase();
        String[] userMove = lower.split(" ");

        sCol = (userMove[0].charAt(1) - '1');
        sRow = (userMove[0].charAt(0) - 'a');
        dCol = (userMove[2].charAt(1) - '1');
        dRow = (userMove[2].charAt(0) - 'a');

        if (legalMove()) {
            updateScore();
            gB[dRow][dCol] = gB[sRow][sCol];
            gB[sRow][sCol] = null;
            redMove = !redMove;
        } else {
            illegalMove = true;
            printBoard();
            movePiece();
        }
    }

    private void updateScore() {
        if (gB[dRow][dCol] == null) {
            return;
        }
        if (redMove) {
            rScore += gB[dRow][dCol].value();
            lostPieces.push("B");
        } else {
            bScore += gB[dRow][dCol].value();
            lostPieces.push("R");

        }
    }

}
