/**
 * @author Hakkı Can Akut
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(substring("hakki", "hki"));
    }

    /**
     * Question:
     * Find length of longest common substring from given strings
     *
     * @param str main string
     * @param sub sub string
     * @return length of longest common substring
     */
    public static int substring(String str, String sub) {
        int n1 = str.length();
        int n2 = sub.length();
        int[][] result = new int[n1 + 1][n2 + 1];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if(str.charAt(i)==sub.charAt(j)){
                    result[i+1][j+1]=result[i][j]+1;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n1+1; i++){
            for(int j = 0; j < n2+1; j++){
                if(result[i][j] > max){
                    max = result[i][j];
                }
            }

        }
        return max;
    }
}
