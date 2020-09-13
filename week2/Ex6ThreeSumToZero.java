package exercises;

import java.util.Arrays;

import static java.lang.System.out;

/*
    Find three elements in sorted array that adds up to 0.
    Return indices to the elements as an array.

 */
public class Ex6ThreeSumToZero {

    public static void main(String[] args) {
        new Ex6ThreeSumToZero().program();
    }


    void program() {
        int[] arr1 = {-25, -10, -7, -3, 2, 4, 8, 10}; // Must be sorted
        int[] arr2 = {0, 1, 2, 4, 8, 10};
        int[] arr3 = {-2, 1, 1};
        int[] arr4 = {0, 0, 0, 0};



        out.println(Arrays.toString(getThreeSum(arr1)));    // [1, 4, 6]
        out.println(Arrays.toString(getThreeSum(arr2)));    // []
        out.println(Arrays.toString(getThreeSum(arr3)));    // [0, 1, 2]
        out.println(Arrays.toString(getThreeSum(arr4)));    // [ 0, 1 ,3 ] or any
    }


    int[] bubbleSort (int[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }

    int[] getThreeSum (int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 2; i++)
            for (int j = i + 1; j < n - 1; j++)
                for (int k = j + 1; k < n; k++) {
                    if ((arr[i] + arr[j] + arr[k]) == 0) {
                        int[] newArr = {arr[i], arr[k], arr[j]};
                        return newArr;
                    }
                }
        int[] emptyArr = {};
        return emptyArr;
    }
}
