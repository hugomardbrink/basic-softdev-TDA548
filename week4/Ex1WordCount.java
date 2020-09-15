package exercises;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 * Count number of words in some text
 *
 * See:
 * - UseCharacter
 * - UseString
 */
public class Ex1WordCount {

    public static void main(String[] args) {
        new Ex1WordCount().program();
    }

    void program() {
        out.println(countWords("") == 0);
        out.println(countWords("hello") == 1);
        out.println(countWords(" hello ") == 1);
        out.println(countWords("hello world") == 2);
        out.println(countWords("hello        world") == 2);
        out.println(countWords("   hello        world  ") == 2);
        String s = "Education is what remains after one has forgotten what one has learned in school.";
        out.println(countWords(s) == 14);

    }

    //--------------- Methods -----------------

    int countWords(String text) {
        boolean inWord = false;
        int size = 0;
        char[] charArr = stringToChar(text);

        for(int i = 0; i < charArr.length; i++) {
            boolean checkLetter = (charArr[i] != ' ');

            if (!checkLetter){
                inWord = false;
            } else if (checkLetter && !inWord) {
                inWord = true;
                size++;
            }
        }
    return size;
    }

    char[] stringToChar(String text) {
        char[] charArr = new char[text.length()];
        for(int i = 0; i < text.length() ; i++)
            charArr[i] = text.charAt(i);

        return charArr;
    }
}
