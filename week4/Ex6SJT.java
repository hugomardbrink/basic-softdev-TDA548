package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/*
   Using Steinhaus-Johnson-Trotter (non-recursive) algorithm for permutations
   See https://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm

   See:
   - UseString
   - UseAList
 */
public class Ex6SJT {

    public static void main(String[] args) {
        new Ex6SJT().program();
    }

    private void program() {
        // Call permutation method
        List<String> perms = permutationSJT("123456");  // Replace null with permutationSJT("123456");

        out.println(perms.size());  // 720
        for (String s : perms) {
            out.println(s);
        }

        int count = 0;
        for (String s : perms) {
            if (s.equals("123456")) {
                count++;
            }
        }
        out.println(count == 1);
    }

    List<String> permutationSJT(String perm) {
        StringBuilder s = new StringBuilder();

        List<String> permList = new ArrayList<>();
        int[] tempPerm = toInt(perm);
        int[] dirs = new int[tempPerm.length];
        Mobile mobile = new Mobile();
        int[] newPerm = new int[tempPerm.length];

        permList.add(perm);


        Arrays.fill(dirs, -1);

        while (true) {
            newPerm = mobile.update(tempPerm, dirs);

            if(newPerm == null)
                break;

            for (int i : newPerm)
                s.append(Integer.toString(i));

            permList.add(s.toString());
            s.setLength(0);
        }

        return permList;
    }

    int getDir(int[] dirs, int index) {
        return dirs[index];

    }

    void swapDir(int index, int[] dirs) {
        dirs[index] *= -1;
    }

    int[] toInt(String stringArray) {
        char[] charArray = stringArray.toCharArray();
        int[] intArray = new int[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            intArray[i] = Integer.parseInt(Character.toString(charArray[i]));

        return intArray;
    }                               //turns string into int array

    int findLargest(int[] tempPerm, boolean[] illegal) {
        int[] temp = new int[tempPerm.length];


        for (int i = 0; i < tempPerm.length; i++)
            if (!illegal[i])
                temp[i] = tempPerm[i];

        Arrays.sort(temp);

        for (int j = 0; j < tempPerm.length; j++)
            if (temp[temp.length - 1] == tempPerm[j])
                return j;

        return -1;
    }

    boolean checkBound(int dir, int[] tempPerm, int i) {
        boolean checkRight = (dir == 1) && (i == (tempPerm.length - 1));
        boolean checkLeft = (dir == -1) && (i == 0);

        return checkLeft || checkRight;
    }

    boolean checkNext(int[] dirs, int[] tempPerm, int i) {
        if (dirs[i] > 0 && (i < tempPerm.length - 1)) {
            if (tempPerm[i] < tempPerm[i + 1])
                return false;
        }
        else if (dirs[i] < 0 && (i != 0)) {
            if (tempPerm[i] < tempPerm[i - 1])
                return false;
        }

        return true;
    }

    class Mobile {
        int index;

        void getShift(int dir, int[] tempPerm, int[] dirs) {
            if (dir > 0)
                shiftRight(tempPerm, dirs);
            else
                shiftLeft(tempPerm, dirs);

        }

        void shiftRight(int[] tempPerm, int[] dirs) {
            int tempNum,
                    tempDir;

            tempNum = tempPerm[index];
            tempDir = dirs[index];

            tempPerm[index] = tempPerm[index + 1];
            dirs[index] = dirs[index + 1];

            tempPerm[index + 1] = tempNum;
            dirs[index + 1] = tempDir;

            index += 1;
        }         //shift mobile to right

        void shiftLeft(int[] tempPerm, int[] dirs) {
            int tempNum,
                    tempDir;

            tempNum = tempPerm[index];
            tempDir = dirs[index];

            tempPerm[index] = tempPerm[index - 1];
            dirs[index] = dirs[index - 1];

            tempPerm[index - 1] = tempNum;
            dirs[index - 1] = tempDir;


            index -= 1;


        }         //shift mobile to left

        void getIndex(int[] tempPerm, int[] dirs) {
            index = 0;
            boolean[] illegalIndex = new boolean[tempPerm.length];
            boolean bounds,
                    next;

            Arrays.fill(illegalIndex, false);

            for (int i = 0; i < tempPerm.length; i++) {
                bounds = checkBound(dirs[i], tempPerm, i);
                next = checkNext(dirs, tempPerm, i);
                if (bounds)
                    illegalIndex[i] = true;
                else if (!next)
                    illegalIndex[i] = true;
            }

            index = findLargest(tempPerm, illegalIndex);
        }

        int[] update(int[] tempPerm, int[] dirs) {
            getIndex(tempPerm, dirs);
            if (index < 0)
                return null;
            else {
                getShift(getDir(dirs, index), tempPerm, dirs);
                swapLarger(dirs, tempPerm);
                return tempPerm;
            }
        }

        void swapLarger(int[] dirs, int[] tempPerm) {
            for (int i = 0; i < tempPerm.length; i++)
                if (tempPerm[index] < tempPerm[i])
                    swapDir(i, dirs);
        }
    }
}
