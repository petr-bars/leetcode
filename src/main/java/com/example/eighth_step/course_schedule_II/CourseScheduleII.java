package com.example.eighth_step.course_schedule_II;

import java.util.Arrays;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 * take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
 * return any of them. If it is impossible to finish all courses, return an empty array.
 */
public class CourseScheduleII {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        Solution schedule = new Solution();
//        int[] result = schedule.findOrder(numCourses,prerequisites);
        int[] result = schedule.findOrderRecursion(numCourses,prerequisites);
        System.out.println(Arrays.toString(result));
    }
}
