package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/*
 * Permutations of strings
 *
 * This is meant to use recursion. See
 * https://introcs.cs.princeton.edu/java/23recursion/
 * https://en.wikipedia.org/wiki/Recursion_(computer_science)
 *
 * See:
 * - UseString
 * - UseAList
 */
public class Ex7Permutations {

    public static void main(String[] args) {
        new Ex7Permutations().program();
    }

    void program() {
        List<String> ps = Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");
        List<String> perms = perm("abc");

        // Some checks
        out.println(perms.size() == 6);
        out.println(perms.containsAll(ps));
        out.println(!perms.contains("aab"));
        out.println(!perms.contains("abb"));

    }

    // -------- Methods -----------
    List<String> perm(String str) {
        List<String> perms = new ArrayList<>();
        char[] perm = str.toCharArray();
        int index = 0;

        permute(perm, index, perms);

        return perms;
    }

    void permute(char[] perm, int index, List<String> list) {

        if (index == perm.length - 1)
            list.add(String.valueOf(perm));

        for (int i = index; i < perm.length; i++) {
            swap(perm, index, i);
            permute(perm, index + 1, list);
            swap(perm, index, i);
        }
    }

    void swap(char[] perm, int i, int j) {
        char temp = perm[i];
        perm[i] = perm[j];
        perm[j] = temp;
    }

}