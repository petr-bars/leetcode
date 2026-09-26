package com.example.second_step.two_sum2;

import java.util.*;

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * Return the indices of the two numbers index1 and index2,
 * each incremented by one, as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * Your solution must use only constant extra space.
 * <p>
 * Дан массив numbers, отсортированный по возрастанию (1-indexed в условии, но в коде 0-indexed).
 * Нужно найти два числа, которые в сумме дают target, и вернуть их индексы (1-indexed) в виде
 * [index1, index2], где index1 < index2.
 * Гарантируется:
 * Ровно одно решение.
 * Нельзя использовать один и тот же элемент дважды.
 * Пример:
 * Вход: numbers = [2, 7, 11, 15], target = 9
 * Выход: [1, 2]
 * Потому что 2 + 7 = 9. В 1-indexed это [1, 2].
 * Паттерн
 * Два указателя навстречу (left, right).
 * Ключевая идея
 * Массив отсортирован, поэтому не нужен HashMap (как в Two Sum I). Работаем двумя указателями:
 * left = 0, right = numbers.length - 1.
 * Пока left < right:
 * Считаем sum = numbers[left] + numbers[right].
 * Если sum == target → возвращаем [left + 1, right + 1] (1-indexed).
 * Если sum < target → сумма слишком маленькая, нужно увеличить → left++.
 * Если sum > target → сумма слишком большая, нужно уменьшить → right--.
 * Почему это работает
 * Массив отсортирован.
 * Если сумма меньше цели, единственный способ её увеличить — сдвинуть левый указатель вправо (взять число больше).
 * Если сумма больше цели, единственный способ её уменьшить — сдвинуть правый указатель влево (взять число меньше).
 * Мы никогда не пропустим правильную пару.
 * Сложность
 * Время: O(n) — каждый указатель двигается не более n раз.
 * Память: O(1) — без дополнительных структур.
 */
public class TwoSumInputArrayIsSorted {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 10};
        int target = 9;

        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[0];
    }
}
