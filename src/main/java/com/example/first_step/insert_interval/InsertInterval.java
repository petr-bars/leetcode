package com.example.first_step.insert_interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent
 * the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Two intervals are considered overlapping if they share at least one point.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals
 * still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 * <p>
 * Дан массив непересекающихся интервалов intervals, отсортированных по началу, и новый интервал newInterval.
 * Нужно вставить newInterval в массив так, чтобы интервалы по-прежнему не пересекались и были отсортированы.
 * Если новый интервал пересекается с какими-то существующими — их нужно объединить.
 * Пример:
 * Вход: intervals = [[1,3], [6,9]], newInterval = [2,5]
 * Выход: [[1,5], [6,9]]
 * [2,5] пересекается с [1,3] → объединяем в [1,5].
 * Заметка по паттерну
 * Паттерн: три фазы в одном проходе.
 * Ключевая идея:
 * Фаза 1: интервал полностью слева (interval.end < newInterval.start) → копируем.
 * Фаза 2: пересекается (interval.start <= new.end) → расширяем new через min/max, не копируем.
 * Фаза 3: интервал справа → break.
 * После цикла: добавить newInterval, потом докопировать остаток.
 * Что важно запомнить:
 * Работает за O(n), потому что массив уже отсортирован — сортировка не нужна (в отличие от Merge Intervals).
 * Не забыть добавить newInterval после цикла — это отдельный шаг.
 * Три фазы покрывают все случаи: слева, пересечение, справа.
 * Формула проверки пересечения
 * interval.end >= newInterval.start && interval.start <= newInterval.end
 * Расширение
 * newInterval.start = min(newInterval.start, interval.start)
 * newInterval.end = max(newInterval.end, interval.end)
 * вернуть result.toArray(new int[result.size()][]);
 * Сложность
 * Время: O(n) — один проход, в отличие от Merge Intervals, где была сортировка O(n log n).
 * Память: O(n) — для результата.
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
            result.add(intervals[index]);
            index++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
