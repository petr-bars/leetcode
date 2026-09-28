package com.example.third_step.search_in_rotated_sorted_arrayII;

/**
 * <p>There is an integer array nums sorted in non-decreasing order (not necessarily
 * with distinct values). Before being passed to your function, nums is rotated at an
 * unknown pivot index k (0 &lt;= k &lt; nums.length) such that the resulting array is
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become
 * [4,5,6,6,7,0,1,2,4,4].</p>
 *
 * <p>Given the array nums after the rotation and an integer target, return true
 * if target is in nums, or false if it is not in nums. You must decrease the
 * overall operation steps as much as possible.</p>
 *
 * <p>Дан массив nums, отсортированный по неубыванию (значения могут повторяться),
 * возможно сдвинутый на неизвестный индекс k. Например, [0,1,2,4,4,4,5,6,6,7]
 * после сдвига на 5 становится [4,5,6,6,7,0,1,2,4,4]. Дан target. Вернуть true,
 * если target есть в массиве, иначе false.</p>
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
 * Задача похожа на Search in Rotated Sorted Array, но с одним отличием —
 * значения могут повторяться. Из-за этого теряется ключевое свойство:
 * по сравнению nums[left] и nums[mid] нельзя однозначно определить, какая
 * половина отсортирована. Например, [1,0,1,1,1] — nums[left] = 1,
 * nums[mid] = 1, но левая половина НЕ отсортирована. Причина — дубликаты
 * «маскируют» точку сдвига. Решение: когда nums[left] == nums[mid] == nums[right],
 * мы не можем ничего сказать — просто сужаем отрезок с обоих концов на один шаг
 * и продолжаем. В остальных случаях работаем как в задаче без дубликатов.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Инициализация. Левая граница — начало массива, правая — конец.</li>
 *   <li>Пока отрезок не пуст (левая граница не перешла за правую):
 *       <ul>
 *         <li>Берём середину. mid = left + (right - left) / 2.</li>
 *         <li>Если nums[mid] == target → нашли, вернуть true.</li>
 *         <li>Проверяем случай неопределённости:
 *             <ul>
 *               <li>Если nums[left] == nums[mid] == nums[right], то
 *                   невозможно определить, где сортировка, и где target.
 *                   Тогда сужаем отрезок с обоих концов на один шаг:
 *                   left++, right--. И переходим к следующей итерации.</li>
 *             </ul>
 *         </li>
 *         <li>Если определённость есть, работаем как в задаче без дубликатов:
 *             <ul>
 *               <li>Определяем, какая половина отсортирована:
 *                   <ul>
 *                     <li>Если nums[left] &lt;= nums[mid] → левая отсортирована.</li>
 *                     <li>Иначе → правая отсортирована.</li>
 *                   </ul>
 *               </li>
 *               <li>Если левая отсортирована:
 *                   <ul>
 *                     <li>Если nums[left] &lt;= target &lt; nums[mid] →
 *                         right = mid - 1.</li>
 *                     <li>Иначе → left = mid + 1.</li>
 *                   </ul>
 *               </li>
 *               <li>Если правая отсортирована:
 *                   <ul>
 *                     <li>Если nums[mid] &lt; target &lt;= nums[right] →
 *                         left = mid + 1.</li>
 *                     <li>Иначе → right = mid - 1.</li>
 *                   </ul>
 *               </li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>Если цикл закончился — target не найден, вернуть false.</li>
 * </ul>
 *
 * <p>Ключевая тонкость (случай неопределённости):<br>
 * Когда nums[left] == nums[mid] == nums[right], массив выглядит одинаково
 * «со всех сторон», и по значениям границ невозможно понять, где сортировка.
 * В такой ситуации единственный безопасный шаг — сузить отрезок с двух сторон
 * на один. Это не сломает поиск, потому что если target равен этому значению,
 * он бы уже нашёлся на предыдущей проверке nums[mid]. А если не равен —
 * эти крайние элементы можно выкинуть без потери.</p>
 *
 * <p>Ключевая тонкость (сложность):<br>
 * Из-за дубликатов худший случай деградирует до O(n). Например,
 * [1,1,1,1,0,1,1] — почти весь массив из единиц, а target = 0. Придётся
 * много раз сужать с двух сторон, пока отрезок не сожмётся до нуля. В среднем
 * работает быстро, но формально верхняя граница O(n). Это ожидаемо и
 * принимается на собеседовании, если уметь объяснить почему.</p>
 *
 * <p>Типичные ошибки:</p>
 * <ul>
 *   <li>Забыть про случай nums[left] == nums[mid] == nums[right] и пытаться
 *       работать как с обычным rotated array. Получишь неверный ответ или
 *       бесконечный цикл.</li>
 *   <li>Пытаться определить сортированную половину при равенстве nums[left]
 *       и nums[mid]. При дубликатах это не всегда значит, что левая половина
 *       отсортирована.</li>
 *   <li>Сужать только с одной стороны при неопределённости. Надо с обеих,
 *       иначе можно застрять.</li>
 *   <li>Забыть, что после сужения left++/right-- надо перейти к следующей
 *       итерации, не сравнивая target ещё раз.</li>
 *   <li>Использовать строгое неравенство при проверке nums[left] &lt;= nums[mid].
 *       Случай равенства должен идти в левую половину.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>target в левой отсортированной части.</li>
 *   <li>target в правой отсортированной части.</li>
 *   <li>target в точке сдвига.</li>
 *   <li>target отсутствует, но между элементами.</li>
 *   <li>массив без сдвига (обычный отсортированный с дубликатами).</li>
 *   <li>массив полностью из одинаковых элементов, target совпадает.</li>
 *   <li>массив полностью из одинаковых элементов, target не совпадает.</li>
 *   <li>массив с точкой сдвига внутри группы дубликатов.</li>
 *   <li>массив из одного элемента, target совпадает.</li>
 *   <li>массив из одного элемента, target не совпадает.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(log n) в среднем, O(n) в худшем (из-за дубликатов).<br>
 * Память: O(1) — только границы и середина.</p>
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
