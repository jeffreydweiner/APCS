/**
 * MergeAlgorithm combines two arrays that are in ascending order in such a way
 * that the resultant array is in ascending order. 
 * 
 * @author Jeffrey Weiner
 * @version 1.0
 */
public class MergeAlgorithm {
    /**
     * merge() combines two arrays that have been sorted in ascending order into a single array 
     * that is sorted in ascending order
     * @param lista is sorted in ascending order lista[i] <= lista[i+1], 0 <= i < lista.length
     * @param listb is sorted in ascending order listb[j] <= listb[j+1], 0 <= j < listb.length
     * @return a new sorted array representing the merge of lista and listb
     **/
    public static int[] merge (int[] lista, int[] listb) {
        int lenA = lista.length;
        int lenB = listb.length;
        int[] result = new int[lenA + lenB];
        int i = 0;
        int j = 0;
        int k = 0;
        //Assertion 1. If the last element of lista is smaller than the first element of listb then
        //all of lista is added to result and then all of listb is added to result. Similarly, if
        //the last item of listb is smaller than the first item of lista, all of list b is added to
        //result and then all of list a is added to result.
        if (lista[lenA - 1] < listb[0]) {
            for (int num : lista) {
                result[k] = num;
                k++;
            }
            for (int num : listb) {
                result[k] = num;
                k++;
            }
        } else if (lista[0] > listb[lenB - 1]) {
            for (int num : listb) {
                result[k] = num;
                k++;
            }
            for (int num : lista) {
                result[k] = num;
                k++;
            }
        } else {
            //Assertion 2. The next element of result - k - is the current element of lista - i - 
            //if the current item in lista is less than or equal to the current item in listb and is
            //the current element of listb - j - if the current item in listb is less than the 
            //current item in lista
            while (i < lenA && j < lenB) {
                if (lista[i] <= listb[j]) {
                    result[k] = lista[i];
                    i++;
                } else {
                    result[k] = listb[j];
                    j++;
                }
                k++;
            }
            //Assertion 3. Once one of the lists has been entirely added to the result, if
            //the other list has not, its entire contents are added to result
            if (i < lenA) {
                for (int p = i; p < lenA; p++) {
                    result[k] = lista[p];
                    k++;
                }
            } else if (j < lenB) {
                for (int p = j; p < lenB; p++) {
                    result[k] = listb[p];
                    k++;
                }
            }
        }
        //Assertion 4. lista and listb have been combined into result in such a manner that 
        //result contains all the elements of lista and listb and is in ascending order.
        //result[k] <= result[k+1], 0 <= k < result.length
        return result;
    }
}
