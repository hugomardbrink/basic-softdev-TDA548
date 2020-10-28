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
    final static String OP_NOT_FOUND = "Operator/Operand not found";

    // Definition of operators
    final static String OPERATORS = "+-*/^";

    // Method used in REPL
    double eval(String expr) {                           //evaluate input
        if (expr.length() == 0)                            //if nothing is typed display NaN
            return NaN;

        List<String> tokens = tokenize(expr);           //tokenize string
        List<String> postfix = infix2Postfix(tokens);   //infix -> postfix
        return evalPostfix(postfix);                    //calculate postfix
    }

    // ------  Evaluate RPN expression -------------------

    public double evalPostfix(List<String> postfix) {   //evaluate postfix
        Deque<Double> stack = new ArrayDeque<>();       //initiate stack

        for(String token : postfix) {                       //for all tokens in postfix
            if(Character.isDigit(token.charAt(0)))               //if token is a number push to stack
                stack.push(Double.parseDouble(token));               //format token into double
            else  {
                if (stack.size() >= 2)
                    stack.push(applyOperator(token, stack.pop(), stack.pop()));  //use operator in stack with the last 2 numbers in the stack
                else
                    throw new IllegalArgumentException(MISSING_OPERAND);          //if stack size is less than 2 then excess operator
            }
        }

        if (stack.size() == 1)
            return (stack.pop());
        else    // if stack size does not equal 1 it means that there are more than 1 number on the stack (or 0) and that would mean that there is a missing operator
            throw new IllegalArgumentException(MISSING_OPERATOR);
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
                    throw new IllegalArgumentException(DIV_BY_ZERO);
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
        Deque<String> stack = new ArrayDeque<>();

        for (String token : tokens) {                           //for each token
           if (Character.isDigit(token.charAt(0)))              //if digit add to postfix
               postFix.add(token);
           else if (isOperator(token.charAt(0)))                //if operator do according managing
                manageOperator(token, stack, postFix);
           else if (token.equals("("))                          //if left par then push to stack
               stack.push(token);
           else if (token.equals(")"))                          //if right par do according managing
                manageRightPar(stack, postFix);
        }

        popOperators(stack, postFix);

        if(!stack.isEmpty()) //if not empty then left par left in stack, error
            throw new IllegalArgumentException(MISSING_OPERATOR);

        return postFix;                                         //return postfix
    }

    void manageOperator(String token, Deque<String> stack, List<String> postFix) {

        while (!stack.isEmpty() &&                                              //while the stack is not empty
              (!stack.peek().equals("(")) &&                                    //and top stack is not left par
              ((getPrecedence(stack.peek()) > getPrecedence(token)) ||          //and if top stack precedence is greater than tokens
                      (getPrecedence(stack.peek()) == getPrecedence(token) &&       //or top stack and token have same precedence
                              getAssociativity(token) == Assoc.LEFT))) {            //and tokens associativity is left

            postFix.add(stack.pop());                                      //pop operator
        }
        stack.push(token);                                                          //push token regardless
    }

    void manageRightPar(Deque<String> stack, List<String> postFix) {                //manage right parenthesis
        while(!stack.isEmpty() && !stack.peek().equals("(")) {                      //while stack is not empty and not left par
            postFix.add(stack.pop());                                               //pop stack and add to postfix
        }

        if (!stack.isEmpty() && stack.peek().equals("("))                           //if next stack equals left then pop it
            stack.pop();
        else                                                                        //if not = unbalanced parenthesis
            throw new IllegalArgumentException(MISSING_OPERATOR);
    }

    Assoc getAssociativity(String op) {             //gets associativity
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    enum Assoc {            //declare assoc enum
        LEFT,
        RIGHT
    }

    void popOperators(Deque<String> stack, List<String> postFix) {               //pops all operators
        for (String token : stack)                                               //for all tokens in stack
            if (isOperator(token.charAt(0))) {                                   //if operator pop it and add to postfix
                postFix.add(stack.pop());
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

    boolean isOperator(char token) {                                    //returns true if token is a operator
        return OPERATORS.contains(Character.toString(token));           //if operator contains the token return true
    }

    // ---------- Tokenize -----------------------

    public List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        int upperBound;

        for (int i = 0; i < expr.length(); i++) {
            if (Character.isDigit(expr.charAt(i))) {        // special case for the token digit, point is to see if there are digits after the first digit for it to form a number
                upperBound = getNumUpperBound(i, expr);        // returns the position of the last digit in the number
                tokens.add(expr.substring(i, upperBound+1));      // adds the substring made of digits formed as a number to the list of tokens
                i = upperBound;                                   // jumps to index after number
            } else if ("+-*/^()".contains(Character.toString(expr.charAt(i))))      //checks if valid token
                tokens.add(Character.toString(expr.charAt(i)));   //add to tokenList
            else if (expr.charAt(i) != ' ')                         //if not space, num or valid token throw exception
                throw new IllegalArgumentException(OP_NOT_FOUND);
        }
        return tokens;                                              //return tokenList
    }

    int getNumUpperBound(int i, String expr) {                      //gets the end index of the number in the expression
        while (i+1 < expr.length() && Character.isDigit(expr.charAt(i+1))) //while inbounds and the next token is a number
            i++;                                                           //increment index

        return i;                                                           //return endIndex for number
    }
}
