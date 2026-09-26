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
 * Проверка [1,8,6,2,5,4,8,3,7]
 * L=0 (1), R=8 (7), min=1, area=8. Левый while: 1<=1 → L=1 (8, стоп). Правый while: 7<=1? нет.
 * L=1 (8), R=8 (7), min=7, area=49. Левый: 8<=7? нет. Правый: 7<=7 → R=7 (3), 3<=7 → R=6 (8, стоп).
 * L=1 (8), R=6 (8), min=8, area=40. Левый: 8<=8 → L=2 (6), 6<=8 → L=3 (2), 2<=8 → L=4 (5), 5<=8 → L=5 (4), 4<=8 → L=6. L=R=6 → стоп.
 * Return 49 ✓
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
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int width = right - left;
            int currentArea = minHeight * width;
            maxArea = Math.max(maxArea, currentArea);

            /*Сдвигаемся не на один шаг как в классическом решении, а пропускаем сразу группами.
             * Аналогичным способом отрабатывали поиск дубликатов в задаче 3Sum
             * тем самым избегаем лишних шагов*/
            while (left < right && height[left] <= minHeight) {
                left++;
            }
            while (left < right && height[right] <= minHeight) {
                right--;
            }
        }

        return maxArea;
    }
}
