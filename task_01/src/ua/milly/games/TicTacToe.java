package ua.milly.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    private final Scanner input = new Scanner(System.in);

    public void play() {
        System.out.println("Welcome to Tic Tac Toe!");
        gameLoop(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " "), "X");
    }
    // Using recursion instead of Loops
    private void gameLoop(List<String> board, String currentPlayer) {
        printBoard(board);

        if (isWinner(board, "X")) { System.out.println("Player X wins!"); return; }
        if (isWinner(board, "O")) { System.out.println("Player O wins!"); return; }
        if (!board.contains(" ")) { System.out.println("It's a tie!"); return; }

        System.out.printf("Player %s, choose (0-8): ", currentPlayer);

        try {
            int move = Integer.parseInt(input.nextLine().trim());

            if (move < 0 || move > 8 || !board.get(move).equals(" ")) {
                System.out.println("Invalid move, try again.");
                gameLoop(board, currentPlayer);
                return;
            }

            List<String> newBoard = updateBoard(board, move, currentPlayer);
            String nextPlayer = currentPlayer.equals("X") ? "O" : "X";
            gameLoop(newBoard, nextPlayer);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number 0–8.");
            gameLoop(board, currentPlayer);
        }
    }

    // Creates a new board copy with the move applied (no mutation) (pure function)
    private List<String> updateBoard(List<String> board, int move, String player) {
        List<String> newBoard = new ArrayList<>(board); // copy to keep immutability
        newBoard.set(move, player);
        return List.copyOf(newBoard); // return as unmodifiable list
    }

    private void printBoard(List<String> b) { //Pure Function
        System.out.printf("""
            %s | %s | %s
            ---------
            %s | %s | %s
            ---------
            %s | %s | %s
            %n""",
                b.get(0), b.get(1), b.get(2),
                b.get(3), b.get(4), b.get(5),
                b.get(6), b.get(7), b.get(8));
    }
    // Pure function (Cause its just calculation lol)
    private boolean isWinner(List<String> b, String p) {
        int[][] wins = {
            {0,1,2},{3,4,5},{6,7,8}, // rows
            {0,3,6},{1,4,7},{2,5,8}, // cols
            {0,4,8},{2,4,6}          // diagonals
        };
        return Arrays.stream(wins) // Declarative Programming style (Instead of nested conditionals)
                .anyMatch(line -> b.get(line[0]).equals(p) &&
                                   b.get(line[1]).equals(p) &&
                                   b.get(line[2]).equals(p));
    }
}
