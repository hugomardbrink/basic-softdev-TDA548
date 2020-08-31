package exercises;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

/*
 * Program to calculate sum and average for non-negative integers
 *
 * See:
 * - Loops (while only)
 * - LoopAndAHalf
 *
 */
public class Ex3SumAvg {

    public static void main(String[] args) {
        new Ex3SumAvg().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int sum = 0;
        float average;
        boolean running = true;


        out.println("Type non-negative integers");

        while (running) {
            int input = sc.nextInt();
            if (input >= 0) {
                numbers.add(input);
                sum += input;
            }
            else{
                running = false;
            }
        }

        out.println(numbers);
        average = sum/numbers.size();
        out.println("sum: " + sum + ", average: " + average);
    }
}
