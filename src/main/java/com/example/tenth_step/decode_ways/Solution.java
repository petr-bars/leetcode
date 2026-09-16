package com.example.tenth_step.decode_ways;

public class Solution {
    public int numDecodings(String s) {
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;

        if (s.charAt(0) == '0') {
            return 0;
        }

        dp[1] = 1;

        for (int index = 2; index <= length; index++) {
            if (s.charAt(index - 1) != '0') {
                dp[index] += dp[index - 1];
            }

            int twoDigit = Integer.parseInt(s.substring(index - 2, index));
            if (twoDigit > 9 && twoDigit <= 26) {
                dp[index] += dp[index - 2];
            }

        }
        return dp[length];
    }


    public int numDecodingsOptimized(String s) {
        int length = s.length();
        if (length == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int prev2 = 1; // dp[0]
        int prev1 = 1; // dp[1]

        for (int index = 2; index <= length; index++) {
            int current = 0;

            // Однозначный переход
            if (s.charAt(index - 1) != '0') {
                current += prev1;
            }

            // Двузначный переход
            int twoDigit = extractTwoDigitNumber(s, index);
            if (twoDigit > 9 && twoDigit <= 26) {
                current += prev2;
            }

            // Сдвигаем "окно" из двух значений
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * Вычисляет двузначное число из двух соседних символов строки.
     * <p>
     * Формула: {@code (десятки) * 10 + (единицы)}.
     * <p>
     * Каждый символ цифры ('0'..'9') преобразуется в числовое значение
     * путём вычитания символа {@code '0'} (код ASCII 48):
     * <ul>
     *   <li>{@code '1' - '0' = 1}</li>
     *   <li>{@code '2' - '0' = 2}</li>
     *   <li>...</li>
     *   <li>{@code '9' - '0' = 9}</li>
     * </ul>
     * <p>
     * Первый символ умножается на 10 (сдвигается в разряд десятков),
     * затем прибавляется второй символ (разряд единиц).
     * <p>
     * <b>Пример для строки "12":</b>
     * <pre>
     * (s.charAt(index - 2) - '0') * 10 + (s.charAt(index - 1) - '0')
     * = ('1' - '0') * 10 + ('2' - '0')
     * = 1 * 10 + 2
     * = 12
     * </pre>
     * <p>
     * <b>Преимущества перед {@code Integer.parseInt(s.substring(...))}:</b>
     * <ul>
     *   <li>Не создаёт новых объектов {@link String} в куче.</li>
     *   <li>Не нагружает сборщик мусора (GC).</li>
     *   <li>Работает через простую арифметику с символами — максимально быстро.</li>
     * </ul>
     *
     * @param s     исходная строка, содержащая цифры
     * @param index позиция в строке, указывающая на символ после второго извлекаемого
     *              (то есть второй символ имеет индекс {@code index - 1},
     *              первый — {@code index - 2})
     * @return двузначное число, собранное из символов {@code s.charAt(index - 2)}
     * и {@code s.charAt(index - 1)}
     */
    private int extractTwoDigitNumber(String s, int index) {
        return (s.charAt(index - 2) - '0') * 10 + (s.charAt(index - 1) - '0');
    }
}
