package exercises;

import java.util.Arrays;
import java.util.Random;

import static java.lang.System.out;

/*
 * Implement generic versions of shuffle and sort
 *
 *  See:
 *  - WrapperTypes
 *  - GenericMethod
 */
public class Ex6GenericMethods {

    public static void main(String[] args) {
        new Ex6GenericMethods().program();
    }

    final Random rand = new Random();

    void program() {

        // Working with wrapper types, generic methods only work with reference types
        Integer[] is = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String origIs = Arrays.toString(is);
        String[] ss = {"a", "b", "c", "d", "e"};
        String origSS = Arrays.toString(ss);

        // diffIndex works for all arrays reference types
        out.println(diffIndex(is, is) == -1);
        out.println(diffIndex(ss, ss) == -1);

        // shuffle should be a generic method, so we can use it for Integers ...
        shuffle(is);
        out.println(!Arrays.toString(is).equals(origIs));  // May be false, but unlikely
        // ... and here for String.
        shuffle(ss);
        out.println(!Arrays.toString(ss).equals(origSS));

        // sort should also be generic (this is hard)
        sort(is);
        out.println(Arrays.toString(is).equals(origIs));
        sort(ss);
        out.println(Arrays.toString(ss).equals(origSS));

    }

    // ------- Methods -------------------------
    <T> int diffIndex(T[] arr1, T[] arr2) {
        int length;
        if (arr1.length < arr2.length)
            length = arr1.length;
        else
            length = arr2.length;

        for (int i = 0; i < length; i++)
            if (arr1[i] != arr2[i])
                return i;
        return -1;
    }

    <T> void sort(T[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
    }

    <T> void shuffle(T[] array){
        for(int i = 0; i < array.length; i++){
            array = switchIndex(array, rand.nextInt(array.length));
        }
    }

    <T> T[] switchIndex(T[] array, int randomNum){
        T temp;
        int pos = (randomNum * array.length) % (array.length/2);

        temp = array[randomNum];
        array[randomNum] = array[pos];
        array[pos] = temp;

        return array;
    }

}
