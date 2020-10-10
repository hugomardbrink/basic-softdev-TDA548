package calc;

import java.util.*;

import static java.lang.Double.NaN;
import static java.lang.Math.pow;


/*
 *   A calculator for rather simple arithmetic expressions
 *
 *   This is not the program, it's a class declaration (with methods) in it's
 *   own file (which must be named Calculator.java)
 *
 *   NOTE:
 *   - No negative numbers implemented
 */
class Calculator {

    // Here are the only allowed instance variables!
    // Error messages (more on static later)
    final static String MISSING_OPERAND = "Missing or bad operand";
    final static String DIV_BY_ZERO = "Division with 0";
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    // Definition of operators
    final static String OPERATORS = "+-*/^";

    // Method used in REPL
    double eval(String expr) {                           //evaluate input
        if (expr.length() == 0) {                        //if nothing is inputed display NaN
            return NaN;
        }

        List<String> tokens = tokenize(expr);           //tokenize string
        if(tokens == null)                              //if the input is illegal
            return NaN;                                 //display NaN

        List<String> postfix = infix2Postfix(tokens);   //infix -> postfix

        return evalPostfix(postfix);                    //calculate postfix
    }

    // ------  Evaluate RPN expression -------------------

    public double evalPostfix(List<String> postfix) {   //evaluate postfix
        Deque<Double> stack = new ArrayDeque<>();       //initiate stack
        char token;                                     //declare token char

        for(String s : postfix) {                       //for all tokens in postfix
            token = s.charAt(0);                        //format string to char

            if(Character.isDigit(token))               //if token is a number push to stack
                stack.push(toDouble(s));               //format s into double
            else
                stack.push(applyOperator(Character.toString(token), //use operator in stack with the last 2 numbers in the stack
                          toDouble(stack.pop().toString()),
                          toDouble(stack.pop().toString())));
        }

        return stack.pop();                                         //return final calculation
    }

    double applyOperator(String op, double d1, double d2) {
        switch (op) {                                              //do addition
            case "+":
                return d1 + d2;
            case "-":                                              //do subtraction
                return d2 - d1;
            case "*":                                              //do multiplication
                return d1 * d2;
            case "/":                                              //do division
                if (d1 == 0) {                                     //if divided by 0 return NaN
                    return NaN;
                }
                return d2 / d1;
            case "^":                                              //do power of
                return pow(d2, d1);
        }
        throw new RuntimeException(OP_NOT_FOUND);                 //crash if error
    }

    // ------- Infix 2 Postfix ------------------------

    public List<String> infix2Postfix(List<String> tokens) {    //turns infix into postfix
        List<String> postFix = new ArrayList<>();               //initiate lists and variables
        Deque<Character> stack = new ArrayDeque<>();
        char ch;


        for(String s : tokens) {                                //for all tokens in loop
            ch = s.charAt(0);

            if (Character.isDigit(ch))                          //if digit
                postFix.add(s);                                 //add to postfix
            else
                manageStack(ch, stack, postFix);                //if not digit manage stack
        }

        popUntilStop(stack, postFix);                           //when all tokens read pop all operators

        return postFix;                                         //return postfix
    }

    void manageStack(char token, Deque<Character> stack, List<String> postFix) {   //manages stack if token is not num
        switch (token) {
            case ')':                                                             //if token is left par pop until par
                popUntilStop(stack, postFix);
                return;
            case '(':                                                             //if start par push it
                stack.push(token);
                return;
            case '^':                                                            //if operator manage with precedence
            case '*':
            case '/':
            case '+':
            case '-':
                manageWithPrecedence(token, stack, postFix);
                return;
            default:                                                            //if somehow illegal crash program
                throw new RuntimeException(MISSING_OPERATOR);
        }
    }

    void manageWithPrecedence(char token, Deque<Character> stack, List<String> postFix) {  //manages with precedence

                        //if stack is empty or the value on top of the stack is greater or equal to token then pop until
                        //either parenthesis or stacks end
            if (stack.isEmpty() || getPrecedence(stack.peekLast().toString()) >= getPrecedence(Character.toString(token)))
                popUntilStop(stack, postFix);

            stack.push(token);         //push token
    }

    void popUntilStop(Deque<Character> stack, List<String> postFix) {
        for (char token : stack)                                               //for all tokens in stack
            if (token != '(') {                                                //if not parenthesis then pop and add
                postFix.add(stack.pop().toString());
            } else if (!stack.isEmpty()) {                                     //if stack is not empty then pop
                stack.pop();
                return;
            }
    }

    int getPrecedence(String op) {                                          //gets precedence for all operators
        if ("+-".contains(op)) {
            return 2;
        } else if ("*/".contains(op)) {
            return 3;
        } else if ("^".contains(op)) {
            return 4;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);                       //crashes if no op
        }
    }

    double toDouble(String op) {                                            //turns string into double
        return Double.parseDouble(op);
    }

    // ---------- Tokenize -----------------------

    public List<String> tokenize(String expr) {                         //turn infix into tokens
        List<String> tokens = new ArrayList<>();

        expr = expr.replaceAll(" ", "");                //remove spaces

        for(char token : expr.toCharArray())
            tokens.add(Character.toString(token));                      //for each token add it to list

        if (!checkValidity(expr))                                       //if not legal expression then return null
            return null;

        return tokens;                                                  //if legal return infix tokenized
    }

    boolean checkValidity(String expr) {                                //checks if expression is legal

        for(char token : expr.toCharArray())                            //for each token check if token is valid
            if(!validCharacter(token))
                return false;
                                    //return true if it contains numbers, has legal parenthesis and is in a valid order
        return validOrder(expr) && checkParentheses(expr) && containNumbers(expr);
    }

    boolean checkParentheses(String expr) {             //checks parenthesis
        Deque<Character> stack = new ArrayDeque<>();

        for(char token : expr.toCharArray()) {          //for each token
            if (token == '(') {                         //if start push it
                stack.push(token);
            } else if (token == ')') {                  //if end try to pop and if success return true
                try {
                    stack.pop();
                } catch (Exception e) {                //if fail then unbalanced
                    return false;
                }
            }
        }
        return stack.size() == 0;                       //if ( is still left then unbalanced
    }

    boolean containNumbers(String expr) {              //if expression have number(s) return true
        for(char token : expr.toCharArray())
            if(Character.isDigit(token))
                return true;

        return false;
    }

    boolean validCharacter(char token) {               //if is a operator a parenthesis or digit then return true
        return  isOperator(token) ||
                Character.isDigit(token) ||
                token == '(' || token == ')';
    }

    boolean validOrder(String expr) {                               //checks order of expression
        boolean inBounds, startPar, endPar;
        char [] exprCh = expr.toCharArray();

        for (int i = 0; i < exprCh.length-1; i++) {
            inBounds = i != 0 && i != exprCh.length - 1;

            //  "(" must have a operator behind it and must have a number after, can not have opposite parenthesis adj.
            //  ")" must have a operator after it and a number behind it, can not have opposite parenthesis adjacent
            startPar = inBounds &&
                    (!Character.isDigit(exprCh[i - 1]) && exprCh[i-1] != ')'
                    || Character.isDigit(exprCh[i+1]) && exprCh[i+1] != ')');
            endPar = inBounds &&
                    (Character.isDigit(exprCh[i - 1]) && exprCh[i-1] != '('
                    || (!Character.isDigit(exprCh[i+1])) && exprCh [i+1] != '(');


            if (isOperator(exprCh[i]) && isOperator(exprCh[i + 1]))     //if 2 operators are adjacent the error
                return false;
            else if (exprCh[i] == '(' && startPar)                      //checks both parenthesis
                return false;
            else if (exprCh[i] == ')' && endPar)
                return false;
        }

        return true;                                                    //return true if all checks out
    }

    boolean isOperator(char token) {                                    //returns true if token is a operator
        for (char ch : OPERATORS.toCharArray())
            if(token == ch)
                return true;

        return false;
    }
}
