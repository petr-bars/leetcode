package com.example.first_step.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <p>Given an array of intervals where intervals[i] = [start_i, end_i], merge
 * all overlapping intervals, and return an array of the non-overlapping
 * intervals that cover all the intervals in the input.</p>
 *
 * <p>Дан массив интервалов intervals, где intervals[i] = [start_i, end_i].
 * Объединить все пересекающиеся интервалы и вернуть массив непересекающихся
 * интервалов, покрывающих все исходные.</p>
 *
 * <p>Пример:<br>
 * Вход:  intervals = [[1,3],[2,6],[8,10],[15,18]]<br>
 * Выход: [[1,6],[8,10],[15,18]]<br>
 * Пояснение: [1,3] и [2,6] пересекаются → [1,6].</p>
 *
 * <p>Пример:<br>
 * Вход:  intervals = [[1,4],[4,5]]<br>
 * Выход: [[1,5]]<br>
 * Пояснение: границы касаются — считаются пересекающимися.</p>
 *
 * <p>Паттерн:<br>
 * Сортировка + линейный проход.</p>
 *
 * <p>Именование переменных:<br>
 * intervals — массив интервалов. currentInterval — текущий интервал,
 * который может расширяться при слиянии. Ссылка на массив, лежащий
 * в result. result — список итоговых интервалов. index — индекс в цикле
 * по intervals. Comparator.comparingInt(a -&gt; a[0]) — сортировка по началу
 * интервала.</p>
 *
 * <p>Идея:<br>
 * Ключевое: сначала отсортировать интервалы по началу. Тогда все
 * пересекающиеся окажутся рядом. Дальше идём слева направо, держим
 * текущий интервал. Для каждого следующего: если его начало не больше
 * конца текущего — они пересекаются, расширяем текущий (конец = max
 * из двух концов). Иначе — не пересекаются, фиксируем текущий и начинаем
 * новый.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Сортировка:
 *       <ul>
 *         <li>Arrays.sort(intervals, Comparator.comparingInt(a -&gt; a[0])).
 *             Сортируем по первому элементу каждого интервала (началу).</li>
 *         <li>После сортировки пересекающиеся интервалы идут подряд.</li>
 *       </ul>
 *   </li>
 *   <li>Инициализация:
 *       <ul>
 *         <li>currentInterval = intervals[0]. Берём первый интервал как
 *             текущий.</li>
 *         <li>result = new ArrayList&lt;&gt;(). Список для ответа.</li>
 *         <li>result.add(currentInterval). Первый интервал уже в списке.
 *             В result лежит ссылка на тот же массив, что и currentInterval.</li>
 *       </ul>
 *   </li>
 *   <li>Проход по остальным интервалам:
 *       <ul>
 *         <li>index от 1 до intervals.length - 1.</li>
 *         <li>Если currentInterval[1] &gt;= intervals[index][0] — пересечение:
 *             <ul>
 *               <li>currentInterval[1] = max(currentInterval[1],
 *                   intervals[index][1]). Расширяем конец текущего интервала.</li>
 *               <li>Дополнительный add не нужен — мутация видна в result,
 *                   потому что там ссылка на тот же массив.</li>
 *             </ul>
 *         </li>
 *         <li>Иначе — не пересекаются:
 *             <ul>
 *               <li>currentInterval = intervals[index]. Начинаем новый
 *                   текущий интервал.</li>
 *               <li>result.add(currentInterval). Добавляем его в список.</li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>Возвращаем result.toArray(new int[result.size()][]). Преобразуем список
 *       в двумерный массив.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Сортировка обязательна. Без неё пересекающиеся интервалы могут
 *       идти вразнобой, и линейный проход не сработает.</li>
 *   <li>Условие пересечения — currentInterval[1] &gt;= intervals[index][0],
 *       а не &gt;. Касание границ ([1,4] и [4,5]) считается пересечением
 *       по условию задачи.</li>
 *   <li>Math.max при расширении нужен. Новый интервал может целиком
 *       лежать внутри текущего. Пример: [[1,10],[2,3]] → [1,10], а не
 *       [1,3]. Без max получишь неправильный результат.</li>
 *   <li>currentInterval — ссылка на массив в result. Когда мутируешь
 *       currentInterval[1], изменение видно и в result. Повторный add
 *       не нужен — он создал бы дубликат (две ссылки на один массив).</li>
 *   <li>Comparator.comparingInt безопаснее, чем (a, b) -&gt; a[0] - b[0].
 *       Второй может дать overflow на больших значениях. Первый
 *       использует Integer.compare без вычитания.</li>
 *   <li>result.toArray(new int[0][]) — в Java 11+ JVM сама создаёт
 *       массив нужного размера. Нулевой размер — это подсказка типа,
 *       а не фактический размер.</li>
 *   <li>Если бы intervals содержал отрицательные числа — всё равно
 *       работает. Сортировка и max не зависят от знака.</li>
 *   <li>Пустой intervals — упадёт на intervals[0]. По условиям LeetCode
 *       n &gt;= 1. Если хочешь надёжности — добавь проверку в начале.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>intervals = [[1,3],[2,6],[8,10],[15,18]] → [[1,6],[8,10],[15,18]].</li>
 *   <li>intervals = [[1,4],[4,5]] → [[1,5]]. Касание границ.</li>
 *   <li>intervals = [[1,4],[2,3]] → [[1,4]]. Вложенный интервал.</li>
 *   <li>intervals = [[1,4],[5,7],[6,8]] → [[1,4],[5,8]]. Слияние не
 *       первого пересечения, а второго.</li>
 *   <li>intervals = [[1,2]] → [[1,2]]. Один интервал.</li>
 *   <li>intervals = [[1,10],[2,3],[4,5],[6,7]] → [[1,10]]. Все внутри
 *       первого.</li>
 *   <li>intervals = [[1,2],[3,4],[5,6]] → без слияний.</li>
 *   <li>интервалы с одинаковыми началами: [[1,4],[1,5]] → [[1,5]].</li>
 *   <li>интервалы с одинаковыми концами: [[1,5],[2,5]] → [[1,5]].</li>
 *   <li>отрицательные значения: [[-5,-1],[-3,2]] → [[-5,2]].</li>
 *   <li>все интервалы пересекаются: [[1,5],[2,6],[3,7]] → [[1,7]].</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n log n) — сортировка O(n log n), линейный проход O(n).
 *       Итого определяется сортировкой.<br>
 * Память: O(n) — result хранит до n интервалов. Сортировка может требовать
 *         O(log n) стека для рекурсии, но это меньше O(n).</p>
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {
                {2, 6},
                {1, 3},
                {8, 10},
                {15, 18}
        };

        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] currentInterval = intervals[0];

        List<int[]> result = new ArrayList<>();
        result.add(currentInterval);
        for (int index = 1; index < intervals.length; index++) {
            if (currentInterval[1] >= intervals[index][0]) {
                currentInterval[1] = Math.max(currentInterval[1], intervals[index][1]);
            } else {
                currentInterval = intervals[index];
                result.add(currentInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
