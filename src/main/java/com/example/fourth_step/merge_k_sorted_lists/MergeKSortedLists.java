package com.example.fourth_step.merge_k_sorted_lists;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <p>You are given an array of k linked-lists lists, each linked-list is sorted
 * in ascending order. Merge all the linked-lists into one sorted linked-list
 * and return it.</p>
 *
 * <p>Дан массив из k отсортированных связных списков. Слить их в один
 * отсортированный список. Вернуть его голову.</p>
 *
 * <p>Паттерн:<br>
 * PriorityQueue (min-heap) + dummy node.</p>
 *
 * <p>Идея:<br>
 * Кладём голову каждого списка в min-heap. На каждом шаге достаём
 * минимальный узел, прицепляем к результату, и если у него есть next —
 * кладём next в heap. Dummy — заглушка в начале, чтобы не возиться
 * с первым узлом.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>dummy = новый узел, tail = dummy.</li>
 *   <li>Положить в heap голову каждого непустого списка.</li>
 *   <li>Пока heap не пуст: достать минимум, прицепить к tail, сдвинуть
 *       tail, положить его next в heap.</li>
 *   <li>Вернуть dummy.next.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(N log k).<br>
 * Память: O(k).</p>
 */
public class MergeKSortedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode[] listNodes = createLists(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}, {7, 8}});

        System.out.println(listToString(mergeKListsPriorityQueue(listNodes)));
    }


    public static ListNode mergeKListsPriorityQueue(ListNode[] lists) {
        Queue<ListNode> heap = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.val));

        for (ListNode head : lists) {
            if (head != null) {
                heap.add(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                heap.add(node.next);
            }
        }
        return dummy.next;
    }

    public static ListNode[] createLists(int[][] arrays) {
        if (arrays == null) return null; // защита
        ListNode[] heads = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            heads[i] = createList(arrays[i]); // используем существующий метод
        }
        return heads;
    }

    public static ListNode createList(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(", ");
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
