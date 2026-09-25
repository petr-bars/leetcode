package com.example.second_step.minimum_size_subarray_sum;

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 * <p>
 * Дан массив положительных чисел nums и число target.
 * Найти минимальную длину подмассива, сумма которого >= target.
 * Если такого нет — вернуть 0.
 * Пример:
 * Вход:  target = 7, nums = [2,3,1,2,4,3]
 * Выход: 2
 * Пояснение: [4,3] — сумма 7, длина 2.
 * Паттерн
 * Sliding window (variable) — но цель минимизировать, а не максимизировать.
 * Идея
 * Держишь окно [left, right] и сумму элементов внутри.
 * right расширяет окно, пока сумма < target.
 * Как только сумма достигла target — окно валидное. Запоминаешь длину.
 * И пытаешься сжать — двигаешь left, чтобы найти окно короче, но всё ещё с суммой >= target.
 * Ключевое отличие от Longest Substring: там мы искали максимум и сжимали только когда нарушалось условие.
 * Здесь мы ищем минимум и сжимаем всегда, как только условие выполнилось — чтобы попробовать ещё короче.
 * Формула
 * left = 0, sum = 0, best = MAX
 * right: 0 → n-1
 * sum += nums[right]
 * пока sum >= target:
 * best = min(best, right - left + 1)
 * sum -= nums[left]
 * left++
 * вернуть best (или 0, если best == MAX)
 * right всегда расширяет.
 * while сжимает, пока сумма ещё держится >= target.
 * best обновляется внутри while — на каждом валидном окне.
 * Сложность
 * Время: O(n) — right и left двигаются только вперёд.
 * Память: O(1) — сумма и указатели.
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(target, nums));
        System.out.println(minSubArrayLen1(target, nums));
    }

    public static int minSubArrayLen1(int target, int[] nums) {
        int best = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                best = Math.min(best, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
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
