package com.example.third_step.search_a_2d_matrix;

/**
 * <p>You are given an m x n integer matrix matrix with the following two
 * properties: each row is sorted in non-decreasing order, and the first
 * integer of each row is greater than the last integer of the previous row.</p>
 *
 * <p>Given an integer target, return true if target is in matrix or false
 * otherwise. You must write a solution in O(log(m * n)) time complexity.</p>
 *
 * <p>Дана матрица matrix размера m x n: каждая строка отсортирована,
 * и первый элемент каждой строки больше последнего элемента предыдущей.
 * Вернуть true, если target есть в матрице, иначе false. Время — O(log(m·n)).</p>
 *
 * <p>Пример:<br>
 * Вход:  matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3<br>
 * Выход: true</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск по виртуальному одномерному массиву.</p>
 *
 * <p>Идея:<br>
 * Матрица устроена так, что если читать её слева направо, сверху вниз —
 * получится один отсортированный массив. Значит, можно мысленно «выпрямить»
 * её в одномерный массив длиной m·n и применить обычный бинарный поиск.
 * Реальный массив не создаём — пересчитываем индекс в координаты строки
 * и столбца.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>left = 0, right = m · n − 1.</li>
 *   <li>mid = left + (right − left) / 2.</li>
 *   <li>строка = mid / n, столбец = mid % n.</li>
 *   <li>если matrix[строка][столбец] == target → вернуть true;</li>
 *   <li>если меньше target → сдвинуть левую границу за середину;</li>
 *   <li>иначе → сдвинуть правую границу перед серединой.</li>
 *   <li>Повторять, пока левая не перешла за правую. Вернуть false.</li>
 * </ul>
 *
 * <p>Почему делим на n, а не на m:<br>
 * Виртуальный массив — это строки матрицы, склеенные в одну линию.
 * Длина каждой строки = число столбцов n. Чтобы узнать, в какой строке
 * лежит mid, делим mid на длину строки, то есть на n.</p>
 *
 * <p>Сложность:<br>
 * Время: O(log(m · n)).<br>
 * Память: O(1).</p>
 */
public class SearchA2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]
                {
                        {1, 3, 5, 7},
                        {10, 11, 16, 20},
                        {23, 30, 34, 60}
                };
        int target = 3;

        System.out.println(searchMatrix(matrix, target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int left = 0;
        int right = rowLength * colLength - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int rowIndex = mid / colLength;
            int colIndex = mid % colLength;
            if (matrix[rowIndex][colIndex] == target) {
                return true;
            } else if (matrix[rowIndex][colIndex] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
