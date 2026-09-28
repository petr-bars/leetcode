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
 * <p>
 * Паттерн:
 * Sliding window (variable) — окно переменного размера + поддержание суммы внутри.
 * <p>
 * Идея:
 * Держим окно и считаем сумму элементов внутри.
 * Правая граница расширяет окно, пока сумма не достигнет target.
 * Как только сумма стала достаточной — окно валидное. Мы запоминаем его длину.
 * И сразу начинаем жадно сжимать окно слева, чтобы найти окно еще короче, но всё ещё с суммой >= target.
 * <p>
 * Как именно мы находим и обновляем минимальную длину:
 * Изначально переменная minLength установлена в бесконечность.
 * Пока сумма окна >= target, мы вычисляем длину текущего окна и обновляем минимум по формуле:
 * Сравниваем сохраненный минимум с длиной текущего окна (правая граница минус левая плюс один)
 * и записываем в minLength меньшее из двух значений.
 * После обновления формулы мы сжимаем окно (сдвигаем left) и повторяем процесс, пока сумма >= target.
 * Если minLength так и не обновился (остался бесконечностью), возвращаем 0.
 * <p>
 * Ключевое отличие от поиска максимума:
 * Там мы искали МАКСИМУМ, поэтому сжимали окно ТОЛЬКО когда условие нарушалось.
 * Здесь мы ищем МИНИМУМ, поэтому сжимаем окно ИМЕННО ТОГДА, когда условие выполнилось,
 * чтобы проверить, можно ли найти окно еще короче.
 * <p>
 * Сложность:
 * Время: O(n) — правая и левая границы двигаются только вперед.
 * Память: O(1) — сумма и указатели.
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
