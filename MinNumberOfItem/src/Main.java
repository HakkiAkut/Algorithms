/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4};
        int limit = 14;
        System.out.println(findMin(arr, limit));
        System.out.println(recursiveFindMin(arr, limit));
    }

    /**
     * Question:
     * There is infinite number of items with certain kg. such as {1, 3, 4}
     * We have a limit in our bag and want to get minimum number of items.
     * And get our bag full.
     * <p>
     * Dynamic Programming:
     * We hold min number of items from 0 to limit in array. For example if we want to find 6 and our array is {1, 3, 4}
     * We will check 5th, 3rd and 2nd indexes and make 6th index element as min + 1
     *
     * @param array of items
     * @param limit we need to get
     * @return number of minimum items
     */
    public static int findMin(int[] array, int limit) {
        int n = array.length;
        // if limit or number of different items is 0 then return 0
        if (n == 0 || limit == 0) {
            return 0;
        }
        int[] result = new int[limit + 1];
        // initialize result array,
        // 0th index must be 0, and others -1. -1 item is impossible so we will check limits has -1 items.
        // it's important to 0th index element as 0 because we are checking elements not -1 so we need a starting point
        // algorithm
        for (int i = 1; i <= limit; i++) {
            result[i] = -1;
        }
        // finds every element before limit
        for (int i = 1; i <= limit; i++) {
            // checks every element before i, with array elements.
            for (int j = 0; j < n; j++) {
                // array element must be lesser than current limit(i)
                if (array[j] <= i) {
                    int check = result[i - array[j]];
                    // checks is number of items is not -1,
                    // there is a chance for can't get some number, if min item is 2, we can't get 1
                    if (check != -1) {
                        if (result[i] == -1 || check + 1 < result[i]) {
                            result[i] = check + 1;
                        }
                    }
                }
            }
        }
        return result[limit];
    }

    /**
     * Recursive solution
     *
     * @param array of items
     * @param limit we need to get
     * @return number of minimum items
     */
    public static int recursiveFindMin(int[] array, int limit) {
        if (limit == 0) {
            return 0;
        } else if (limit < 0) {
            return Integer.MAX_VALUE;
        }
        int current = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int result = recursiveFindMin(array, limit - array[i]);
            if (result != Integer.MAX_VALUE) {
                current = Math.min(current, result + 1);
            }
        }

        return current;
    }
}
