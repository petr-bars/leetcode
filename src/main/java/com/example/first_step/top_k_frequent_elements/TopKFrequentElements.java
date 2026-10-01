package com.example.first_step.top_k_frequent_elements;

import java.util.*;

/**
 * <p>Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.</p>
 *
 * <p>Follow up: Your algorithm's time complexity must be better than O(n log n),
 * where n is the array's size.</p>
 *
 * <p>Дан целочисленный массив nums и целое число k. Вернуть k самых часто
 * встречающихся элементов массива. Порядок — любой. Сложность должна быть
 * лучше, чем O(n log n).</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [1,1,1,2,2,3], k = 2<br>
 * Выход: [1, 2]<br>
 * Пояснение: 1 встречается 3 раза, 2 — 2 раза, 3 — 1 раз. Топ-2: 1 и 2.</p>
 *
 * <p>Паттерн:<br>
 * Bucket sort по частотам.</p>
 *
 * <p>Именование переменных:<br>
 * frequencyMap — HashMap, ключ — число из массива, значение — сколько раз
 * оно встречается. item — текущее число при подсчёте частот. buckets —
 * массив списков длиной n + 1. Индекс — частота, значение — список чисел
 * с этой частотой. number — текущее число при раскладке по ведрам.
 * frequency — частота числа number. result — список для ответа.
 * bucketIndex — текущий индекс в массиве buckets (он же частота, которую
 * сейчас обрабатываем). value — текущий элемент внутри ведра.</p>
 *
 * <p>Идея:<br>
 * Задача просит сложность лучше, чем O(n log n) — значит, сортировка
 * не подходит. Но частоты — это маленькие целые числа от 1 до n. Раз так,
 * можно не сортировать, а разложить числа по ведрам: индекс ведра — частота,
 * значение — список чисел с этой частотой. Затем пройти по ведрам с конца
 * (от высокой частоты к низкой) и собрать k чисел. Все шаги линейные,
 * итого O(n).</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Считаем частоты:
 *       <ul>
 *         <li>Создаём пустую HashMap frequencyMap.</li>
 *         <li>Идём по массиву nums, для каждого item вызываем
 *             merge(item, 1, Integer::sum). Это увеличивает счётчик на 1,
 *             если ключ есть, или кладёт 1, если ключа нет.</li>
 *       </ul>
 *   </li>
 *   <li>Создаём ведра:
 *       <ul>
 *         <li>Массив buckets длиной nums.length + 1. Индекс — частота,
 *             значение — список чисел с этой частотой.</li>
 *         <li>Идём по keySet мапы, берём каждое number и его frequency
 *             через frequencyMap.get(number).</li>
 *         <li>Если buckets[frequency] == null — создаём новый ArrayList
 *             и кладём туда number.</li>
 *         <li>Иначе — добавляем number в существующий список.</li>
 *       </ul>
 *   </li>
 *   <li>Собираем ответ:
 *       <ul>
 *         <li>Идём по buckets с конца: bucketIndex от buckets.length - 1
 *             вниз до 1.</li>
 *         <li>Условие внешнего цикла — result.size() &lt; k. Как только
 *             набрали k, останавливаемся.</li>
 *         <li>Если buckets[bucketIndex] != null — идём по всем value
 *             внутри этого ведра.</li>
 *         <li>Добавляем value в result.</li>
 *         <li>После каждого добавления проверяем: result.size() == k?
 *             Если да — break из внутреннего цикла.</li>
 *       </ul>
 *   </li>
 *   <li>Преобразуем result в int[] через stream и возвращаем.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Размер buckets — n + 1, а не n. Максимальная частота — n (когда
 *       все числа одинаковые). Индекс n должен существовать. Если поставить
 *       просто n — упадёт ArrayIndexOutOfBoundsException на таком входе.</li>
 *   <li>Индекс ведра — частота, а не число. Числа лежат внутри списка,
 *       который является значением по этому индексу. Не наоборот.</li>
 *   <li>В одном ведре может быть несколько чисел. Например, если 1 и 2
 *       встречаются по 2 раза — оба лежат в buckets[2]. Поэтому внутренний
 *       цикл идёт по всему списку ведра, а не берёт только get(0). Если
 *       бы взяли только первый, потеряли бы часть ответа.</li>
 *   <li>Из одного ведра можно набрать все k. Например, если k = 3 и все
 *       три числа встречаются одинаково часто — они все в одном ведре.
 *       Внутренний цикл добавляет их все, size становится 3, break.</li>
 *   <li>Внешний цикл останавливается через условие result.size() &lt; k.
 *       Как только набрали k, дальше идти не нужно — остальные ведра
 *       содержат числа с меньшей частотой, они уже не войдут в топ-k.</li>
 *   <li>Внутренний break выходит ТОЛЬКО из внутреннего цикла. Сам по себе
 *       он не остановит внешний. Остановка внешнего — через условие
 *       result.size() &lt; k в заголовке. Две проверки работают вместе:
 *       внутренняя ловит момент «набрали k внутри одного ведра», внешняя —
 *       «набрали k, переходя к следующему ведру».</li>
 *   <li>Итерируемся по keySet мапы, а не по массиву. В массиве дубликаты —
 *       строили бы ведро несколько раз для одного числа. В keySet каждое
 *       число уникально, попадает в ведро ровно один раз.</li>
 *   <li>bucketIndex идёт до 1, а не до 0. buckets[0] всегда null — частота
 *       0 невозможна, любое число из массива встречается хотя бы раз.</li>
 *   <li>merge(item, 1, Integer::sum) — компактная замена связке
 *       getOrDefault + put. Если ключа нет — кладёт 1. Если есть —
 *       прибавляет 1 к текущему значению.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>nums = [1,1,1,2,2,3], k = 2 → [1, 2].</li>
 *   <li>nums = [1], k = 1 → [1]. Один элемент.</li>
 *   <li>nums = [1,2], k = 2 → [1, 2]. Оба по разу.</li>
 *   <li>nums = [1,1,1,1], k = 1 → [1]. Все одинаковые, максимальная
 *       частота = n.</li>
 *   <li>nums = [1,2,3], k = 3 → [1, 2, 3]. Все уникальны, все в одном
 *       ведре — из одного ведра набираем все k.</li>
 *   <li>nums = [1,1,2,2,3,3], k = 3 → [1, 2, 3]. Все в одном ведре,
 *       набираем все три из buckets[2].</li>
 *   <li>nums = [4,4,4,4,5,5,5,6,6,7], k = 2 → [4, 5].</li>
 *   <li>nums = [-1,-1,-2,-2,-3], k = 2 → [-1, -2]. Отрицательные.</li>
 *   <li>nums = [0,0,0,1,1], k = 1 → [0]. Нули.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — подсчёт частот O(n), раскладка по ведрам O(m), где m —
 *       число уникальных элементов, сборка ответа O(n). Итого O(n).<br>
 * Память: O(n) — frequencyMap, buckets и result.</p>
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] nums = new int[]{1,1,2,2,3,3};
//        int k = 2;
        int k = 3;

        System.out.println(Arrays.toString(topKFrequentBucketSort(nums, k)));
    }


    public static int[] topKFrequentBucketSort(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int item : nums) {
            frequencyMap.merge(item, 1, Integer::sum);
        }


        List<Integer>[] buckets = new List[nums.length + 1];
        for (int number : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(number);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(number);
        }

        List<Integer> result = new ArrayList<>();
        for (int bucketIndex = buckets.length - 1; bucketIndex > 0 && result.size() < k; bucketIndex--) {
            if (buckets[bucketIndex] != null) {
                for (int value : buckets[bucketIndex]) {
                    result.add(value);
                    if (result.size() == k) {
                        break;
                    }
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
