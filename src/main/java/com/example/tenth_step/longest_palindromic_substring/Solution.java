package com.example.tenth_step.longest_palindromic_substring;

public class Solution {

    public String longestPalindrome(String s) {
        // Если строка пустая или null, возвращаем пустую строку
        if (s == null || s.isEmpty()) {
            return "";
        }

        int stringLength = s.length();
        int longestStart = 0;
        int longestLength = 1;

        // Перебираем все возможные центры палиндрома
        for (int center = 0; center < stringLength; center++) {

            // 1. Нечётная длина: центр — один символ (center, center)
            int oddLength = expandFromCenter(s, center, center);

            // 2. Чётная длина: центр — два символа (center, center+1)
            int evenLength = expandFromCenter(s, center, center + 1);

            // Выбираем длину, которая больше
            int currentBestLength = Math.max(oddLength, evenLength);

            // Если нашли палиндром длиннее текущего максимума — обновляем
            if (currentBestLength > longestLength) {
                longestLength = currentBestLength;

                // Вычисляем стартовый индекс палиндрома
                if (oddLength >= evenLength) {
                    // Для нечётной длины: start = center - (length - 1) / 2
                    longestStart = center - (oddLength - 1) / 2;
                } else {
                    // Для чётной длины: start = center - length/2 + 1
                    longestStart = center - evenLength / 2 + 1;
                }
            }
        }

        // Возвращаем найденную подстроку
        return s.substring(longestStart, longestStart + longestLength);
    }

    /**
     * Расширяется от центра (left, right) влево и вправо,
     * пока символы совпадают.
     * Возвращает длину найденного палиндрома.
     */
    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // После выхода из цикла left и right указывают на символы за пределами палиндрома,
        // поэтому длина = right - left - 1
        return right - left - 1;
    }

    /**
     * ////////////////////////////////////////////////////////////////////////////////////////////////////
     * ///////// Для примера работы 2D приведено это решение но приоритет алгоритм расширение от центра.//
     * ///////////////////////////////////////////////////////////////////////////////////////////////////
     **/
    public String longestPalindrome2D(String s) {
        int length = s.length();
        if (length == 0) {
            return "";
        }

        // dp[i][j] = true, если подстрока s[i..j] является палиндромом
        boolean[][] dp = new boolean[length][length];

        int start = 0;
        int maxLength = 1; // минимум один символ

        // 1. Подстроки длины 1 (все палиндромы)
        for (int index = 0; index < length; index++) {
            dp[index][index] = true;
        }

        // 2. Подстроки длины 2
        for (int index = 0; index < length - 1; index++) {
            if (s.charAt(index) == s.charAt(index + 1)) {
                dp[index][index + 1] = true;
                start = index;
                maxLength = 2;
            }
        }

        // 3. Подстроки длины >= 3
        // Перебираем длину подстроки
        for (int len = 3; len <= length; len++) {
            for (int row = 0; row <= length - len; row++) {
                int col = row + len - 1; // конец подстроки

                // Подстрока s[i..j] палиндром, если:
                // - крайние символы равны
                // - внутренняя подстрока s[i+1..j-1] тоже палиндром
                if (s.charAt(row) == s.charAt(col) && dp[row + 1][col - 1]) {
                    dp[row][col] = true;
                    // если нашли более длинный палиндром, запоминаем
                    if (len > maxLength) {
                        start = row;
                        maxLength = len;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }
}
