package com.example.first_step.longest_consecutive_sequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Дан неотсортированный массив целых чисел nums.
 * Нужно вернуть длину самой длинной последовательности подряд идущих чисел (например, 1, 2, 3, 4 или 10, 11, 12).
 * Важно: числа не обязаны стоять рядом в массиве. Они могут быть в любом порядке.
 * Важно только, чтобы они шли подряд по значению.
 * Пример:
 * Вход: nums = [100, 4, 200, 1, 3, 2]
 * Выход: 4
 * Потому что самая длинная последовательность — 1, 2, 3, 4.
 * <p>
 * Заметка по паттерну
 * Паттерн: HashSet + поиск начала последовательности.
 * Ключевая идея:
 * Все числа в HashSet.
 * Начало последовательности — число, у которого num - 1 нет в set.
 * От каждого начала идём вверх через while (set.contains(current + 1)).
 * Что важно запомнить:
 * Идём по set, а не по массиву.
 * Условие !contains(num - 1) — ключевое для O(n).
 * Считать длину и обновлять максимум — только внутри if.
 * Сложность:
 * O(n) время,
 * O(n) память.
 * Где ещё применимо: задачи на последовательности, интервалы, «есть ли цепочка».
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 1, 1, 3, 2};

        System.out.println(longestConsecutiveWithSort(nums));
        System.out.println(longestConsecutiveWithHashSet(nums));
    }

    public static int longestConsecutiveWithSort(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int counter = 1;
        int result = 0;
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] == nums[index - 1]) {
                continue;
            }

            if (nums[index] == nums[index - 1] + 1) {
                counter++;
            } else if (counter > result) {
                result = counter;
                counter = 1;
            }
        }
        return Math.max(result, counter);
    }

    public static int longestConsecutiveWithHashSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int item : nums) {
            set.add(item);
        }

        int best = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int counter = 1;
                int current = num;
                while (set.contains(current + 1)) {
                    current++;
                    counter++;
                }
                if (counter > best) {
                    best = counter;
                }
            }
        }

        return best;
    }
}
