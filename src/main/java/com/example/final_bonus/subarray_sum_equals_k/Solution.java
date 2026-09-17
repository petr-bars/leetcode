package com.example.final_bonus.subarray_sum_equals_k;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Считает количество подмассивов массива {@code nums}, сумма элементов
     * которых равна заданному числу {@code k}.
     * <p>
     * <b>Подход:</b> префиксные суммы + HashMap (Prefix Sum + HashMap).
     * <p>
     * <b>Идея:</b> введём префиксную сумму:
     * <pre>
     * prefix[i] = nums[0] + nums[1] + ... + nums[i-1]
     * prefix[0] = 0
     * </pre>
     * Тогда сумма подмассива от {@code i} до {@code j} включительно равна:
     * <pre>
     * sum(i..j) = prefix[j + 1] - prefix[i]
     * </pre>
     * Нам нужно, чтобы {@code sum(i..j) == k}. Значит:
     * <pre>
     * prefix[j + 1] - prefix[i] = k
     * prefix[i] = prefix[j + 1] - k
     * </pre>
     * <b>Ключевой вывод:</b> когда мы дошли до текущей префиксной суммы
     * {@code currentSum = prefix[j + 1]}, нам нужно узнать, сколько раз
     * <b>раньше</b> встречалась сумма {@code currentSum - k}. Каждое такое
     * вхождение даёт один подмассив с суммой {@code k}, заканчивающийся
     * в текущей позиции.
     * <p>
     * <b>Алгоритм:</b>
     * <ol>
     *   <li>Создаётся {@link HashMap} {@code prefixCount}, где ключ — значение
     *       префиксной суммы, значение — сколько раз она встречалась.</li>
     *   <li>Кладётся базовое значение {@code prefixCount.put(0, 1)} — пустая
     *       префиксная сумма встречается один раз до начала массива. Это нужно,
     *       чтобы учитывать подмассивы, начинающиеся с индекса 0.</li>
     *   <li>Заводятся {@code currentSum = 0} и {@code count = 0}.</li>
     *   <li>Идём по массиву слева направо:
     *     <ul>
     *       <li>Прибавляем текущий элемент к {@code currentSum}.</li>
     *       <li>К {@code count} прибавляем количество вхождений суммы
     *           {@code currentSum - k} в {@code prefixCount}. Если такой суммы
     *           нет, {@code getOrDefault} вернёт 0.</li>
     *       <li>Увеличиваем счётчик для {@code currentSum} в {@code prefixCount}
     *           на 1.</li>
     *     </ul>
     *   </li>
     *   <li>Возвращаем {@code count}.</li>
     * </ol>
     * <p>
     * <b>Почему это работает с отрицательными числами:</b>
     * подход не требует, чтобы элементы были положительными. Префиксные суммы
     * могут убывать и возрастать, но формула разности работает всегда.
     * Именно поэтому здесь нельзя использовать скользящее окно (sliding window),
     * которое требует монотонности.
     * <p>
     * <b>Пример 1:</b>
     * <pre>
     * nums = [1, 1, 1], k = 2
     *
     * prefixCount = {0: 1}
     * currentSum = 0, count = 0
     *
     * num=1: currentSum=1, ищем -1 → 0; count=0; put(1, 1)
     * num=1: currentSum=2, ищем  0 → 1; count=1; put(2, 1)
     * num=1: currentSum=3, ищем  1 → 1; count=2; put(3, 1)
     *
     * Ответ: 2 (подмассивы [1,1] и [1,1])
     * </pre>
     * <p>
     * <b>Пример 2 (с отрицательными числами):</b>
     * <pre>
     * nums = [1, -1, 1], k = 1
     *
     * prefixCount = {0: 1}
     * num=1:  currentSum=1, ищем  0 → 1; count=1; put(1, 1)   → map={0:1, 1:1}
     * num=-1: currentSum=0, ищем -1 → 0; count=1; put(0, 2)   → map={0:2, 1:1}
     * num=1:  currentSum=1, ищем  0 → 2; count=3; put(1, 2)   → map={0:2, 1:2}
     *
     * Ответ: 3 (подмассивы [1], [1,-1,1], [1])
     * </pre>
     * <p>
     * <b>Сложность:</b>
     * <ul>
     *   <li>Время: O(n), где n — длина массива. Один проход по массиву,
     *       каждая операция с {@link HashMap} амортизированно O(1).</li>
     *   <li>Память: O(n) в худшем случае — все префиксные суммы разные,
     *       и каждая хранится в map.</li>
     * </ul>
     * <p>
     * <b>Альтернативные подходы и почему они хуже:</b>
     * <ul>
     *   <li><b>Наивный:</b> для каждой пары (i, j) считать сумму заново — O(n²)
     *       или O(n³). Слишком медленно при n &gt; 10⁴.</li>
     *   <li><b>Скользящее окно:</b> работает только при неотрицательных числах.
     *       Здесь условие этого не гарантирует, поэтому не подходит.</li>
     * </ul>
     *
     * @param nums целочисленный массив, может содержать отрицательные числа
     * @param k    целевая сумма подмассива
     * @return количество подмассивов с суммой, равной {@code k}
     * @see <a href="https://leetcode.com/problems/subarray-sum-equals-k/">LeetCode 560</a>
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num;

            count += prefixCount.getOrDefault(currentSum - k, 0);

            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);

        }
        return count;
    }
}
