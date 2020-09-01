package exercises;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

/*
 *  The TicTacToe Game
 *  See https://en.wikipedia.org/wiki/Tic-tac-toe.
 *
 *  This is exercising functional decomposition and testing
 *  - Any non trivial method should be tested (in test() method far below)
 *  - IO methods never tested.
 *
 *  NOTE: Just use an array for the board (we print it to look square, see plotBoard())
 *
 */
public class Ex9TicTacToe {

    public static void main(String[] args) {
        new Ex9TicTacToe().program();
    }

    final Scanner sc = new Scanner(in);
    final Random rand = new Random();
    final char EMPTY = '-';        // This is so that we easy can change the value in one place

    void program() {
        Player p1 = new Player("olle", 'X');
        Player p2 = new Player("fia", 'O');
        Player current = null;         // For now
        Player winner = null;
        char[] board = createBoard();  // alt. { EMPTY, EMPTY, ... }

        out.println("Welcome to Tic Tac Toe, board is ...");
        plotBoard(board);
        while (winner == null) {
            current = p1;
            move(board, getPlayerSelection(p1), p1);
            winner = checkWin(board,p1);

            if (winner != null)
                break;
            else {
                current = p2;
                move(board, getPlayerSelection(p2), p2);
                winner = checkWin(board, p2);
            }

        }
        out.println("Game over!");
        plotBoard(board);

        if (winner != null)
            out.println("Winner is " + current.name);
         else
            out.println("Draw");
    }


    // ---------- Methods below this ----------------

    char[] move(char[] board, int selection, Player player){
        board[selection] = player.mark;
        plotBoard(board);
        return board;
    }

    Player checkWin(char[] board, Player player){
            if ((board[0] == player.mark) && (board[1] == player.mark) && (board[2] == player.mark))
                return player;
            else if((board[3] == player.mark) && (board[4] == player.mark) && (board[5] == player.mark))
                return player;
            else if((board[6] == player.mark) && (board[7] == player.mark) && (board[8] == player.mark))
                return player;
            else if((board[0] == player.mark) && (board[3] == player.mark) && (board[6] == player.mark))
                return player;
            else if((board[1] == player.mark) && (board[4] == player.mark) && (board[7] == player.mark))
                return player;
            else if((board[2] == player.mark) && (board[5] == player.mark) && (board[8] == player.mark))
                return player;
            else if((board[0] == player.mark) && (board[4] == player.mark) && (board[8] == player.mark))
                return player;
            else if((board[3] == player.mark) && (board[4] == player.mark) && (board[6] == player.mark))
                return player;
            else
                return null;
        }


    char[] createBoard() {
        char[] board = new char[9];
        for (int i = 0; i < board.length; i++)
            board[i] = EMPTY;

        return board;
    }

    int getPlayerSelection(Player player) {
        int selection;
        while (true) {
            out.println("Player is " + player.name + "(" + player.mark + ")");
            out.print("Select position to put mark (0-8) > ");
            selection = sc.nextInt();
            if (0 <= selection && selection <= 8)
                break;

            out.println("Bad choice (0-8 allowed)");
        }
        return selection;
    }

    void plotBoard(char[] board) {
        for (int i = 0; i < board.length; i++) {
            out.print(board[i] + " ");
            if ((i + 1) % 3 == 0)
                out.println();
        }
    }

    // A class (blueprint) for players.
    class Player {
        String name;
        char mark;
        Player(String name, char mark) {
            this.name = name;
            this.mark = mark;
        }
    }

}