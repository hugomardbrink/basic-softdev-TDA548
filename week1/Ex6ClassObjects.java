package exercises;

import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 *  Using class objects to represent heroes
 *  https://en.wikipedia.org/wiki/Gladiators_(1992_UK_TV_series)
 *
 * See:
 * - ClassObjects
 * - MethodsArrObj
 */
public class Ex6ClassObjects {

    public static void main(String[] args) {
        new Ex6ClassObjects().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {
        int heroCount = 0; // Would preferably be in class but nested class prevents it

        Hero obj[] = new Hero[2];
        for (int i = 0; i < 2; i++) {
            heroCount++;
            obj[i] = new Hero(heroCount);
        }
        if (obj[0].strength > obj[1].strength)
            out.println(obj[0].name + " is stronger.");
        else if (obj[0].strength < obj[1].strength)
            out.println(obj[1].name + " is stronger.");
        else
            out.println(obj[0].name + " and " + obj[1].name + " are equally strong.");

    }

    // ------ The class to use  -----------
    // A class for objects that describes a Hero
    class Hero {
        String name;
        int strength;

        Hero(int objCounter) { //constructor
            out.println("What's the name of Hero " + objCounter + "?" );
            name = sc.nextLine();

            out.println("How strong is " + name + "?");
            strength = sc.nextInt();
            sc.nextLine();
        }
    }
}
