package com.example.third_step.find_minimum_in_rotated_sorted_array;

/**
 * <p>Suppose an array of length n sorted in ascending order is rotated between
 * 1 and n times. For example, [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]
 * after 4 rotations.</p>
 *
 * <p>Given the sorted rotated array nums of unique elements, return the minimum
 * element. You must write an algorithm that runs in O(log n) time.</p>
 *
 * <p>Дан отсортированный по возрастанию массив nums (все элементы уникальны),
 * сдвинутый вправо. Найти минимальный элемент. Решение должно работать
 * за O(log n).</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [3,4,5,1,2]<br>
 * Выход: 1</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [4,5,6,7,0,1,2]<br>
 * Выход: 0</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [11,13,15,17]<br>
 * Выход: 11</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск по точке разрыва сортировки.</p>
 *
 * <p>Идея:<br>
 * Минимум — это точка разрыва сортировки. Сравниваем nums[mid] с nums[right]:
 * если mid больше правого края — разрыв правее, идём вправо. Если меньше —
 * разрыв на mid или левее, идём влево, не исключая mid. Сравниваем именно
 * с nums[right], потому что он всегда в «нижней» части массива и даёт
 * однозначный ответ, с какой стороны разрыв.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>left = 0, right = n − 1.</li>
 *   <li>mid = left + (right − left) / 2.</li>
 *   <li>Если nums[mid] &gt; nums[right] → left = mid + 1.</li>
 *   <li>Иначе → right = mid.</li>
 *   <li>Повторять, пока left &lt; right.</li>
 *   <li>Вернуть nums[left].</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(log n).<br>
 * Память: O(1).</p>
 */
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 1, 2};

        System.out.println(findMin(nums));
    }

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
