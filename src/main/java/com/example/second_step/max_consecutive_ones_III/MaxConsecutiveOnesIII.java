package com.example.second_step.max_consecutive_ones_III;

/**
 * Given a binary array nums and an integer k,
 * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * <p>
 * Дан бинарный массив nums (только 0 и 1) и число k. Можно заменить не более k нулей на единицы.
 * Найти длину самой длинной последовательности единиц, которую можно получить.
 * Пример:
 * Вход:  nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Выход: 6
 * Пояснение: заменяем нули на индексах 4 и 5 → [1,1,1,0,0,1,1,1,1,1,1], длина 6.
 * Паттерн
 * Variable sliding window — окно переменного размера.
 * Идея
 * Держишь окно [left, right]. Внутри окна считаешь количество нулей. Окно валидное,
 * если нулей ≤ k — их можно заменить, и всё станет единицами.
 * right расширяет окно. Как только нулей стало больше k — сжимаешь слева, пока нулей снова не станет ≤ k.
 * Это тот же variable window, что в Longest Repeating Character Replacement: ищем максимум,
 * сжимаем при нарушении условия.
 * Формула
 * left = 0, zeros = 0, best = 0
 * для right от 0 до n-1:
 * если nums[right] == 0: zeros++
 * пока zeros > k:
 * если nums[left] == 0: zeros--
 * left++
 * best = max(best, right - left + 1)
 * вернуть best
 * zeros — количество нулей в текущем окне. best обновляется после сжатия, когда окно снова валидное.
 * Сложность
 * Время: O(n) — right и left двигаются только вперёд.
 * Память: O(1).
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
