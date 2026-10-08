package com.example.third_step.binary_search;

/**
 * <p>Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.</p>
 *
 * <p>Дан отсортированный по возрастанию массив nums и число target.
 * Найти индекс target. Если его нет — вернуть -1.</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [-1,0,3,5,9,12], target = 9<br>
 * Выход: 4</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск — деление отрезка пополам.</p>
 *
 * <p>Идея:<br>
 * Массив отсортирован. Держим отрезок, где может быть target. Смотрим
 * на середину: если равна — нашли. Если меньше — target правее, отбрасываем
 * левую половину. Если больше — отбрасываем правую. Каждый шаг сокращает
 * отрезок вдвое.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>левая граница — начало, правая — конец.</li>
 *   <li>пока левая не перешла за правую:
 *       <ul>
 *         <li>mid = left + (right − left) / 2.</li>
 *         <li>если значение в середине равно target — вернуть индекс
 *             середины;</li>
 *         <li>если значение в середине меньше target — сдвинуть левую
 *             границу за середину;</li>
 *         <li>иначе — сдвинуть правую границу перед серединой.</li>
 *       </ul>
 *   </li>
 *   <li>вернуть −1.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(log n).<br>
 * Память: O(1).</p>
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,3,5,9,12};
        int target = 12;

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
