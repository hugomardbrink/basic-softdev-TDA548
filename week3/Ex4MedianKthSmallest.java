package exercises;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Even more array methods, possibly even harder ...
 */
public class Ex4MedianKthSmallest {

    public static void main(String[] args) {
        new Ex4MedianKthSmallest().program();
    }

    void program() {
        int[] arr1 = {9, 3, 0, 1, 3, -2};

        out.println(!isSorted(arr1));  // Is sorted increasing? No not yet!

        sort(arr1);     // Sort in increasing order original order lost!

        out.println(Arrays.toString(arr1).equals("[-2, 0, 1, 3, 3, 9]"));

        out.println(isSorted(arr1));  // Is sorted increasing? Yes!

        int[] arr2 = {5, 4, 2, 1, 7, 0, -1, -4, 12};
        int[] arr3 = {2, 3, 0, 1};
        out.println(median(arr2) == 2);    // Calculate median of elements
        out.println(median(arr3) == 1.5);

        int[] arr4 = {2, 3, 0, 1, 5, 4};
        int[] arr5 = {5, 4, 2, 2, 1, 7, 4, 0, -1, -4, 0, 0, 12};
        out.println(kSmallest(arr4, 2) == 1);   // Second smallest is 1
        out.println(kSmallest(arr5, 5) == 2);   // 5th smallest is 2

    }

    // ---------- Write methods here --------------
    boolean isSorted(int[] arr) {
        for (int ele = 0; ele < arr.length - 1; ele++) {
            if (arr[ele] > arr[ele + 1]) {
                return false;
            }
        }
        return true;
    }

    void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    double median(int[] arr) {
        double median;

        sort(arr);
        median = arr.length % 2;
        if (median == 1) {
            median = arr[(arr.length / 2)];
        } else {
            median = arr[(arr.length / 2)] + arr[((arr.length / 2) - 1)];
            median /= 2;
        }
        return median;
    }

    int kSmallest(int[] arr, int roof) {
        int unique = 0;

        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (unique >= roof) {
                return arr[i-1];
            } else if (arr[i] != arr[i + 1]) {
                unique++;
            }
        }
        return 0;
    }
}
