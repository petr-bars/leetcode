package com.example.third_step.search_in_rotated_sorted_arrayII;

/**
 * <p>There is an integer array nums sorted in non-decreasing order (not
 * necessarily with distinct values). Before being passed to your function,
 * nums is rotated at an unknown pivot index k. For example, [0,1,2,4,4,4,5,6,6,7]
 * might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].</p>
 *
 * <p>Given the rotated array nums and an integer target, return true if target
 * is in nums, or false if it is not. You must decrease the overall operation
 * steps as much as possible.</p>
 *
 * <p>Дан массив nums, отсортированный по неубыванию (значения могут повторяться),
 * возможно сдвинутый на неизвестный индекс k. Вернуть true, если target есть
 * в массиве, иначе false.</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [2,5,6,0,0,1,2], target = 0<br>
 * Выход: true</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [2,5,6,0,0,1,2], target = 3<br>
 * Выход: false</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск с обработкой дубликатов.</p>
 *
 * <p>Идея:<br>
 * Задача похожа на Search in Rotated Sorted Array, но значения могут
 * повторяться. Из-за дубликатов теряется ключевое свойство: по сравнению
 * nums[left] и nums[mid] нельзя однозначно определить, какая половина
 * отсортирована. Когда nums[left] == nums[mid] == nums[right] — мы не можем
 * ничего сказать, сужаем отрезок с обоих концов на один шаг. В остальных
 * случаях работаем как в задаче без дубликатов.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>left = 0, right = n − 1.</li>
 *   <li>mid = left + (right − left) / 2.</li>
 *   <li>Если nums[mid] == target → вернуть true.</li>
 *   <li>Если nums[left] == nums[mid] == nums[right] → left++, right--,
 *       перейти к следующей итерации.</li>
 *   <li>Если nums[left] ≤ nums[mid] — левая половина отсортирована:
 *       <ul>
 *         <li>если nums[left] ≤ target &lt; nums[mid] → right = mid − 1;</li>
 *         <li>иначе → left = mid + 1.</li>
 *       </ul>
 *   </li>
 *   <li>Иначе — правая половина отсортирована:
 *       <ul>
 *         <li>если nums[mid] &lt; target ≤ nums[right] → left = mid + 1;</li>
 *         <li>иначе → right = mid − 1.</li>
 *       </ul>
 *   </li>
 *   <li>Повторять, пока left ≤ right. Вернуть false.</li>
 * </ul>
 *
 * <p>Почему в случае неопределённости сужаем с двух сторон:<br>
 * Когда nums[left] == nums[mid] == nums[right], по значениям границ
 * невозможно понять, где разрыв сортировки. Если target равен этому
 * значению — он бы уже нашёлся на проверке nums[mid]. Если не равен —
 * крайние элементы можно безопасно выкинуть, они не могут быть target.</p>
 *
 * <p>Сложность:<br>
 * Время: O(log n) в среднем, O(n) в худшем (из-за дубликатов).<br>
 * Память: O(1).</p>
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
//        int[] nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        int[] nums = new int[]{1, 0, 1, 1, 1};
        int target = 0;

        System.out.println(search(nums, target));
    }

    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return false;
    }
}
