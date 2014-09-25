/**
 * public class Question3 implements a least significant digit radix sort
 * to sort words lexicographically based on a new alphabet. A radix sort
 * was chosen because its time complexity is O(n), making it fairly fast
 * for sufficiently large inputs. A 2d array was used to create buckets 
 * in which data can be stored. A list of lists would have been 
 * implemented for this problem if it were permitted.
 * 
 * @author Jeffrey Weiner
 * @version 1.0
 */
public class Question3 {
    /**
     * wackySort() performs radix sort
     * @param unordered data to sort
     * @return sorted data
     */
    public static String[] wackySort (String[] unordered) {
        for (int i = 6; i > 0; i--) {
            unordered = dumpBuckets(fillBuckets(unordered, i), unordered);
        }
        return unordered;
    }

    /**
     * fillBuckets() stores unordered inputs in the appropriate buckets.
     * @param unordered data to sort
     * @param sigCh significant character (can range from 1 to 6)
     * @return data sorted into buckets based on sigCh
     */
    private static String[][] fillBuckets(String[] unordered, int sigCh) {
        String alph = "VOFLTSUQXJGBCAHNMDEZRYKWIP#$";
        String[][] buckets = new String[alph.length()][unordered.length];
        int[] indices = new int[buckets.length];
        String ch = "";
        int bucket = 0;
        for (String str : unordered) {
            if (sigCh == 6) {
                ch = str.substring(sigCh - 1);
            } else {
                ch = str.substring(sigCh - 1, sigCh);
            }
            bucket = alph.indexOf(ch);
            buckets[bucket][indices[bucket]] = str;
            ++indices[bucket];
        }
        return buckets;
    }

    /**
     * dumpBuckets() dumps contents of buckets into a target "bin" array 
     * bucket by bucket.
     * @param buckets buckets in which data has been stored
     * @param bin array in which data is dumped
     * @return the data stored in an array
     */
    private static String[] dumpBuckets (String[][] buckets, String[] bin) {
        int index = 0;
        for (int i = 0; i < buckets.length; ++i) {
            for (int j = 0; j < buckets[0].length; ++j) {
                if (buckets[i][j] != null) {
                    bin[index] = buckets[i][j];
                    ++index;
                }
            }
        }
        return bin;
    }
}