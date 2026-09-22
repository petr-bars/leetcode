package com.example.first_step.maximum_subarray;

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 * <p>
 * Дан целочисленный массив nums.
 * Нужно найти непрерывный подмассив (содержащий хотя бы одно число) с максимальной суммой и вернуть эту сумму.
 * Пример:
 * Вход: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Выход: 6
 * Подмассив [4, -1, 2, 1] даёт сумму 6.
 * Паттерн
 * Алгоритм Кадане (Kadane's algorithm).
 * Это 1D DP: идём по массиву и на каждом шаге решаем — продолжить текущий подмассив или начать новый.
 * Ключевая идея
 * На каждом элементе nums[i] есть два варианта:
 * Продолжить текущий подмассив: currentSum + nums[i] > nums[i].
 * Начать заново с nums[i] currentSum = nums[i]. (если накопленная сумма стала отрицательной — она только тянет вниз).
 * Берём максимум из двух — это и есть новая currentSum.
 * maxSum обновляем на каждом шаге.
 * Формула
 * currentSum = max(nums[i], currentSum + nums[i])
 * maxSum = max(maxSum, currentSum)
 * Если currentSum стал отрицательным, то nums[i] больше, значит, начинаем новый подмассив.
 * Сложность
 * Время: O(n) — один проход.
 * Память: O(1) — две переменные.
 */
public class MaximumSubarray {
    public static void main(String[] args) {
//        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = new int[]{4, -1, 2, 1};

        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = 0;
        for (int num : nums) {
            if (currentSum + num < num) {
                currentSum = num;
            } else {
                currentSum += num;
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }

        }
        return maxSum;
    }
}
