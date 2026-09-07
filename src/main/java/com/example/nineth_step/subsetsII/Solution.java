package com.example.nineth_step.subsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsetsRecursion(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, new ArrayList<>(), nums, result);
        return result;
    }

    private void backtrack(int start, List<Integer> current, int[] nums, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int index = start; index < nums.length; index++) {
            if (index > start && nums[index] == nums[index - 1]) {
                continue;
            }
            current.add(nums[index]);
            backtrack(index + 1, current, nums, result);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsetsIterative(int[] nums) {
        // Сортируем массив, чтобы одинаковые числа стояли рядом
        Arrays.sort(nums);

        List<List<Integer>> allSubsets = new ArrayList<>();
        allSubsets.add(new ArrayList<>()); // пустое подмножество

        // Размер allSubsets до обработки предыдущего числа
        // Используется для того, чтобы при дубликатах не создавать повторяющиеся подмножества
        int sizeBeforePreviousStep = 0;

        for (int index = 0; index < nums.length; index++) {
            int currentNumber = nums[index];

            // Если текущее число равно предыдущему, начинаем добавлять новые подмножества
            // только с той позиции, с которой были созданы подмножества на предыдущем шаге
            int startAddingFrom = (index > 0 && currentNumber == nums[index - 1])
                    ? sizeBeforePreviousStep
                    : 0;

            // Запоминаем размер allSubsets до добавления новых подмножеств на этом шаге
            int currentSizeBeforeAdding = allSubsets.size();

            // Создаём новые подмножества, добавляя currentNumber к существующим
            for (int subsetIndex = startAddingFrom; subsetIndex < currentSizeBeforeAdding; subsetIndex++) {
                List<Integer> newSubset = new ArrayList<>(allSubsets.get(subsetIndex));
                newSubset.add(currentNumber);
                allSubsets.add(newSubset);
            }

            // Сохраняем размер, который был до добавления новых подмножеств,
            // для использования при следующем повторяющемся числе
            sizeBeforePreviousStep = currentSizeBeforeAdding;
        }

        return allSubsets;
    }
}
