package com.example.second_step.container_with_most_water;

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * <p>
 * Дан массив height длины n, где height[i] — высота вертикальной линии на позиции i.
 * Нужно найти две линии, которые вместе с осью X образуют контейнер, вмещающий максимальное количество воды.
 * Объём воды = min(height[left], height[right]) × (right - left).
 * Пример:
 * Вход: height = [1,8,6,2,5,4,8,3,7]
 * Выход: 49
 * Максимум — между линиями на позициях 1 (высота 8) и 8 (высота 7).
 * Объём: min(8,7) × (8-1) = 7 × 7 = 49.
 * Паттерн
 * Два указателя навстречу + жадное движение.
 * Ключевая идея
 * Начинаем с самой широкой позиции: left = 0, right = n - 1.
 * Считаем объём. Затем сдвигаем тот указатель, у которого высота меньше. Почему?
 * Ширина контейнера всегда уменьшается при сдвиге (расстояние между указателями падает).
 * Единственный способ увеличить объём — увеличить высоту контейнера.
 * Высота ограничена меньшей из двух линий.
 * Если сдвинуть большую — ничего не изменится (меньшая так и останется ограничителем).
 * Если сдвинуть меньшую — есть шанс найти более высокую линию.
 * Алгоритм
 * left = 0, right = n - 1, maxArea = 0.
 * Пока left < right:
 * height = min(height[left], height[right])
 * width = right - left
 * area = height × width
 * maxArea = max(maxArea, area)
 * Если height[left] < height[right] → left++, иначе → right--.
 * Вернуть maxArea.
 * Сложность
 * Время: O(n) — каждый указатель двигается не более n раз.
 * Память: O(1).
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int maxArea = 0;

        while (leftIndex < rightIndex) {
            int minHeight = Math.min(height[leftIndex], height[rightIndex]);
            int width = rightIndex - leftIndex;
            int currentArea = minHeight * width;
            maxArea = Math.max(maxArea, currentArea);

            /*Сдвигаемся не на один шаг как в классическом решении, а пропускаем сразу группами.
             * Аналогичным способом отрабатывали поиск дубликатов в задаче 3Sum
             * тем самым избегаем лишних шагов*/
            while (leftIndex < rightIndex && height[leftIndex] <= minHeight) {
                leftIndex++;
            }
            while (leftIndex < rightIndex && height[rightIndex] <= minHeight) {
                rightIndex--;
            }
        }

        return maxArea;
    }
}
