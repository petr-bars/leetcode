package com.example.first_step.insert_interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>You are given an array of non-overlapping intervals intervals where
 * intervals[i] = [start_i, end_i] represent the start and the end of the ith
 * interval and intervals is sorted in ascending order by start_i. You are
 * also given an interval newInterval = [start, end] that represents the start
 * and end of another interval.</p>
 *
 * <p>Two intervals are considered overlapping if they share at least one
 * point. Insert newInterval into intervals such that intervals is still
 * sorted in ascending order by start_i and intervals still does not have
 * any overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.</p>
 *
 * <p>Note that you don't need to modify intervals in-place. You can make
 * a new array and return it.</p>
 *
 * <p>Дан массив непересекающихся интервалов intervals, отсортированных
 * по началу. Дан новый интервал newInterval. Вставить newInterval в массив
 * так, чтобы интервалы по-прежнему не пересекались и были отсортированы.
 * Если новый интервал пересекается с существующими — объединить.</p>
 *
 * <p>Пример:<br>
 * Вход:  intervals = [[1,3],[6,9]], newInterval = [2,5]<br>
 * Выход: [[1,5],[6,9]]<br>
 * Пояснение: [2,5] пересекается с [1,3] → объединяем в [1,5].</p>
 *
 * <p>Пример:<br>
 * Вход:  intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]],
 * newInterval = [4,8]<br>
 * Выход: [[1,2],[3,10],[12,16]]<br>
 * Пояснение: [4,8] пересекается с [3,5],[6,7],[8,10] → объединяем
 * в [3,10].</p>
 *
 * <p>Паттерн:<br>
 * Три фазы в одном проходе.</p>
 *
 * <p>Именование переменных:<br>
 * intervals — исходный массив отсортированных непересекающихся интервалов.
 * newInterval — вставляемый интервал, который может расширяться при слиянии.
 * result — список итоговых интервалов. index — текущий индекс в цикле
 * по intervals. currentInterval — текущий интервал из intervals
 * (обращение через intervals[index]).</p>
 *
 * <p>Идея:<br>
 * Массив уже отсортирован — сортировка не нужна, в отличие от Merge
 * Intervals. Значит, можно за один проход. Логика разбивается на три фазы
 * в зависимости от того, как текущий интервал соотносится с newInterval:
 * целиком слева, пересекается, целиком справа. Первая фаза — копирование,
 * вторая — слияние, третья — выход из цикла. После цикла newInterval
 * добавляется один раз, потом докопируется остаток массива.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Создаём пустой список result.</li>
 *   <li>Идём по intervals с индексом index от 0:
 *       <ul>
 *         <li>Фаза 1 — интервал полностью слева от newInterval
 *             (intervals[index][1] &lt; newInterval[0]):
 *             <ul>
 *               <li>Добавляем intervals[index] в result.</li>
 *               <li>index++.</li>
 *             </ul>
 *         </li>
 *         <li>Фаза 2 — интервал пересекается с newInterval
 *             (intervals[index][0] &lt;= newInterval[1]):
 *             <ul>
 *               <li>Расширяем newInterval: начало = min(newInterval[0],
 *                   intervals[index][0]), конец = max(newInterval[1],
 *                   intervals[index][1]).</li>
 *               <li>В result пока не добавляем — newInterval может
 *                   расшириться ещё.</li>
 *               <li>index++.</li>
 *             </ul>
 *         </li>
 *         <li>Фаза 3 — интервал полностью справа от newInterval
 *             (иначе):
 *             <ul>
 *               <li>Пересечений больше не будет. Прерываем цикл через break.</li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>После цикла:
 *       <ul>
 *         <li>Добавляем newInterval в result. Это отдельный шаг — newInterval
 *             не был добавлен внутри цикла ни в одной из фаз.</li>
 *         <li>Докопируем оставшиеся интервалы с текущего index до конца:
 *             они все правее newInterval и не пересекаются с ним.</li>
 *       </ul>
 *   </li>
 *   <li>Возвращаем result.toArray(new int[result.size()][]).</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Три фазы — взаимоисключающие, поэтому используется else if
 *       цепочка, а не три отдельных if. Если написать три независимых if,
 *       после первой фазы index увеличится, и вторая фаза проверит уже
 *       следующий интервал — логика сломается, а на выходе за границу
 *       массива будет ArrayIndexOutOfBoundsException.</li>
 *   <li>Порядок проверок важен. Сначала «слева», потом «пересекается»,
 *       потом «справа». Это естественный порядок вдоль отсортированного
 *       массива.</li>
 *   <li>newInterval расширяется через min/max при каждом слиянии.
 *       Вложенные интервалы обрабатываются корректно: если текущий
 *       интервал внутри newInterval, min и max не изменят newInterval,
 *       но он всё равно поглотит вложенный.</li>
 *   <li>newInterval добавляется в result только ОДИН раз — после цикла.
 *       Внутри цикла в фазе 2 мы его не добавляем, потому что он может
 *       ещё расшириться на следующей итерации.</li>
 *   <li>Фаза 3 — break, а не return. Потому что после break ещё нужно
 *       добавить newInterval и докопировать остаток.</li>
 *   <li>Границы включительны: intervals[index][1] &lt; newInterval[0] —
 *       строго меньше, потому что касание (равенство) считается
 *       пересечением. То же для intervals[index][0] &lt;= newInterval[1].</li>
 *   <li>Сортировка не нужна, потому что intervals уже отсортирован
 *       по условию. Это главное отличие от Merge Intervals, где сортировка
 *       давала O(n log n). Здесь O(n).</li>
 *   <li>Формула проверки пересечения двух интервалов:
 *       intervals[index][1] &gt;= newInterval[0] И
 *       intervals[index][0] &lt;= newInterval[1]. В коде мы не проверяем
 *       оба условия одновременно — фаза 1 уже отсекла случаи, когда
 *       intervals[index][1] &lt; newInterval[0]. Значит, для оставшихся
 *       автоматически intervals[index][1] &gt;= newInterval[0],
 *       и достаточно проверить intervals[index][0] &lt;= newInterval[1].</li>
 *   <li>Пустой intervals — цикл не выполнится, newInterval добавится
 *       в result, докопирование ничего не сделает. Вернётся [newInterval].</li>
 *   <li>newInterval слева от всех — фаза 1 не сработает, фаза 2 не сработает
 *       (newInterval[1] меньше первого начала), сработает фаза 3 — break.
 *       newInterval добавится, потом докопируется весь массив.</li>
 *   <li>newInterval справа от всех — все интервалы уйдут в фазу 1, цикл
 *       закончится, newInterval добавится в конце.</li>
 *   <li>newInterval внутри одного интервала — фаза 2 сработает один раз,
 *       min/max не изменят newInterval, но он поглотит этот интервал.
 *       Дальше либо break, либо новый интервал.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>intervals = [[1,3],[6,9]], newInterval = [2,5] → [[1,5],[6,9]].</li>
 *   <li>intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]],
 *       newInterval = [4,8] → [[1,2],[3,10],[12,16]].</li>
 *   <li>intervals = [], newInterval = [5,7] → [[5,7]].</li>
 *   <li>intervals = [[1,5]], newInterval = [2,3] → [[1,5]].
 *       newInterval внутри существующего.</li>
 *   <li>intervals = [[1,5]], newInterval = [6,8] → [[1,5],[6,8]].
 *       newInterval справа.</li>
 *   <li>intervals = [[1,5]], newInterval = [0,3] → [[0,5]].
 *       newInterval пересекается с началом.</li>
 *   <li>intervals = [[1,5]], newInterval = [-2,0] → [[-2,0],[1,5]].
 *       newInterval слева.</li>
 *   <li>intervals = [[1,5]], newInterval = [5,8] → [[1,8]].
 *       Касание границ — пересечение.</li>
 *   <li>intervals = [[1,5]], newInterval = [6,6] → [[1,5],[6,6]].
 *       Одиночная точка справа.</li>
 *   <li>intervals = [[1,2],[3,4],[5,6]], newInterval = [0,10] →
 *       [[0,10]]. Поглощает всё.</li>
 *   <li>отрицательные значения:
 *       intervals = [[-5,-1],[0,3]], newInterval = [-3,1] → [[-5,3]].</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — один проход по массиву. Сортировка не нужна, потому что
 *       intervals уже отсортирован.<br>
 * Память: O(n) — для списка result.</p>
 */
public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[] newInterval = new int[]{2, 5};

        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }

        List<int[]> result = new ArrayList<>();
        int index = 0;
        int length = intervals.length;

        while (index < length) {
            //Фаза 1 проверка стоит ли интервал левее от нового
            if (intervals[index][1] < newInterval[0]) {
                result.add(intervals[index]);
                index++;
            }
            // Фаза 2 проверяем пересекаются ли интервалы если да то сливаем (расширяем без добавления в результат).
            else if (intervals[index][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
                newInterval[1] = Math.max(intervals[index][1], newInterval[1]);
                index++;
            }
            // Прерываем т.к сделали все что могли
            else {
                break;
            }
        }

        // Добавляем получившийся новый интервал после всех возможных до него.
        result.add(newInterval);

        // Фаза 3 теперь копируем все с позиции index т.е все что не прошло обработку до момента break.
        while (index < length) {
            result.add(intervals[index++]);
        }

        return result.toArray(new int[result.size()][]);
    }
}
