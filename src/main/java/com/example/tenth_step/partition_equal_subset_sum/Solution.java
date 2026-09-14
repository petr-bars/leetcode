package com.example.tenth_step.partition_equal_subset_sum;

public class Solution {
    public boolean canPartition(int[] nums) {
        // Шаг 1: считаем общую сумму
        int totalSum = 0;
        for (int number : nums) {
            totalSum += number;
        }

        // Шаг 2: если сумма нечётная — разбить нельзя
        if (totalSum % 2 != 0) {
            return false;
        }

        // Шаг 3: целевая сумма — половина общей
        int target = totalSum / 2;

        // Шаг 4: массив достижимости сумм
        // reachable[s] == true, если сумму s можно собрать из просмотренных чисел
        boolean[] reachable = new boolean[target + 1];
        reachable[0] = true; // пустое подмножество даёт сумму 0

        // Шаг 5: перебираем все числа
        for (int number : nums) {
            // Идём от target вниз до number (включительно)
            for (int currentSum = target; currentSum >= number; currentSum--) {
                if (reachable[currentSum - number]) {
                    reachable[currentSum] = true;
                }
            }

            // Ранний выход: если target уже достижим — дальше смысла нет
            if (reachable[target]) {
                return true;
            }
        }

        // Шаг 6: проверяем, достижима ли целевая сумма
        return reachable[target];
    }
}
