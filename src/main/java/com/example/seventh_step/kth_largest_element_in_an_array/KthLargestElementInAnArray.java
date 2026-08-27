package com.example.seventh_step.kth_largest_element_in_an_array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Can you solve it without sorting?
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargestQuickSelectIterative(nums, k));
        System.out.println(findKthLargestMinHeap(nums, k));
    }

    public static int findKthLargestQuickSelectIterative(int[] nums, int k) {
        int targetIndex = nums.length - k;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            } else if (pivotIndex > targetIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
        return nums[left];
    }

    private static int partition(int[] nums, int left, int right) {
        // Берём опорным последний элемент в текущем диапазоне
        int pivot = nums[right];
        // Индекс, куда будем ставить элементы меньше опорного
        int i = left;

        // Проходим по диапазону от left до right-1 (не включая опорный)
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                // Меняем местами nums[i] и nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++; // сдвигаем границу "меньших"
            }
        }

        // Ставим опорный элемент на его место (в позицию i)
        int temp = nums[i];
        nums[i] = nums[right];
        nums[right] = temp;

        // Возвращаем индекс опорного элемента
        return i;
    }

    public static int findKthLargestMinHeap(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }
}
