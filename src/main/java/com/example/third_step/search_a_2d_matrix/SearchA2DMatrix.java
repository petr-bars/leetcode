package com.example.third_step.search_a_2d_matrix;

/**
 * <p>You are given an m x n integer matrix matrix with the following two properties:
 * each row is sorted in non-decreasing order, and the first integer of each row
 * is greater than the last integer of the previous row.</p>
 *
 * <p>Given an integer target, return true if target is in matrix or false otherwise.
 * You must write a solution in O(log(m * n)) time complexity.</p>
 *
 * <p>Дана матрица matrix размера m x n со свойствами: каждая строка отсортирована
 * по возрастанию, и первый элемент каждой строки больше последнего элемента
 * предыдущей строки. Дано число target. Вернуть true, если target есть в матрице,
 * иначе false. Решение должно работать за O(log(m * n)).</p>
 *
 * <p>Пример:<br>
 * Вход:  matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3<br>
 * Выход: true</p>
 *
 * <p>Пример:<br>
 * Вход:  matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13<br>
 * Выход: false</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск по виртуальному одномерному массиву.</p>
 *
 * <p>Идея:<br>
 * Матрица обладает свойством: если читать её слева направо, сверху вниз —
 * получается один отсортированный массив. Первый элемент каждой строки больше
 * последнего элемента предыдущей, значит строки не пересекаются и идут по
 * возрастанию. Поэтому можно мысленно «выпрямить» матрицу в одномерный массив
 * длиной m * n и применить обычный бинарный поиск. Только не создавать реальный
 * массив, а виртуально пересчитывать индекс в координаты строки и столбца.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Инициализация. Виртуальные границы: left = 0, right = m * n - 1.
 *       Отрезок покрывает все элементы матрицы.</li>
 *   <li>Пока отрезок не пуст (left не перешла за right):
 *       <ul>
 *         <li>Берём середину. mid = left + (right - left) / 2.</li>
 *         <li>Переводим mid в координаты: строка = mid / n, столбец = mid % n.
 *             Делим на число столбцов, потому что каждая строка содержит ровно
 *             n элементов.</li>
 *         <li>Сравниваем значение matrix[строка][столбец] с target:
 *             <ul>
 *               <li>равно → нашли, вернуть true.</li>
 *               <li>меньше → target правее, отбрасываем левую половину:
 *                   left = mid + 1.</li>
 *               <li>больше → target левее, отбрасываем правую половину:
 *                   right = mid - 1.</li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>Если цикл закончился — target не найден, вернуть false.</li>
 * </ul>
 *
 * <p>Ключевая тонкость (перевод индекса в координаты):<br>
 * Виртуальный индекс mid в одномерном массиве соответствует строке mid / n
 * и столбцу mid % n. Число n — количество столбцов. Не наоборот: делим на
 * количество столбцов, а не строк. Это потому, что одномерный массив
 * «нарезан» построчно: каждые n подряд идущих элементов — одна строка.</p>
 *
 * <p>Ключевая тонкость (границы):<br>
 * Середину проверяем и исключаем: left = mid + 1, right = mid - 1.
 * Условие цикла — left &lt;= right. Иначе потеряешь последний элемент.</p>
 *
 * <p>Типичные ошибки:</p>
 * <ul>
 *   <li>Делить mid на количество строк вместо столбцов. Делитель — число
 *       столбцов n, потому что строки «нарезаны» по n элементов.</li>
 *   <li>Создавать реальный одномерный массив. Это O(m * n) памяти и
 *       противоречит требованию O(log(m * n)). Матрица используется виртуально.</li>
 *   <li>Сравнивать mid с target вместо значения matrix[строка][столбец].</li>
 *   <li>Сдвигать границы на один шаг, а не за середину. Это линейный поиск,
 *       а не бинарный.</li>
 *   <li>Забыть проверить пустую матрицу (m = 0 или n = 0).</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>target в середине матрицы.</li>
 *   <li>target в первом элементе.</li>
 *   <li>target в последнем элементе.</li>
 *   <li>target меньше всех.</li>
 *   <li>target больше всех.</li>
 *   <li>target отсутствует, но между элементами.</li>
 *   <li>матрица из одной строки.</li>
 *   <li>матрица из одного столбца.</li>
 *   <li>матрица 1x1, target совпадает.</li>
 *   <li>матрица 1x1, target не совпадает.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(log(m * n)) — бинарный поиск по виртуальному одномерному массиву.<br>
 * Память: O(1) — только границы и середина.</p>
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

        int rows = matrix.length;
        int cols = matrix[0].length;
        int total = rows * cols;
        int left = 0;
        int right = total - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
