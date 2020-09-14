package exercises;

import java.util.Arrays;

import static java.lang.System.out;

/*
 *  Some puzzlers
 *  General improvement of programming skills
 *
 *  See:
 *  - LoopPuzzler
 */
public class Ex1LoopPuzzlers {

    public static void main(String[] args) {
        new Ex1LoopPuzzlers().program();
    }


    void program() {
            multiplicationTable(10);
            out.println();
            patternB(10);
            out.println();
            patternC(10);
    }

    void multiplicationTable(int multiple) {
        int[][] table = new int[multiple][multiple];

        for (int c = 0; c < table.length; c++)
            for (int r = 0; r < table[0].length; r++) {
                table[c][r] = (c + 1) * (r + 1);
            }

        for (int i = 0; i < table.length; i++) {
            for (int k = 0; k < table[0].length; k++) {
                if (table[i][k] < 10) {
                    out.print(table[i][k] + "  ");
                } else {
                    out.print(table[i][k] + " ");
                }
            }
            out.println();
        }
    }

    void patternB(int patternLimit) {
        for (int i = 1; i <= patternLimit; i++) {
            for (int k = 1; k <= i; k++) {
                out.print('X');
            }
            out.println();
        }
    }

    void patternC(int patternLimit){

       for(int i = 1; i <= patternLimit; i++) {
           for (int k = 1; k < i; k++) {
               out.print(" ");
           }
           out.print("XXXXXXXXXX\n");
       }
    }
}

