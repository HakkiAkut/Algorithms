/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 1, 2, 5, 3, 12, 5, 24, 11, 8};
        // 2, 4, 7, 12, 24
        // output must be 5
        System.out.println(subsequence(arr));
    }

    /**
     * Question:
     * Find longest increasing subsequence of given an integer array
     *
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
}
