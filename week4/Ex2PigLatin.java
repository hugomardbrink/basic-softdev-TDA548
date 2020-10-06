package exercises;

import static java.lang.System.out;

/*
 *  Pig Latin, silly secret language
 *  https://en.wikipedia.org/wiki/Pig_Latin
 *
 *  See:
 *  - UseCharacter
 *  - UseStringBuilder
 *  - UseString
 *  - ShortForLoop
 *
 */
public class Ex2PigLatin {

    public static void main(String[] args) {
        new Ex2PigLatin().program();
    }

    void program() {

        out.println(toPigLatin("My name is Eric")
                .equals("yMay emanay isway Ericway"));
    }

    // ---------- Methods --------------------

    String toPigLatin(String sentence) {
        StringBuilder sb = new StringBuilder();
        String[] strs = sentence.split(" ");  // " " matches one space.

        for (String word : strs)
            sb.append(pigConvertWord(word));

        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }

    String pigConvertWord(String word) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sentence;

        if (checkVowel(word.charAt(0))) {
            sentence = sb.append(word).append("way ");
        } else {
            sentence = sb.append(word).reverse().append("ay ");
        }

        return sentence.toString();
    }

    boolean checkVowel(char ch) {
        boolean checkSmallerChar = (ch == 'e' || ch == 'o' || ch == 'u' ||
                ch == 'a' || ch == 'y' || ch == 'i');
        boolean checkBiggerChar = (ch == 'E' || ch == 'O' || ch == 'U' ||
                ch == 'A' || ch == 'Y' || ch == 'I');

        return checkBiggerChar || checkSmallerChar;
    }
}
