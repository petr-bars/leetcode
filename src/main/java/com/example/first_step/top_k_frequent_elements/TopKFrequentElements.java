package com.example.first_step.top_k_frequent_elements;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * <p>
 * Дан целочисленный массив nums и целое число k.
 * Нужно вернуть k самых часто встречающихся элементов массива. Порядок — любой.
 * Пример:
 * Вход: nums = [1,1,1,2,2,3], k = 2
 * Выход: [1, 2]
 * Потому что 1 встречается 3 раза, 2 — 2 раза, 3 — 1 раз. Топ-2 по частоте: 1 и 2.
 * <p>
 * Паттерн: Bucket sort по частотам.
 * Ключевая идея:
 * Считаем частоты через HashMap → merge(num, 1, Integer::sum).
 * Создаём buckets длиной n + 1: индекс = частота, значение = список чисел с этой частотой.
 * Идём с конца (от высокой частоты к низкой), собираем k чисел.
 * В одном ведре может быть несколько чисел — если они встречаются одинаковое количество раз.
 * Поэтому внутренний цикл идёт по всему списку ведра, а не берёт только первый элемент.
 * Из одного ведра можно набрать все k — например, если все числа встречаются одинаково часто.
 * Внешний цикл останавливается, когда result.size() == k.
 * Внутренний цикл прерывается через break, когда k набран внутри одного ведра.
 * Что не перепутать:
 * Размер ведра — n + 1, потому что максимальная частота — n.
 * Индекс ведра — частота, а не число.
 * break выходит только из внутреннего цикла. Остановка внешнего — через условие result.size() < k.
 * Время: O(n),
 * Память: O(n).
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int[] nums = new int[]{1,1,2,2,3,3};
        int k = 2;
//        int k = 3;

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
