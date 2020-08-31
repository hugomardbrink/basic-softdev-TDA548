package exercises;

import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;

/*
 *
 * Program to calculate a persons BMI
 * See https://en.wikipedia.org/wiki/Body_mass_index
 *
 * Formula is: bmi = weight / height²     (kg/m²)
 *
 * See:
 * - F2C
 * - IO
 * - PrimitiveVariables
 * - Arithmetic
 */
public class Ex1BMI {

    public static void main(String[] args) {
        new Ex1BMI().program();
    }

    // This connects our program to the keyboard
    final Scanner sc = new Scanner(in);

    void program() {
        double  weight,  //declares variables with type: double for decimals
                height,  //double is also used for java math support
                bmi;

        // --- Input ---------
        out.println("Input your weight in kg > ");
        weight = sc.nextDouble(); //input for variable with double type
        out.println("Input your height in m > ");
        height = sc.nextDouble();

        // --- Process --------
        bmi = weight / Math.pow(height, 2); //divides weight with height^2

        // --- Output ---------
        out.println("Your BMI: " + bmi); //prints result
    }
}
