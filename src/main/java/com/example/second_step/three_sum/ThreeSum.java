package com.example.second_step.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j,
 * i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * Дан массив nums. Нужно вернуть все уникальные тройки [nums[i], nums[j], nums[k]] такие, что:
 * i != j != k
 * nums[i] + nums[j] + nums[k] = 0
 * В ответе не должно быть дублирующихся троек.
 * Пример:
 * Вход: nums = [-1, 0, 1, 2, -1, -4]
 * Выход: [[-1, -1, 2], [-1, 0, 1]]
 * Паттерн
 * Сортировка + два указателя + пропуск дубликатов.
 * Ключевая идея
 * Отсортируй массив.
 * Идёшь внешним циклом по i от 0 до n-2. nums[i] — первый элемент тройки.
 * Пропускаешь дубликаты: если i > 0 && nums[i] == nums[i-1] → continue.
 * Для каждого i задача сводится к Two Sum II: найти два числа в nums[i+1..n-1], сумма которых = -nums[i].
 * left = i + 1, right = n - 1.
 * Пока left < right:
 * Считаешь sum = nums[i] + nums[left] + nums[right].
 * Если sum == 0:
 * Добавляешь тройку [nums[i], nums[left], nums[right]].
 * Пропускаешь дубликаты у left и right (сдвигаешь, пока одинаковые).
 * Двигаешь оба указателя.
 * Если sum < 0 → left++.
 * Если sum > 0 → right--.
 * Что важно запомнить:
 * Три уровня пропуска дубликатов: i, left, right.
 * while (left < right && nums[left] == nums[left+1]) left++; — пропуск дубликатов слева.
 * while (left < right && nums[right] == nums[right-1]) right--; — справа.
 * После пропуска обязательно left++ и right--, иначе зациклишься.
 * Сложность
 * Время: O(n²) — внешний цикл O(n), внутри два указателя O(n).
 * Память: O(1) (не считая результата).
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};

        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        for (int index = 0; index < nums.length - 2; index++) {
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }

            int left = index + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(List.of(nums[index], nums[left], nums[right]));

                    // Пропускаем дубликаты у left (сдвигаем, пока одинаковые).
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Пропускаем дубликаты у right (сдвигаем, пока одинаковые).
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
