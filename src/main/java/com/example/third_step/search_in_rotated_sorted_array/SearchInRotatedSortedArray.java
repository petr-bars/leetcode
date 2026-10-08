package com.example.third_step.search_in_rotated_sorted_array;

/**
 * <p>There is an integer array nums sorted in ascending order (with distinct
 * values). Prior to being passed to your function, nums is possibly left
 * rotated at an unknown index k. For example, [0,1,2,4,5,6,7] might become
 * [4,5,6,7,0,1,2].</p>
 *
 * <p>Given the rotated array nums and an integer target, return the index
 * of target if it is in nums, or -1 if it is not. O(log n) time complexity.</p>
 *
 * <p>Дан массив nums, отсортированный по возрастанию (все значения уникальны),
 * возможно сдвинутый влево на неизвестный индекс k. Вернуть индекс target
 * или -1, если его нет. Время — O(log n).</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [4,5,6,7,0,1,2], target = 0<br>
 * Выход: 4</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [4,5,6,7,0,1,2], target = 3<br>
 * Выход: -1</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск с определением отсортированной половины.</p>
 *
 * <p>Идея:<br>
 * Массив сдвинут, но не «сломан». Если разделить отрезок пополам — хотя бы
 * одна половина отсортирована. Сначала определяем, какая именно, потом
 * проверяем, лежит ли target в ней. Если да — идём туда, если нет — в другую.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>left = 0, right = n − 1.</li>
 *   <li>mid = left + (right − left) / 2.</li>
 *   <li>Если nums[mid] == target → вернуть mid.</li>
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
 *   <li>Повторять, пока left ≤ right. Вернуть −1.</li>
 * </ul>
 *
 * <p>Почему у mid строгое неравенство, а у границ — нестрогое:<br>
 * Со стороны mid — всегда строгое (&lt; или &gt;), потому что mid уже проверен
 * на равенство target в начале итерации. Если бы mid был target, мы бы уже
 * вышли. Со стороны left и right — нестрогое (≤ или ≥), потому что эти
 * границы ещё не проверены и могут совпасть с target.</p>
 *
 * <p>Сложность:<br>
 * Время: O(log n).<br>
 * Память: O(1).</p>
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 7;

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
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
        return -1;
    }
}
