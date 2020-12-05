package hackerrank.DS;

/**
 * gakshintala created on 11/10/19.
 */
public class BookOfCricket {
    public static void main(String[] args) {
        System.out.println(combinationCount(new int[6], 6, new int[]{2, 4, 6}, 0));
    }

    static int combinationCount(int[] arr, int score, int[] pageNumbers, int i) {
        var result = 0;
        if (score == 0) {
            return 1;
        } else if (score > 0) {
            for (var pageNumber : pageNumbers) {
                arr[i] = pageNumber;
                result += combinationCount(arr, score - pageNumber, pageNumbers, i + 1);
            }
        }
        return result;
    }
}

