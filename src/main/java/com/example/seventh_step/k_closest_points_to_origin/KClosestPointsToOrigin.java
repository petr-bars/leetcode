package com.example.seventh_step.k_closest_points_to_origin;

import java.util.Arrays;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 * <p>
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * <p>
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        Solution points = new Solution();
        int[][] result = points.kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2);
        System.out.println(Arrays.deepToString(result));
    }
}
