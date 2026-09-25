package com.example.first_step.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Дан массив интервалов intervals, где intervals[i] = [start_i, end_i].
 * Нужно объединить все пересекающиеся интервалы и вернуть массив непересекающихся интервалов,
 * которые покрывают все исходные.
 * Пример:
 * Вход: intervals = [[1,3], [2,6], [8,10], [15,18]]
 * Выход: [[1,6], [8,10], [15,18]]
 * [1,3] и [2,6] пересекаются → объединяем в [1,6].
 * Паттерн
 * Сортировка по началу + один проход.
 * Ключевая идея:
 * Сортируем отрезки по start. Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
 * Держим «текущий» отрезок — последний в результате.
 * Если current.end >= next.start → пересекаются → расширяем current.end = max(...).
 * Иначе — новый отрезок в результат.
 * Что важно запомнить:
 * Касание (end == start) — это пересечение, поэтому >=, а не >.
 * max нужен, потому что next может быть внутри current.
 * Ссылка currentInterval — тот же объект, что в result.
 * Сложность
 * Время: O(n log n) — из-за сортировки.
 * Память: O(n) — для результата (или O(log n) на сортировку, если не считать результат).
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
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);

        for (int index = 1; index < intervals.length; index++) {
            int[] nextInterval = intervals[index];
            if (currentInterval[1] >= nextInterval[0]) {//Схлопываем интервалы
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                currentInterval = nextInterval;
                result.add(currentInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
