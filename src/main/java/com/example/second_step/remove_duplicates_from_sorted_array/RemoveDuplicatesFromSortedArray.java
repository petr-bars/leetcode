package com.example.second_step.remove_duplicates_from_sorted_array;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same.
 * Consider the number of unique elements in nums to be k.
 * After removing duplicates, return the number of unique elements k.
 * The first k elements of nums should contain the unique numbers in sorted order.
 * The remaining elements beyond index k - 1 can be ignored.
 * <p>
 * Дан отсортированный массив nums. Удалить дубликаты на месте так, чтобы каждый уникальный элемент встречался один раз. Вернуть k — количество уникальных.
 * Первые k ячеек должны содержать уникальные в исходном порядке. Остальное — не важно.
 * Пример:
 * Вход:  nums = [1,1,2]
 * Выход: k = 2, nums = [1,2,_]
 * Паттерн
 * Read/Write pointers — тот же, что в Move Zeroes.
 * Идея
 * Массив отсортирован → все дубликаты стоят подряд. Значит, достаточно сравнивать текущий элемент с последним уникальным.
 * Два указателя:
 * write — где лежит последний уникальный (медленный). Начинается с 0.
 * read — что читаем (быстрый). Идёт с 1.
 * Если nums[read] != nums[write] — нашли новый уникальный. Сдвигаем write вперёд и кладём туда nums[read].
 * Формула
 * write = 0
 * для read от 1 до n-1:
 * если nums[read] ≠ nums[write]:
 * write++
 * nums[write] = nums[read]
 * вернуть write + 1
 * write — индекс последнего уникального. write + 1 — их количество.
 * Сложность
 * Время: O(n) — один проход.
 * Память: O(1) — на месте.
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] nums = new int[]{1, 1, 2, 3};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int write = 0;
        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[write]) {
                write++;
                nums[write] = nums[read];
            }
        }

        return write + 1;
    }
}
