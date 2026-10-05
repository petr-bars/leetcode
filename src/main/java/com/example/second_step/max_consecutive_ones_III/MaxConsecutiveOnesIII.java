package com.example.second_step.max_consecutive_ones_III;

/**
 * <p>Given a binary array nums and an integer k, return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's.</p>
 *
 * <p>Дан бинарный массив nums (только 0 и 1) и число k. Можно заменить не
 * более k нулей на единицы. Найти длину самой длинной последовательности
 * единиц, которую можно получить.</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2<br>
 * Выход: 6</p>
 *
 * <p>Паттерн:<br>
 * Variable sliding window. Ищем максимум, сжимаем при нарушении условия.</p>
 *
 * <p>Идея:<br>
 * Держим окно [left, right] и считаем нули внутри. Окно валидное, если
 * нулей не больше k — их можно заменить, и всё внутри станет единицами.
 * Как только нулей стало больше k — сжимаем слева, пока окно снова
 * не станет валидным.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>zeros = количество нулей в окне.</li>
 *   <li>При добавлении элемента справа: если это ноль — zeros++.</li>
 *   <li>Пока zeros &gt; k: если выпадающий слева элемент ноль — zeros--,
 *       left++.</li>
 *   <li>best = max(best, right - left + 1). Обновляется после сжатия.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n).<br>
 * Память: O(1).</p>
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1};
        int k = 1;

        System.out.println(longestOnes(nums, k));
    }

    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeros = 0;
        int best = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (nums[left++] == 0) {
                    zeros--;
                }
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
