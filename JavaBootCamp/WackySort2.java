/**
 * WackySort2 utilizes a selection sort to lexicographically sort a set of
 * six-character words based on a new alphabet. It has a time complexity of
 * O(n^2). Upon completion, an attempt was made to improve readability based
 * on previous comments. Please let me know if there is anything else I can
 * improve on.
 * 
 * @author Jeffrey Weiner
 * @version 2.0
 */
public class WackySort2 {
    /**
     * wackySort() utilizes a selection sort to lexicographically sort a set of
     * six-character words based on a new alphabet
     * @param unordered is an unordered array of Strings that is to be sorted
     * @return unordered once it has been sorted lexicographically
     */
    public static String[] wackySort (String[] unordered) {
        if (unordered.length > 1) {
            for (int i = 0; i < unordered.length; i++) {
                int minPos = getMinPos(unordered, i);
                System.out.println(minPos);
                swap(unordered, minPos, i);
            }
        }
        return unordered;
    }

    /**
     * getMinPos() determines the index of the String with the least value
     * @param a is an array of Strings from which we want to determine the minPos
     * @param from is the index at which we begin searching for the minPos
     * @return minPos is the index of the String with the least value
     */
    private static int getMinPos (String[] a, int from) {
        String alph = "VOFLTSUQXJGBCAHNMDEZRYKWIP#$";
        int minPos = from;
        for (int i = from + 1; i < a.length; i++) {
            for (int j = 0; j < 6; j++) {
                int minVal = alph.indexOf(a[minPos].substring(j, j+1));
                int otherVal = alph.indexOf(a[i].substring(j, j+1));
                if (otherVal < minVal) {
                    minPos = i;
                    break;
                } else if (minVal < otherVal) {
                    break;
                }
            }
        }
        return minPos;
    }
    
    /**
     * swap() swaps the positions of two Strings
     * @param a is the array of Strings in which the swap takes place
     * @param i is the index of the first String to be swapped
     * @param j is the index of the second String to be swapped
     */
    private static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}