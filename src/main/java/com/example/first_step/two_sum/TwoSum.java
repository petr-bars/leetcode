package com.example.first_step.two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Given an array of integers nums and an integer target, return indices of
 * the two numbers such that they add up to target. You may assume that each
 * input would have exactly one solution, and you may not use the same element
 * twice. You can return the answer in any order.</p>
 *
 * <p>Дан массив целых чисел nums и число target. Найти индексы двух чисел,
 * сумма которых равна target. Гарантируется, что решение ровно одно. Один
 * и тот же элемент нельзя использовать дважды. Порядок ответа не важен.</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [2,7,11,15], target = 9<br>
 * Выход: [0,1]</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [3,2,4], target = 6<br>
 * Выход: [1,2]</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [3,3], target = 6<br>
 * Выход: [0,1]</p>
 *
 * <p>Паттерн:<br>
 * Хеш-таблица (HashMap) для поиска дополнения за O(1).</p>
 *
 * <p>Именование переменных:<br>
 * seen — HashMap, где ключ — число из массива, значение — его индекс.
 * Хранит все числа, которые мы уже прошли, и их позиции. complement —
 * число, которое нужно найти в seen, чтобы в сумме с текущим получился
 * target. currentIndex — текущая позиция в массиве. currentValue —
 * значение nums[currentIndex].</p>
 *
 * <p>Идея:<br>
 * Наивный подход — два вложенных цикла, O(n²). Но можно за один проход:
 * идём по массиву слева направо, и для каждого числа проверяем, встречалось
 * ли раньше его дополнение до target. Если да — нашли пару. Если нет —
 * запоминаем текущее число и его индекс, чтобы оно могло стать дополнением
 * для кого-то справа. Хеш-таблица даёт проверку «встречалось ли раньше»
 * за O(1).</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Создаём пустую HashMap seen: ключ — число, значение — индекс.</li>
 *   <li>Идём по массиву nums с индексом currentIndex от 0 до n-1:
 *       <ul>
 *         <li>currentValue = nums[currentIndex].</li>
 *         <li>complement = target - currentValue. Это число, которое в паре
 *             с currentValue даёт target.</li>
 *         <li>Проверяем: есть ли complement в seen?
 *             <ul>
 *               <li>Да → нашли пару. Возвращаем массив из двух индексов:
 *                   seen.get(complement) и currentIndex.</li>
 *               <li>Нет → записываем currentValue в seen с индексом
 *                   currentIndex. Это число может стать дополнением для
 *                   кого-то справа.</li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>Если цикл закончился без ответа — решения нет. По условиям LeetCode
 *       такого не бывает, но на всякий случай возвращаем пустой массив
 *       или бросаем исключение.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Порядок операций в цикле критичен: сначала проверяем complement
 *       в seen, потом записываем currentValue. Если сделать наоборот,
 *       для случая nums = [3,3], target = 6 на первой итерации запишем
 *       первое 3, на второй увидим complement = 3, найдём его — вернём
 *       [0, 1]. Это правильно. Но если записать текущее число ПЕРЕД
 *       проверкой, то для nums = [3,3] на первой итерации мы бы нашли
 *       «самого себя» — вернули [0,0]. Это неверно.</li>
 *   <li>Дополнение — это target - currentValue, а не currentValue - target.
 *       Проверка простая: currentValue + complement = target.</li>
 *   <li>Ключ в HashMap — число, а не индекс. Значение — индекс, а не число.
 *       Не путать. Мы ищем число в seen, а возвращаем его индекс.</li>
 *   <li>Один проход вместо двух вложенных. Внешний цикл по индексам,
 *       внутренний «поиск» заменён на обращение к HashMap.</li>
 *   <li>Дубликаты в массиве — норма. Для nums = [3,3], target = 6 первое 3
 *       записывается в seen, второе 3 находит complement = 3 в seen и даёт
 *       ответ [0, 1].</li>
 *   <li>Гарантия ровно одного решения означает, что можно вернуть сразу,
 *       как только нашли пару. Досматривать массив не нужно.</li>
 *   <li>Порядок индексов в ответе не важен. LeetCode принимает любой.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>nums = [2,7,11,15], target = 9 → [0,1].</li>
 *   <li>nums = [3,2,4], target = 6 → [1,2].</li>
 *   <li>nums = [3,3], target = 6 → [0,1]. Дубликаты.</li>
 *   <li>nums = [1,2], target = 3 → [0,1]. Минимальный размер.</li>
 *   <li>nums = [-1,-2,-3,-4,-5], target = -8 → [2,4]. Отрицательные числа.</li>
 *   <li>nums = [0,4,3,0], target = 0 → [0,3]. Два нуля.</li>
 *   <li>nums = [-3,4,3,90], target = 0 → [0,2]. Отрицательное и
 *       положительное.</li>
 *   <li>пара в самом начале массива.</li>
 *   <li>пара в самом конце массива.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — один проход по массиву, каждое обращение к HashMap
 *       в среднем за O(1).<br>
 * Память: O(n) — HashMap хранит до n элементов.</p>
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSumMap(nums, target)));
    }

    public static int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            int currentValue = nums[index];
            int complement = target - currentValue;
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), index};
            }
            seen.put(currentValue, index);
        }
        return new int[0];
    }
}
