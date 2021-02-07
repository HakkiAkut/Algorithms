public class Main {
    public static void main(String[] args) {
        int[] set = {1,3,4,6,9};
        int sum = 15;
        System.out.println(subsetSum(set, sum)); // must return true
    }

    /**
     * Question: Find that, is set has a subsequence which is sums of elements are equal to given number
     *
     * @param set is given superSequence
     * @param sum is given number
     * @return true if there is subsequence for given number
     */
    public static boolean subsetSum(int[] set, int sum) {
        boolean[][] result = new boolean[set.length + 1][sum + 1];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = true;
        }
        for (int i = 1; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (set[i - 1] <= j) {
                    result[i][j] = result[i - 1][j] || result[i - 1][j - set[i - 1]];
                } else {
                    result[i][j] = result[i - 1][j];
                }
            }
        }
        return result[set.length][sum];
    }
}
