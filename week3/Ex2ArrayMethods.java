package exercises;

import java.util.Arrays;

import static java.lang.System.*;

/*
 *  Some harder array methods
 */
public class Ex2ArrayMethods {


    public static void main(String[] args) {
        new Ex2ArrayMethods().program();
    }

    void program() {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};

        // Rotate all elements in the arr k steps to the right (in a circular fashion)
        // Assume arr.length > 0. NOTE: Original array changed!
        rotate(arr1, 3);
        out.println(Arrays.toString(arr1).equals("[6, 7, 8, 1, 2, 3, 4, 5]"));
        rotate(arr1, 0);
        out.println(Arrays.toString(arr1).equals("[6, 7, 8, 1, 2, 3, 4, 5]"));
        rotate(arr1, arr1.length);
        out.println(Arrays.toString(arr1).equals("[6, 7, 8, 1, 2, 3, 4, 5]"));
        rotate(new int[]{1}, 3);

        // Same as above but here we have a return value
        int[] r = rotate2(new int[]{1, 2, 3, 4, 5}, 2);  // Return value!
        out.println(Arrays.toString(r).equals("[4, 5, 1, 2, 3]"));
        r = rotate2(new int[]{1, 2, 3, 4, 5}, 5);  // Return value!
        out.println(Arrays.toString(r).equals("[1, 2, 3, 4, 5]"));


        int[] arr2 = {1, 2, 2, 3, 3};   // All sorted in increasing order
        int[] arr3 = {1, 2, 3, 4, 5};
        int[] arr4 = {1, 1, 1, 1, 1, 1};
        int[] arr5 = {1};

        // Remove all duplicates from arr2, ... (original unchanged, copy created)
        // NOTE: Assume arr is sorted in increasing order and > 0
        out.println(Arrays.toString(removeDupl(arr2)).equals("[1, 2, 3]"));
        out.println(Arrays.toString(arr2).equals("[1, 2, 2, 3, 3]"));   // arr2 unchanged!
        out.println(Arrays.toString(removeDupl(arr3)).equals("[1, 2, 3, 4, 5]"));
        out.println(Arrays.toString(removeDupl(arr4)).equals("[1]"));
        out.println(Arrays.toString(removeDupl(arr5)).equals("[1]"));


        arr1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        // Use fact that array is sorted to search efficiently
        out.println(search(arr1, 1) == 0);
        out.println(search(arr1, 3) == 2);
        out.println(search(arr1, 8) == 7);

    }

    // -------------- Methods --------------------------
    void rotate(int[] arr, int length){
    int temp, j;

        for(int i = arr.length; i > length; i--){
            temp = arr[0];
            for(j = 0; j < arr.length-1; j++){
                arr[j] = arr[j+1];
            }
            arr[j] = temp;
        }
    }

    int[] rotate2(int[] arr, int length){
        int temp,
            j;

        for(int i = arr.length; i > length; i--){
            temp = arr[0];
            for(j = 0; j < arr.length-1; j++){
                arr[j] = arr[j+1];
            }
            arr[j] = temp;
        }
        return arr;
    }

    int[] removeDupl(int[] memArr){
        int found = 0;
        int[] arr = makeMemoryCopy(memArr);


        for(int i = 0; i < arr.length - 1; i++)
            if(arr[i] == arr[i+1]){
                arr[i] = -1;
                found++;
            }

        int[] newArr = new int[arr.length - found];
        int indexSpot = 0;

        for(int k = 0; k < newArr.length ; k++)
            for(int j = indexSpot; j < arr.length; j++)
                if(arr[j] != -1) {
                    newArr[k] = arr[j];
                    indexSpot = j + 1;
                    break;
                }
        return newArr;
    }

    int[] makeMemoryCopy(int[] arr){
        int[] copy = new int[arr.length];

        for(int i = 0; i < arr.length; i++)
            copy[i] = arr[i];

        return copy;
    }

    int search(int[] arr, int searched) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == searched) {
                return i;
            }
        }
        return -1;
    }
}



