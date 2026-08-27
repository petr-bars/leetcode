package com.example.seventh_step.task_scheduler;

/**
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n.
 * Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order,
 * but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.
 * <p>
 * Return the minimum number of CPU intervals required to complete all tasks.
 */
public class TaskScheduler {
    public static void main(String[] args) {
        Solution task = new Solution();
        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        System.out.println(task.leastInterval(tasks, n));
    }
}
