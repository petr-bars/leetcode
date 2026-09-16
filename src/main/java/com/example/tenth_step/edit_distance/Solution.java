package com.example.tenth_step.edit_distance;

public class Solution {
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];

        // Первый столбец: превратить index символов word1 в пустую строку — удалить все
        for (int row = 1; row <= length1; row++) {
            dp[row][0] = row;
        }

        // Первая строка: превратить пустую строку в j символов word2 — вставить все
        for (int col = 1; col <= length2; col++) {
            dp[0][col] = col;
        }

        // Основной цикл
        for (int row = 1; row <= length1; row++) {
            for (int col = 1; col <= length2; col++) {
                char charFromWord1 = word1.charAt(row - 1);
                char charFromWord2 = word2.charAt(col - 1);

                if (charFromWord1 == charFromWord2) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    int deleteCost = dp[row - 1][col];
                    int insertCost = dp[row][col - 1];
                    int replaceCost = dp[row - 1][col - 1];
                    dp[row][col] = 1 + Math.min(deleteCost, Math.min(insertCost, replaceCost));
                }
            }
        }

        return dp[length1][length2];
    }

    public int minDistanceOptimized(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();

        // Хотим, чтобы массив был короче — храним строку для меньшей строки
        // Если word1 длиннее, меняем местами (это не влияет на ответ)
        if (length1 < length2) {
            return minDistance(word2, word1);
        }

        // previousRow[j] — значение dp[i-1][j] для предыдущей строки
        int[] previousRow = new int[length2 + 1];

        // Базовый случай: первая строка (i = 0)
        // dp[0][j] = j (вставить j символов)
        for (int j = 0; j <= length2; j++) {
            previousRow[j] = j;
        }

        // Основной цикл
        for (int i = 1; i <= length1; i++) {
            int[] currentRow = new int[length2 + 1];
            currentRow[0] = i; // dp[i][0] = i (удалить i символов)

            for (int j = 1; j <= length2; j++) {
                char charFromWord1 = word1.charAt(i - 1);
                char charFromWord2 = word2.charAt(j - 1);

                if (charFromWord1 == charFromWord2) {
                    // Совпали — берём по диагонали из предыдущей строки
                    currentRow[j] = previousRow[j - 1];
                } else {
                    int deleteCost = previousRow[j];      // сверху
                    int insertCost = currentRow[j - 1];   // слева
                    int replaceCost = previousRow[j - 1]; // по диагонали
                    currentRow[j] = 1 + Math.min(deleteCost, Math.min(insertCost, replaceCost));
                }
            }

            // Текущая строка становится предыдущей для следующей итерации
            previousRow = currentRow;
        }

        return previousRow[length2];
    }
}
