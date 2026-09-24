package com.example.first_step.longest_consecutive_sequence;

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
 * Ключевая идея
 * Кладём все числа в HashSet — чтобы за O(1) отвечать на вопрос «есть ли такое число».
 * Дальше для каждого числа num проверяем: есть ли в сете число num - 1?
 * Есть → это не начало цепочки. Пропускаем. Эту цепочку посчитают, когда дойдут до её настоящего начала.
 * Нет → это начало. Идём вправо: num + 1, num + 2, ... пока они есть в сете. Считаем длину.
 * Почему именно так: если num - 1 нет, значит, слева от num цепочка не продолжается — num первый.
 * Если бы мы начинали считать с середины, то посчитали бы кусок цепочки, а не всю её длину.
 * А так каждая цепочка считается ровно один раз — от своего начала.
 * Сложность:
 * O(n) время,
 * O(n) память.
 * Где ещё применимо: задачи на последовательности, интервалы, «есть ли цепочка».
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 1, 1, 3, 2};
//        int[] nums = new int[]{1, 2, 3, 4, 5, 10, 11, 20, 21, 22, 23, 24, 25};

        System.out.println(longestConsecutiveWithHashSet(nums));

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
