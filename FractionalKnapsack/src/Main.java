/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int[] profit = {60, 80, 100, 130, 120};
        int[] weights = {10, 5, 20, 25, 30};
        System.out.println(knapsack(profit, weights, 50));//must return  315
    }

    /**
     * Question: Classic Fractional Knapsack, Solve with Greedy Approach
     *
     * @param profit   array of items
     * @param weight   array of items
     * @param capacity of knapsack
     * @return max profit
     */
    public static int knapsack(int[] profit, int[] weight, int capacity) {
        int[] rate = new int[profit.length];
        for (int i = 0; i < profit.length; i++) {
            rate[i] = profit[i] / weight[i];
        }
        int result = 0;
        int currentWeight = 0;
        while (true) {
            int max = 0;
            int index = 0;
            for (int i = 0; i < rate.length; i++) {
                if (max < rate[i]) {
                    max = rate[i];
                    index = i;
                }
            }
            if (max != 0) {
                if (currentWeight + weight[index] <= capacity) {
                    result += profit[index];
                    rate[index] = 0;
                    currentWeight += weight[index];
                } else {
                    result += rate[index] * (capacity - currentWeight);
                    break;
                }
            } else {
                break;
            }
        }
        return result;
    }
}
