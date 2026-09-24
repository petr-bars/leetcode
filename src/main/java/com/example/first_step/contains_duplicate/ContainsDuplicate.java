package com.example.first_step.contains_duplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 * <p>
 * Дан массив целых чисел nums. Нужно вернуть true, если хотя бы одно значение встречается два раза или больше.
 * Если все элементы уникальны — вернуть false.
 * Вход: nums = [1,2,3,1]
 * Выход: true
 * Пояснение: 1 встречается дважды.
 * Заметка по паттерну
 * Паттерн: HashSet для проверки дубликатов.
 * Ключевая идея:
 * Идём по массиву и добавляем элементы в set.
 * Если элемент уже в set — дубликат найден.
 * set.add() возвращает false, если элемент уже был — можно использовать это напрямую.
 * Сложность
 * Время: O(n)
 * Память: O(n)
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
//        int[] nums = new int[]{2,14,18,22,22};
        int[] nums = new int[]{1, 2, 3, 1, 5};
//        int[] nums = new int[]{1, 2, 3, 7, 5};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }
}
