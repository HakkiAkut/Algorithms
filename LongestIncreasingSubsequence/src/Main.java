/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 1, 2, 5, 3, 12, 5, 24, 11, 8};
        // 2, 4, 7, 12, 24
        // output must be 5
        System.out.println(subsequence(arr));
        System.out.println(recursiveSubsequence(arr, 0, 0));
    }

    /**
     * Question:
     * Find longest increasing subsequence of given an integer array
     * <p>
     * Dynamic Programming:
     * We hold max number of longest increasing subsequence from 0 to n in an array. ith element will check every smaller elements
     * before i than get max+1 as number of longest increasing subsequence from 0th to ith element.
     *
     * @param arr is integer array that we will find longest increasing subsequence
     * @return number of longest increasing subsequence
     */
    public static int subsequence(int[] arr) {
        if (arr.length <= 1) {
            return 1;
        }
        int[] result = new int[arr.length];
        result[0] = 1;
        for (int i = 1; i < result.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i] && result[i] <= result[j]) {
                    result[i] = result[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i : result) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Recursive solution
     *
     * @param arr       is integer array that we will find longest increasing subsequence
     * @param index     current index
     * @param lastTaken last taken number from array
     * @return number of longest increasing subsequence
     */
    public static int recursiveSubsequence(int[] arr, int index, int lastTaken) {
        if (index == arr.length) {
            return 0;
        }
        int current = 0;

        if (lastTaken <= arr[index]) {
            int result1 = recursiveSubsequence(arr, index + 1, arr[index]) + 1;
            int result2 = recursiveSubsequence(arr, index + 1, lastTaken);
            current = Math.max(result1, result2);
        } else {
            current = recursiveSubsequence(arr, index + 1, lastTaken);
        }

        return current;
    }
}
