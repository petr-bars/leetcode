package com.example.eighth_step.course_schedule;

/**
 * here are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        Solution schedule = new Solution();
//        boolean result = schedule.canFinish(numCourses, prerequisites);
        boolean result = schedule.canFinishRecursion(numCourses, prerequisites);
        System.out.println(result);
    }
}
