package com.example.tenth_step.longest_common_subsequence;

public class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        int length1 = text1.length();
        int length2 = text2.length();

        int[][] dp = new int[length1 + 1][length2 + 1];

        for (int row = 1; row <= length1; row++) {
            for (int col = 1; col <= length2; col++) {
                char charRow = text1.charAt(row - 1);
                char charCol = text2.charAt(col - 1);

                if (charRow == charCol) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }

        return dp[length1][length2];
    }

    public int longestCommonSubsequenceOptimized(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        int length1 = text1.length();
        int length2 = text2.length();

        int[] dp = new int[length2 + 1];
        int diagonalPrev;

        for (int row = 1; row <= length1; row++) {
            diagonalPrev = 0;
            for (int col = 1; col <= length2; col++) {
                int temp = dp[col]; // сохраняем старое значение (dp[row-1][col])
                if (text1.charAt(row - 1) == text2.charAt(col - 1)) {
                    dp[col] = diagonalPrev + 1;
                } else {
                    dp[col] = Math.max(dp[col], dp[col - 1]);
                }
                diagonalPrev = temp; // для следующего col это будет dp[row-1][col-1]
            }
        }

        return dp[length2];
    }
}
