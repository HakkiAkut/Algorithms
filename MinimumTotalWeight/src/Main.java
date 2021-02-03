/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        /*
         * **runtimes are nanosecond!**
         * Question:
         * We are given a complete binary tree where nodes and edges have positive weights.
         * Node weights are stored in a 1-dimensional array WN. Edge weights are stored in a 2-dimensional
         * array WE where 0 denotes no edge. Node indices are given form top-to-bottom and left-to-right (e.g.
         * root node index is 1, its left child 2 and so on...).
         * Starting at the root of the tree and moving to either one of the children from the current node, the
         * goal is to find the minimum total weight (i.e. sum of node and edge weights) path from the root to
         * any one of the leaves.
         *
         * Runtime table
         *           Recursion  Greedy   Dynamic
         * size 9    54600      13600    13500
         */
        long current1;
        Object[] result1;
        long current2;
        Object[] result3;
        long current3;
        Object[] result2;
        long current4;

        int[] wn = {3, 4, 2, 6, 1, 9, 8, 8, 5,};
        int[][] we = {{0, 1, 5, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 2, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 9, 3, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 6, 4},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        current1 = System.nanoTime();
        result1 = recursive(wn, we, 0);
        current2 = System.nanoTime();
        result3 = greedy(wn, we);
        current3 = System.nanoTime();
        result2 = dynamic_programming(wn, we);
        current4 = System.nanoTime();

        System.out.println("for n= 9");
        System.out.println("Recursion -> path = " + result1[1] + ", weight= " + result1[0] + ", runtime= " + (current2 - current1));
        System.out.println("Greedy -> path = " + result3[0] + ", weight= " + result3[1] + ", runtime= " + (current3 - current2));
        System.out.println("Dynamic P -> path = " + result2[0] + ", weight= " + result2[1] + ", runtime= " + (current4 - current3));


    }

    /**
     * Question 1 : Greedy Algorithm Implementation **Not Optimal!**
     * runtime O(log(n))
     *
     * @param array    of tree nodes
     * @param db_array of tree edges
     * @return an object array which has path string and min weight
     */
    public static Object[] greedy(int[] array, int[][] db_array) {
        Object[] genericArray = new Object[2];
        genericArray[0] = "";
        genericArray[1] = array[0];
        int current = 0;
        while (true) {
            genericArray[0] += (current + 1) + " ";
            if ((2 * current) + 2 < array.length) {
                int leftCost = db_array[current][(2 * current) + 1] + array[(2 * current) + 1];
                int rightCost = db_array[current][(2 * current) + 2] + array[(2 * current) + 2];
                if (leftCost <= rightCost) {
                    genericArray[1] = (int) genericArray[1] + leftCost;
                    current = (2 * current) + 1;
                } else {
                    genericArray[1] = (int) genericArray[1] + rightCost;
                    current = (2 * current) + 2;
                }
            } else if ((2 * current) + 1 < array.length) {
                genericArray[1] = (int) genericArray[1] + db_array[current][(2 * current) + 1] + array[(2 * current) + 1];
                current = (2 * current) + 1;
            } else {
                break;
            }
        }
        return genericArray;
    }

    /**
     * Question 2: Implementation of recursive method
     * runtime O(n log(n))
     *
     * @param array    of tree nodes
     * @param db_array of tree edges
     * @return an object array which has path string and min weight
     */
    public static Object[] recursive(int[] array, int[][] db_array, int i) {
        Object[] genericArray = new Object[2];
        genericArray[1] = 0;
        if (i < array.length) {
            genericArray[0] = array[i] + db_array[(i - 1) / 2][i];
            genericArray[1] = (i + 1) + " ";
        } else {
            return null;
        }

        Object[] leftWeight = recursive(array, db_array, 2 * i + 1);
        Object[] rightWeight = recursive(array, db_array, 2 * i + 2);

        if (leftWeight != null && rightWeight != null) {
            if ((int) leftWeight[0] < (int) rightWeight[0]) {
                genericArray[0] = (int) genericArray[0] + (int) leftWeight[0];
                genericArray[1] = genericArray[1] + (String) leftWeight[1];
            } else {
                genericArray[0] = (int) genericArray[0] + (int) rightWeight[0];
                genericArray[1] = genericArray[1] + (String) rightWeight[1];
            }
        } else if (leftWeight != null) {
            genericArray[0] = (int) genericArray[0] + (int) leftWeight[0];
            genericArray[1] = genericArray[1] + (String) leftWeight[1];
        } else if (rightWeight != null) {
            genericArray[0] = (int) genericArray[0] + (int) rightWeight[0];
            genericArray[1] = genericArray[1] + (String) rightWeight[1];
        }
        return genericArray;
    }

    /**
     * Question 3: Dynamic Programming Implementation
     * runtime O(n)
     *
     * @param array    of tree nodes
     * @param db_array of tree edges
     * @return an object array which has path string and min weight
     */
    public static Object[] dynamic_programming(int[] array, int[][] db_array) {
        Object[] genericArray = new Object[2];
        genericArray[0] = "";
        for (int i = 1; i < array.length; i++) {
            int parent = (i - 1) / 2;
            db_array[parent][i] += db_array[(parent - 1) / 2][parent] + array[i];
        }

        int[] result = new int[2];
        int firstLeaf = ((array.length - 1) / 2);
        result[0] = firstLeaf;
        result[1] = db_array[(firstLeaf - 1) / 2][firstLeaf];
        for (int i = firstLeaf + 1; i < array.length; i++) {
            if (db_array[(i - 1) / 2][i] < result[1]) {
                result[0] = i;
                result[1] = db_array[(i - 1) / 2][i];
            }
        }
        result[1] += array[0];
        for (int j = (result[0] + 1); j >= 1; j = j / 2) {
            genericArray[0] = j + " " + genericArray[0];
        }
        genericArray[1] = result[1];
        return genericArray;
    }
}
