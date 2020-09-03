package exercises;

import java.util.Random;

import static java.lang.System.*;

/*
 *  Methods with array params and/or return value. Implement methods.
 *
 *  See:
 *  - MathMethods
 *  - ArrayMethods
 */
public class Ex2ArrayMethods {

    public static void main(String[] args) {
        new Ex2ArrayMethods().program();
    }

    final static Random rand = new Random();

    void program() {
        int[] arr = {1, 2, 2, 5, 3, 2, 4, 2, 7};  // Hard coded test data

        // Count occurrences of some element in arr
        out.println(count(arr, 2) == 4);      // There are four 2's
        out.println(count(arr, 7) == 1);

        // Generate array with 100 elements with 25% distribution of -1's and 1's (remaining will be 0)
        arr = generateDistribution(100, 0.25, 0.25);
        out.println(count(arr, 1) == 25);
        out.println(count(arr, -1) == 25);
        out.println(count(arr, 0) == 50);

        // Generate array with 14 elements with 40% 1's and 30% -1's
        arr = generateDistribution(14, 0.4, 0.3);
        out.println(count(arr, 1) == 6);
        out.println(count(arr, -1) == 4);

        for (int i = 0; i < 10; i++) {
            shuffle(arr);
            out.println();
        }
    }

    // ---- Write methods below this ------------
    int count(int[] arr, int searched) {
        int match = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == searched) {
                match++;
            }
        }
        return match;
    }

    int[] generateDistribution(int elements, double oneDist, double minOneDist) {
        int[] array = new int[elements];
        int one = (int) Math.round(elements * oneDist);
        int minOne = (int) Math.round(elements * minOneDist);

        for (int i = 0; i < array.length; i++) {
            if (one > 0) {
                array[i] = 1;
                one--;
            } else if (minOne > 0) {
                array[i] = -1;
                minOne--;
            } else{
                array[i] = 0;
            }
        }
        return array;
    }
        int random(int roof){
        return rand.nextInt(roof);
    }
    int[] shuffle(int[] array){

        for(int i = 0; i < array.length; i++){
            array = switchIndex(array, random(array.length));
        }
        for(int k = 0; k < array.length; k++) {
            out.print(array[k] + ", ");  // Does it look random?
        }
        return array;
    }
    int[] switchIndex(int[] array, int randomNum){
        int temp;
        int pos = (randomNum * array.length) % (array.length/2);

        temp = array[randomNum];
        array[randomNum] = array[pos];
        array[pos] = temp;

        return array;
    }

}