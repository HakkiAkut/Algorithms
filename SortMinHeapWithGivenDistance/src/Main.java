/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        int k=3;
        int[] arr={4, 2, 3, 8, 5, 9, 6, 13, 10, 12, 11};
        int n= arr.length;
        int[] sortedArr = sortArr(arr, n, k);
        System.out.print("sorted array: ");
        printArr(sortedArr, n);
    }

    /**
     * Question:
     * Given an unsorted array, the array has this property that every element in array is at most k distance
     * from its position in sorted array where k is a positive integer smaller than size of array.
     * For example, let us consider k is 2, an element at index 7 in the sorted array,
     * can be at indexes 5, 6, 7, 8, 9 in the given array. sort such arrays with the help of Heap data structure
     *
     * @param arr given array
     * @param n size of array
     * @param k distance
     * @return sorted array
     */
    public static int[] sortArr(int[] arr, int n, int k) {
        int[] sortedArr = new int[n];
        int[] sortingArr = new int[k + 1];
        for (int j = 0; j < k + 1; j++) {
            sortingArr[j] = arr[j];
        }
        buildMinHeap(sortingArr, k + 1);
        for (int m = 0; m < n - k - 1; m++) {
            int min = extractMin(sortingArr, k + 1);
            sortedArr[m] = min;
            sortingArr[k] = arr[k + m + 1];
            buildMinHeap(sortingArr, k + 1);
        }
        for (int l = n - k - 1; l < n; l++) {
            int min = extractMin(sortingArr, n - l);
            sortedArr[l] = min;
        }
        return sortedArr;
    }

    public static void minHeapify(int[] arr, int i, int n) {
        int smallest = i;
        if (((2 * i) + 1) < n && arr[(2 * i) + 1] < arr[i]) {
            smallest = (2 * i) + 1;
        }
        if ((2 * i) + 2 < n && arr[(2 * i) + 2] < arr[smallest]) {
            smallest = (2 * i) + 2;
        }
        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
            minHeapify(arr, smallest, n);
        }
    }

    public static void buildMinHeap(int[] arr, int n) {
        int i = (n / 2) - 1;
        for (int j = i; j >= 0; j--) {
            minHeapify(arr, j, n);
        }
    }

    public static void printArr(int[] arr, int n) {
        for (int i = 0; i < n; ++i){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int extractMin(int[] arr, int n) {
        int min = arr[0];
        arr[0] = arr[n - 1];
        n = n - 1;
        minHeapify(arr, 0, n);
        return min;
    }
}
