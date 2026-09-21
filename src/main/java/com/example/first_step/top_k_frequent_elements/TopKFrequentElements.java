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
 * Считаем частоты через HashMap.merge.
 * Создаём buckets[f] = список чисел с частотой f. Размер n + 1.
 * Идём с конца (от высокой частоты к низкой), собираем k чисел.
 * Что важно запомнить:
 * Частота не может быть больше n, поэтому массив размера n + 1.
 * merge(num, 1, Integer::sum) — элегантный подсчёт частот.
 * Время: O(n),
 * Память: O(n).
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;

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

        List<Integer> list = new ArrayList<>();
        for (int bucketIndex = buckets.length - 1; bucketIndex >= 0 && list.size() < k; bucketIndex--) {
            if (buckets[bucketIndex] != null) {
                for (int value : buckets[bucketIndex]) {
                    list.add(value);
                    if (list.size() == k) {
                        break;
                    }
                }
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
