package com.example.second_step.trapping_rain_water;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * <p>
 * Дан массив height длины n, где height[i] — высота столбца рельефа в точке i. Ширина каждого столбца равна 1.
 * После дождя в «ямах» между столбцами скапливается вода. Нужно посчитать,
 * сколько всего единиц воды удержится в рельефе.
 * Формально: для каждой позиции i вода над столбцом стоит до уровня min(maxСлева, maxСправа), где:
 * maxСлева — максимальная высота среди столбцов 0..i;
 * maxСправа — максимальная высота среди столбцов i..n-1.
 * Если height[i] выше или равен этому уровню — воды над столбцом нет.
 * Пример
 * height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Сумма: 1 + 1 + 2 + 1 + 1 = 6.
 * Ответ: 6.
 * Паттерн
 * Два указателя навстречу (тот же паттерн, что в Container With Most Water, но логика другая).
 * Ключевая идея
 * left = 0, right = n - 1. Держим leftMax, rightMax, water = 0.
 * На каждом шаге сравниваем height[left] и height[right]:
 * height[left] < height[right] → справа уже есть стена выше левого. Значит,
 * вода над left ограничена только левой стороной. Обновляем leftMax, прибавляем воду water += leftMax − height[left],
 * двигаем left++.
 * иначе → слева уже есть стена выше правого. Вода над right ограничена только правой стороной.
 * Обновляем rightMax, прибавляем water += rightMax − height[right], двигаем right--.
 * Идём, пока left < right. Возвращаем water.
 * Вода — это максимум_стороны − height[столбик]. Сколько пустоты над столбиком до уровня стены. Копится в water.
 * Почему так. Работаем с более низкой стороной, потому что противоположная заведомо выше и на уровень воды не влияет.
 * сложность
 * Время: O(n)
 * Память: O(1).
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (leftIndex < rightIndex) {
            if (height[leftIndex] < height[rightIndex]) {
                leftMax = Math.max(leftMax, height[leftIndex]);
                water += leftMax - height[leftIndex];
                leftIndex++;
            } else {
                rightMax = Math.max(rightMax, height[rightIndex]);
                water += rightMax - height[rightIndex];
                rightIndex--;
            }
        }
        return water;
    }
}
