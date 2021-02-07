/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int[] profit = {1, 3, 5, 6, 7};
        int[] weight = {2, 3, 4, 5, 6};
        System.out.println(knapsack(profit, weight, 8));// will return 9
        System.out.println(recursiveKnapsack(profit, weight, 8, 0));
    }

    /**
     * Question:
     * Classic 0-1 Knapsack, solve with Dynamic Programming
     *
     * @param profit   array of items
     * @param weight   array of items
     * @param capacity of knapsack
     * @return max value
     */
    public static int knapsack(int[] profit, int[] weight, int capacity) {
        int n1 = profit.length;

        int[][] result = new int[n1 + 1][capacity + 1];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (weight[i] <= j) {
                    result[i + 1][j] = Math.max(profit[i] + result[i][j - weight[i]], result[i][j]);
                } else {
                    result[i + 1][j] = result[i][j];
                }
            }
        }

        return result[n1][capacity];
    }

    /**
     * Recursive Solution
     *
     * @param profit   array of items
     * @param weight   array of items
     * @param capacity of knapsack
     * @param index    current index of array
     * @return max value
     */
    public static int recursiveKnapsack(int[] profit, int[] weight, int capacity, int index) {
        // index cannot be greater than weight length
        if (index >= weight.length) {
            return 0;
        }
        // weight of item cannot be greater than capacity,
        // if it is than it will continue to check other items
        if (capacity < weight[index]) {
            return recursiveKnapsack(profit, weight, capacity, index + 1);
        }
        int take = profit[index] + recursiveKnapsack(profit, weight, capacity - weight[index], index + 1);
        int notTake = recursiveKnapsack(profit, weight, capacity, index + 1);
        return Math.max(take, notTake);
    }
}
