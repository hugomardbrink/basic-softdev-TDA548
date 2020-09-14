package exercises;

import java.util.Arrays;

import static java.lang.System.out;

/**
 *  Some theory mostly about references and identity
 *
 *  See
 *  - References
 *  - Overloading
 */
public class Ex3Theory {

    public static void main(String[] args) {
        new Ex3Theory().program();
    }

    void program() {

        // 1. Uncomment section below and you will get compile errors. Why?
/*
        int i = 1;
        char ch = '1';
        double d = 1.0;

        i = d;
        i = ch;
        ch = i;
        ch = d;
        d = i;
        d = ch;

        i = (int) d;
        (double) i = d;
*/

        /*
        1. integers are smaller than doubles so the compiler prevents potential loss of data
        2. chars are smaller than ints so the compiler prevents potential loss of data
        3. chars are smaller than doubles so the compiler prevents potential loss of data
        4. left side of the operation is not a variable but a expression

         */



        // 2. Uncomment and run.  What will be printed. Explain! ---------

        int[] i1 = {1, 2, 3};
        int[] i2 = {10, 11, 12, 13, 14, 15};
        int[] i3 = i1;

        out.println(i3[1]);
        i3 = i2;
        out.println(i3[1]);
        i1 = i2;
        out.println(i3[1]);
        i3 = i1;
        out.println(i3[1]);

        /*
        all operations after first print points on same memory
         */

        // 3. What will be printed. Explain! (methods below) ----------------
        int a = 1;
        int b = 2;
        swap(a, b);
        out.println(a + ", " + b);

        int[] arr = {1, 2};
        swap(arr);
        out.println(Arrays.toString(arr));

        Player p1 = new Player();
        Player p2 = new Player();
        p1.name = "olle";
        p1.points = 34;
        p2.name = "fia";
        p2.points = 56;

        swap( p1, p2 );
        out.println(p1.name + ", " + p2.name);
        /*
        because swap is a void and int a and b are not references to memory nothing will change and it will print 1, 2
        second print is actually points to memory so the swap will execute and will print 2, 1
        objects are also memory pointers and will also swap to fia, olle
         */

        // 4. What will be printed. Explain! Methods below ----------------
        int[] a1 = getArr();
        int[] a2 = getArr();

        out.println(a1.length == a2.length);
        out.println(a1[0] == a2[0]);
        out.println(a1 == a2);

        /*
        the expression checks if memory addresses are the same which they arent.
         */

        // 5. If uncommenting you'll get a compile error. Why? (methods below)
        //out.println(add(1,1));
        //doesnt match any of the add function, each one need 1 int and 1 double but only ints are provided
    }

    // ---------- Some methods used ---------------------------

    void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    void swap(int[] a) {
        int tmp = a[0];
        a[0] = a[1];
        a[1] = tmp;
    }

    void swap( Player p1, Player p2){
        String tmp = p1.name;
        p1.name = p2.name;
        p2.name = tmp;
    }

    int[] getArr() {
        int[] arr = {1, 2, 3};
        return arr;
    }

    double add(double d, int i) {
        return d + i;
    }

    double add(int i, double d) {
        return i + d;
    }

    class Player {
        String name;
        int points;
    }

}
