/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 7, 8, 10};
        System.out.println("first missing integer: " + firstMissing(arr, 0, arr.length - 1, arr.length));
    }

    /**
     * Question:
     * You are given a sorted array A of n distinct integers, drawn from 1 to m where n<m. That is, A is a subset of [1,2,...,m].
     * Implement an O(log(n)) time algorithm (i.e., write a function) to find the smallest non-negative integer that is
     * missing in A given as a parameter. For example, when A is [1, 2, 3, 5, 7, 8, 10], the function must return 4
     *
     * @param arr that we will check
     * @param p first index
     * @param r last index
     * @param n size of array
     * @return first missing integer in array
     */
    public static int firstMissing(int[] arr, int p, int r, int n) {
        if (arr[n - 1] == n) {
            return 0;
        }
        if (p == r) {
            return p + 1;
        }
        int q = (p + r) / 2;
        if (arr[q] != q + 1) {
            return firstMissing(arr, p, q, n);
        } else {
            return firstMissing(arr, q + 1, r, n);
        }
    }
}
