package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 *   Extract numbers form Strings
 *
 *   See:
 *  - UseCharacter
 *  - UseStringBuilder
 *  - UseAList
 *  - WhyInterface
 *  - Exceptions
 */
public class Ex3ReadNumber {

    public static void main(String[] args) {
        new Ex3ReadNumber().program();
    }

    void program() {
        List<String> numbers = new ArrayList<>();

        // Argument 0 is index to start looking for digits.
        // Return value is index directly after last read digit
        // The number should be in the list numbers (method should add number to list)

        out.println(readNumber(numbers, "1", 0) == 1);
        out.println(numbers.contains("1"));
        numbers.clear();

        out.println(readNumber(numbers, "123", 0) == 3);
        out.println(numbers.contains("123") && !numbers.contains("1"));
        numbers.clear();

        out.println(readNumber(numbers, "123abc", 0) == 3);
        out.println(numbers.contains("123"));
        numbers.clear();

        out.println(readNumber(numbers, "12345abc", 0) == 5);
        out.println(numbers.contains("12345"));
        numbers.clear();

        out.println(readNumber(numbers, "abc123abc", 3) == 6);
        out.println(numbers.contains("123"));

        // Empty string is not accepted will throw exception
        try {
           // out.println(readNumber(numbers, "", 0) == 0);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());   // Should print "Expr length is 0" or similar
        }
    }

    // ----------- Methods-----------------------------------
    int readNumber(List<String> list, String text, int indexStart) {
        char[] newList = text.toCharArray();
        StringBuilder num = new StringBuilder();
        int found = 0;


        for(int i = indexStart; i < newList.length; i++)
            if (Character.isDigit(newList[i])) {
                found++;
                num.append(newList[i]);
            }

        list.add(num.toString());
        return found+indexStart;
    }



}
