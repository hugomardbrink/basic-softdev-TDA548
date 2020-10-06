package exercises;

import static java.lang.System.out;

/**
 * A String problem
 * <p>
 * See:
 * - UseString
 */
public class Ex5StringOrdering {

    public static void main(String[] args) {
        new Ex5StringOrdering().program();
    }

    void program() {

        // Yes, "aa bb cc" is ordered like "abc" because all
        // a's are before all b's that are before all c's
        out.println(isOrdered("abc", "aa bb cc"));
        // Yes, all a's before all b's
        out.println(isOrdered("ab", "aa eee bb ddd cc"));
        // Yes, all e's before all c's
        out.println(isOrdered("ec", "aa eee becb c dddc"));

        // Not all c's are before all b's
        out.println(!isOrdered("acb", "aa bb cc"));
        // Not all b's before all c's
        out.println(!isOrdered("abc", "aa bb ccc b"));
        // No!
        out.println(!isOrdered("bac", "aa eee bbb ddd ccc"));

        // Degenerate cases
        out.println(isOrdered("a", "aa bb cc"));
        out.println(isOrdered("", "aa bb cc"));
        out.println(isOrdered("abc", ""));
        out.println(!isOrdered("ax", "aa bb cc"));
    }


    // -------- Methods ---------------

    boolean isOrdered(String order, String text) {
        int orderNum = 0;
        boolean checkUse = checkUsage(order, text);

        for (char ch : order.toCharArray()) {
            orderNum++;
            text = text.replaceAll(Character.toString(ch), Integer.toString(orderNum));
        }
        text = text.replaceAll(" ", "");

        for(char ch : text.toCharArray())
            if(Character.isLetter(ch))
                text = text.replaceAll(Character.toString(ch),"");

        char[] newText = text.toCharArray();


        return checkOrder(newText) && checkUse;
    }

    boolean checkUsage(String order, String text)  {
        if ((order == "") || (text == ""))
            return true;


        for(char ch : order.toCharArray())
            if(!text.contains(Character.toString(ch)))
                return false;

            return true;
        }

    boolean checkOrder(char[] text) {

        for(int i = 0; i < text.length; i++)
            for(int j = 0; j < i; j++)
                if(text[i] < text[j])
                    return false;

                return true;
     }
}















