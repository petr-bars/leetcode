package com.example.second_step.two_sum2;

import java.util.*;

/**
 * <p>Given a 1-indexed array of integers numbers that is already sorted in
 * non-decreasing order, find two numbers such that they add up to a specific
 * target number. Let these two numbers be numbers[index1] and numbers[index2]
 * where 1 &lt;= index1 &lt; index2 &lt;= numbers.length.</p>
 *
 * <p>Return the indices of the two numbers index1 and index2, each incremented
 * by one, as an integer array [index1, index2] of length 2. The tests are
 * generated such that there is exactly one solution. You may not use the same
 * element twice. Your solution must use only constant extra space.</p>
 *
 * <p>Дан массив numbers, отсортированный по возрастанию. В условии индексы
 * 1-indexed, но в Java массив 0-indexed. Найти два числа, которые в сумме
 * дают target, и вернуть их индексы в 1-indexed в виде [index1, index2],
 * где index1 &lt; index2. Гарантируется ровно одно решение. Один и тот же
 * элемент нельзя использовать дважды.</p>
 *
 * <p>Пример:<br>
 * Вход:  numbers = [2,7,11,15], target = 9<br>
 * Выход: [1,2]<br>
 * Пояснение: 2 + 7 = 9. В 1-indexed это [1, 2].</p>
 *
 * <p>Пример:<br>
 * Вход:  numbers = [2,3,4], target = 6<br>
 * Выход: [1,3]<br>
 * Пояснение: 2 + 4 = 6.</p>
 *
 * <p>Паттерн:<br>
 * Два указателя навстречу.</p>
 *
 * <p>Именование переменных:<br>
 * left — указатель с начала массива. right — указатель с конца.
 * sum — сумма numbers[left] и numbers[right] на текущей итерации.
 * target — целевая сумма. numbers — входной отсортированный массив.</p>
 *
 * <p>Идея:<br>
 * Массив отсортирован, поэтому не нужен HashMap (как в Two Sum I).
 * Работаем двумя указателями: один с начала, другой с конца. Сравниваем
 * их сумму с target и двигаем один из указателей, отсекая неподходящие
 * варианты. Так каждый элемент проверяется не более одного раза.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Инициализация:
 *       <ul>
 *         <li>left = 0 — индекс начала массива.</li>
 *         <li>right = numbers.length - 1 — индекс конца.</li>
 *       </ul>
 *   </li>
 *   <li>Пока left &lt; right:
 *       <ul>
 *         <li>sum = numbers[left] + numbers[right].</li>
 *         <li>Если sum == target — нашли пару. Возвращаем
 *             new int[]{left + 1, right + 1}. Плюс 1 нужен, потому что
 *             условие ждёт 1-indexed.</li>
 *         <li>Если sum &lt; target — сумма слишком маленькая, нужно
 *             увеличить. Двигаем left вправо: left++. Тем самым берём
 *             большее число слева.</li>
 *         <li>Если sum &gt; target — сумма слишком большая, нужно
 *             уменьшить. Двигаем right влево: right--. Тем самым берём
 *             меньшее число справа.</li>
 *       </ul>
 *   </li>
 *   <li>Если цикл закончился — решения нет. По условиям LeetCode такого
 *       не бывает, но возвращаем new int[0] для безопасности.</li>
 * </ul>
 *
 * <p>Почему это работает:</p>
 * <ul>
 *   <li>Массив отсортирован. Если sum &lt; target, то numbers[left] +
 *       numbers[right] &lt; target. Для этого left любой right' &lt; right
 *       даст ещё меньшую сумму. Значит, с текущим left пары нет —
 *       можно смело двигать left.</li>
 *   <li>Симметрично: если sum &gt; target, для этого right любой left' &gt;
 *       left даст ещё большую сумму. Пара с текущим right невозможна —
 *       двигаем right.</li>
 *   <li>Так каждый шаг отсекает один элемент и гарантированно не пропускает
 *       правильную пару.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Возврат left + 1, right + 1 — критично. LeetCode ждёт 1-indexed
 *       индексы. Если вернуть 0-indexed, ответ будет неверным.</li>
 *   <li>Порядок ветвлений: сначала проверка sum == target, потом
 *       sum &lt; target, иначе sum &gt; target. Можно и через else if
 *       цепочку — все три случая взаимоисключающие.</li>
 *   <li>Один и тот же элемент не используется дважды, потому что условие
 *       цикла left &lt; right. Когда left == right, цикл останавливается —
 *       сравнить элемент сам с собой нельзя.</li>
 *   <li>Если решения нет, возвращаем new int[0]. По условиям LeetCode
 *       такого не бывает, но защита не помешает.</li>
 *   <li>Пустой массив не обрабатывается — numbers[left] упадёт. По условиям
 *       LeetCode n &gt;= 2, так что защита не нужна.</li>
 *   <li>Не используется HashMap, как в Two Sum I, потому что массив
 *       отсортирован. Это даёт O(1) памяти вместо O(n).</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>numbers = [2,7,11,15], target = 9 → [1,2].</li>
 *   <li>numbers = [2,3,4], target = 6 → [1,3].</li>
 *   <li>numbers = [-1,0], target = -1 → [1,2]. Отрицательные.</li>
 *   <li>numbers = [1,2], target = 3 → [1,2]. Минимальный размер.</li>
 *   <li>numbers = [-5,-3,0,2,4], target = -1 → [1,4].
 *       -5 + 4 = -1.</li>
 *   <li>numbers = [1,3,5,7,9], target = 10 → [2,4]. 3 + 7 = 10.</li>
 *   <li>числа с одинаковыми значениями: [3,3], target = 6 → [1,2].</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — left и right двигаются навстречу, каждый суммарно
 *       не более n раз.<br>
 * Память: O(1) — только два указателя и sum.</p>
 */
public class TwoSumInputArrayIsSorted {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 10};
        int target = 9;

        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}
