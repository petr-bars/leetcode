package com.example.eighth_step.min_cost_to_tonnect_all_points;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 * <p>
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
 * where |val| denotes the absolute value of val.
 * <p>
 * Return the minimum cost to make all points connected.
 * All points are connected if there is exactly one simple path between any two points.
 */
public class MinCostToConnectAllPoints {
    public static void main(String[] args) {
        int[][] points = {{3, 12}, {-2, 5}, {-4, 1}};
        Solution solution = new Solution();
        int result = solution.minCostConnectPoints(points);
        System.out.println(result);
    }
}
