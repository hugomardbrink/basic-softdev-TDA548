package exercises;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 * The Rock, paper, scissor game.
 * See https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors
 *
 * This is exercising smallest step programming (no methods needed)
 *
 * Rules:
 *
 *       -----------  Beats -------------
 *       |                              |
 *       V                              |
 *      Rock (1) --> Scissors (2) --> Paper (3)
 *
 */
public class Ex7RPS {

    public static void main(String[] args) {
        new Ex7RPS().program();
    }

    final Random rand = new Random();
    final Scanner sc = new Scanner(in);

    void program() {

        int maxRounds = 5;
        int human;          // Outcome for player
        int computer;       // Outcome for computer
        int result= 0;         // Result for this round OBS
        int round = 0;      // Number of rounds
        int total = 0;      // Final result after all rounds

        // All code here ... (no method calls)

        out.println("Welcome to Rock, Paper and Scissors");

        for (int i = 0; i < maxRounds; i++) {
            round++;
            out.println("Round: " + round);

            out.println("Choose action: Rock = 1, Paper = 2, Scissor = 3 >");
            human = sc.nextInt();
            computer = (rand.nextInt(3) + 1);
            out.println("Computer choose: " + computer);

            if (human == computer){       }
            else if(human == 1) {
                if (computer == 2) {result--;}
                else {result++;}
            }
            else if(human == 2) {
                if (computer == 1) {result++;}
                else {result--;}
            }
            else {
                if (computer == 1) {result--;}
                else {result++;}
            }

            if (result == 0) {
                out.println("Draw");
            } else if (result > 0) {
                out.println("Human won.");
            } else {
                out.println("Computer won.");
            }
            total += result;
            result = 0;
            out.println("Score: " + total);
        }
        out.println("Game over! ");
        if (total == 0) {
            out.println("Draw");
        } else if (total > 0) {
            out.println("Human won.");
        } else {
            out.println("Computer won.");
        }
    }
}
