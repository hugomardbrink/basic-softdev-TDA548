package exercises;

/*
 *  For each of the section below. Uncomment and explain
 *
 */
import static java.lang.System.out;

public class Ex4Theory {

    public static void main(String[] args) {
        new Ex4Theory().program();
    }


    void program() {

        // 1. -------------------------------------------

        // Uncomment and run. Which value is correct for the volume of a sphere?

        int r = 10;
        double pi = 3.141;
        double vol1 = 4 * pi / 3 * r * r * r;
        double vol2 = pi * r * r * r * (4 / 3);
        double vol3 = 4 / 3 * pi * r * r * r;
        double vol4 = 4 / (3 * pi) * r * r * r;

        out.println(vol1);
        out.println(vol2);
        out.println(vol3);
        out.println(vol4);
        /*
        Because of the 4 and 3 being integers the division narrows and becomes 1 which rendered vol2 and vol3 useless
        vol4 is mathematically wrong hence the bad result. vol1 works because pi/3 returns a double division which makes
        the expression valid.
         */

        // 2. ---------------------------------------------------

        // Uncomment section below and you will get compile errors. Why?
        /*
        {
            int x = 0;
            {
                int x = 0;
                int y = 0;
                out.println(x);
                out.println(y);
            }
            int x = 0;
            int y = 0;
            out.println(x);
            out.println(y);
        }
        out.println(x);
        out.println(y);
        */
        /*
        The variables are declared in blocks and are local to said block and can't be reached from the outside hence the compile error
         */
        // 3. ----------------------------------------------------------

        // Uncomment and run. Explain result!

        double d1 = 1.0;
        double d2 = 1.0;
        d1 = d1 - 0.7 - 0.3;
        d2 = d2 - 0.6 - 0.4;
        out.println(d1 == 0);
        out.println(d2 == 0);
        out.println(d1 == d2);
        /*
        Computers uses binary radix which sometimes makes it impossible to compute decimals accurately, computers WILL
        get a few decimals wrong in the calculation hence why the equivalence wont work. .6 and .4 is works in binary.
         */

        // 4. -----------------------------------------------------

        // Uncomment and run. Explain output!

        out.println(1 + 2);
        out.println("2 + 1");
        out.println(1 + 2.0 + "a");
        out.println("a" + 1 + 2);
        out.println('a' + 1 + 2);
        out.println("a" + 'a');


        // 5. ---------------------------------
        // Why is there no return type for a constructor?
        /*
        first is simple, two integers adds up to the sum
        second is a text just displaying the numbers and operator in a particular order, doesnt have any mathematical
        implications
        third is a addition between a double and integer resulting in a double of 3.0 value that later gets combined with
        the letter a in string format creating a string output of 3.0a
        fourth is special; the a is now a char character which is eligible for mathematical computation, the program
        converts a into the ascii number and completed the addition resulting in 100
        The last does not result in a mathematical computation due to it being added to a string meaning its getting
        added to the string. This is due to the languages having this relation between the different types.
         */

        }
}
