package com.example.second_step.minimum_size_subarray_sum;

/**
 * <p>Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal
 * to target. If there is no such subarray, return 0 instead.</p>
 *
 * <p>Дан массив положительных чисел nums и число target. Найти минимальную
 * длину подмассива, сумма которого &gt;= target. Если такого нет — вернуть 0.</p>
 *
 * <p>Пример:<br>
 * Вход:  target = 7, nums = [2,3,1,2,4,3]<br>
 * Выход: 2<br>
 * Пояснение: [4,3] — сумма 7, длина 2.</p>
 *
 * <p>Паттерн:<br>
 * Sliding window (variable). Ищем минимум.</p>
 *
 * <p>Идея:<br>
 * Держим окно и сумму внутри. Правая граница расширяет окно, пока сумма
 * не достигнет target. Как только сумма стала достаточной — запоминаем
 * длину и сжимаем окно слева, пытаясь найти короче.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>currentSum += nums[right].</li>
 *   <li>Пока currentSum &gt;= target:
 *       minLength = min(minLength, right - left + 1),
 *       currentSum -= nums[left], left++.</li>
 *   <li>Вернуть minLength или 0, если не обновился.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n).<br>
 * Память: O(1).</p>
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(target, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
