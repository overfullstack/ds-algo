package icake.Arrays.SortScores;

import java.util.Arrays;

public class SortScores {
    public static void main(String[] args) {
        int[] scores = {9, 4, 3, 5, 5, 3, 4, 8, 9, 6, 9, 4, 6, 1, 4, 3, 7, 6, 6};
        var highestPossibleScore = 9;
        System.out.println(Arrays.toString(sortScores(scores, highestPossibleScore)));
    }

    private static int[] sortScores(int[] scores, int highestPossibleScore) {
        var scoreToCount = new int[highestPossibleScore + 1];
        for (var score : scores) {
            scoreToCount[score]++;
        }
        var sortedScores = new int[scores.length + 1];
        var scoreIndex = 0;
        for (var score = 0; score < scoreToCount.length; score++) {
            var count = scoreToCount[score];
            for (var occurrence = 0; occurrence < count; occurrence++) {
                sortedScores[scoreIndex] = score;
                scoreIndex++;
            }
        }
        return sortedScores;
    }
}
