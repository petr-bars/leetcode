package com.example.seventh_step.kth_largest_element_in_a_stream;

/**
 * You are part of a university admissions office and need to keep track of the kth highest test score from applicants
 * in real-time.
 * This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.
 * <p>
 * You are tasked to implement a class which, for a given integer k,
 * maintains a stream of test scores and continuously returns the kth highest test score after a new score
 * has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.
 * <p>
 * Implement the KthLargest class:
 * <p>
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
 * int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest element
 * in the pool of test scores so far.
 */
public class KthLargestElementInAStream {
    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        kthLargest.add(3); // return 4
        kthLargest.add(5); // return 5
        int param_1 = kthLargest.add(10); // return 5
        kthLargest.add(9); // return 8
        kthLargest.add(4); // return 8
        System.out.println(param_1);
    }
}
