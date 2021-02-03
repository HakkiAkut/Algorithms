/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(minSteps(12));
    }

    /**
     * Question:
     * There is 4 operation n-1, n/2, n/3, n/5. We want to reach 0 by using this operations.
     * What is the minimum number of operations to reach 0?
     * 
     * Dynamic Programming:
     * We hold min number of operations from 0 to n in array. And every step we will check i-1, i/2, i/3, i/5
     * if it's possible. Then ith element becomes min+1.
     *
     * @param n number that will return to 0
     * @return min steps to reach 0
     */
    public static int minSteps(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++) {
            result[i] = result[i - 1] + 1;
            if (i % 2 == 0) {
                result[i] = Math.min(result[i], result[i / 2] + 1);
            }
            if (i % 3 == 0) {
                result[i] = Math.min(result[i], result[i / 3] + 1);
            }
            if (i % 5 == 0) {
                result[i] = Math.min(result[i], result[i / 5] + 1);
            }
        }
        return result[n];
    }
}
