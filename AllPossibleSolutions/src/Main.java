/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int[] set = {1, 2, 3, 5};
        int sum = 11;
        System.out.println(possibleSolutions(set, sum)); // must return 24
    }

    /**
     * Question: find number of all possible solutions, set items can usable unlimited times to achieve given number
     *
     * @param set of items(numbers)
     * @param sum that we want to achieve
     * @return total number of solutions
     */
    public static int possibleSolutions(int[] set, int sum) {
        int[][] result = new int[set.length + 1][sum + 1];

        for (int i = 0; i < result.length; i++) {
            result[i][0] = 1;
        }
        for (int i = 1; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (set[i - 1] <= j) {
                    result[i][j] = result[i - 1][j] + result[i][j - set[i - 1]];
                } else {
                    result[i][j] = result[i - 1][j];
                }
            }
        }
        return result[set.length][sum];
    }
}
