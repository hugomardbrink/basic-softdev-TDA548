package exercises;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 * Program to exercise arrays
 *
 * See:
 * - ArrayBasics
 */
public class Ex4Arrays {

    public static void main(String[] args) {
        new Ex4Arrays().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {
        int[] numarray = {0, 0, 0, 0, 0};
        int answer;

        for (int i = 0; i < numarray.length; i++){
            numarray[i] = sc.nextInt();
        }

        out.println("Array is: ");
        for (int i = 0; i < numarray.length; i++){
            out.print(numarray[i] + ", ");
        }
        out.println("\nSelect searched value: ");
        answer = sc.nextInt();

        for (int k = 0; k < numarray.length; k++){
            if (answer == numarray[k]){
                out.println("Your value is at index: " + k);
                return;
            }
        }
        out.println("Value not found.");
    }
}
