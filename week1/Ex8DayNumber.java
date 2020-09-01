package exercises;

import java.util.Scanner;
import static java.lang.System.*;

/*
 *   Program to calculate the day number for same day in a given year.
 *   - To check solution, see http://mistupid.com/calendar/dayofyear.htm
 *
 *   This is exercising functional decomposition
 *   Assume you have a top level method solving the problem. Break down
 *   top level method into smaller methods solving parts of the problem etc.
 *   During this we run tests to make sure the methods works as expected.
 *   Combine the method to solve the problem.
 *
 */
public class Ex8DayNumber {

    public static void main(String[] args) {
        new Ex8DayNumber().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {

        out.print("Input the year > ");
        int year = sc.nextInt();
        out.print("Input the month number > ");
        int month = sc.nextInt();
        out.print("Input the day number > ");
        int day = sc.nextInt();


        int dayNbr = calcDays(month, checkLeapyear(year), day);
        printResult(year, month, day, dayNbr, checkLeapyear(year));
    }

    void printResult(int year, int month, int day, int dayNbr, boolean leapyear) {
        out.println("Ordinal number for " + day + "/" + month + " in " + year + " is: " + dayNbr);
        if(leapyear)
            out.println(year + " is a leapyear.");
        else
            out.println(year + " is not a leapyear");
    }

    boolean checkLeapyear(int year){
        if (year % 4 != 0)
            return false;
        else if (year % 400 == 0)
            return true;
        else if (year % 100 == 0)
            return false;
        else
            return true;
        }

    int calcDays(int month, boolean leapyear, int days) {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int daySum = 0;

        for (int i = 0; i < (month - 1); i++) {
            if (leapyear && i == 1)
                daySum += 29;
            else
                daySum += monthDays[i];
        }
        return daySum + days;
    }
}
