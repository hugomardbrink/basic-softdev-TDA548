package exercises;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 *
 *  Use a Stack to check parentheses (balanced and correct nesting)
 *  The parentheses are: (), [] and {}
 *
 *  See:
 *  - UseAStack
 *  - WhyInterface
 *  - SwitchStatement
 *
 */
public class Ex4CheckParen {

    public static void main(String[] args) {
        new Ex4CheckParen().program();
    }

    void program() {
        // All should be true
        out.println(checkParentheses("()"));
        out.println(checkParentheses("(()())"));
        out.println(!checkParentheses("(()))")); // Unbalanced
        out.println(!checkParentheses("((())")); // Unbalanced

        out.println(checkParentheses("({})"));
        out.println(!checkParentheses("({)}"));  // Bad nesting
        out.println(checkParentheses("({} [()] ({}))"));
        out.println(!checkParentheses("({} [() ({)})"));  // Unbalanced and bad nesting

    }

    // ----------- Methods -------------------------

    boolean checkParentheses(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        str = str.replace(" ", "");

        char[] ch = str.toCharArray();
        char end, stackPop;
        boolean checkNull = false;

        for(char i : ch) {
            switch(i) {
                case '(':
                    stack.push('(');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case '{':
                    stack.push('{');
                    break;
                default:
                    end = checkEnds(i);
                    checkNull = stack.size() == 0;
                    if (!checkNull && end != stack.pop())
                        return false;
                    else if (checkNull)
                        return false;
                    break;
            }
        }
        return stack.size() == 0;
    }


    char checkEnds(char ch) {
        switch(ch) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return '0';
        }
    }

}
