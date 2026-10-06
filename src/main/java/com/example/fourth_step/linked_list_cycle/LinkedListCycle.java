package com.example.fourth_step.linked_list_cycle;

/**
 * <p>Given head, the head of a linked list, determine if the linked list has
 * a cycle in it.</p>
 *
 * <p>There is a cycle in a linked list if there is some node in the list that
 * can be reached again by continuously following the next pointer. Internally,
 * pos is used to denote the index of the node that tail's next pointer is
 * connected to. Note that pos is not passed as a parameter.</p>
 *
 * <p>Return true if there is a cycle in the linked list. Otherwise, return
 * false.</p>
 *
 * <p>Дан головной узел связного списка. Определить, есть ли в нём цикл.
 * Цикл — это когда, идя по ссылкам next, можно вернуться в уже посещённый
 * узел. Вернуть true, если цикл есть, иначе false.</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [3,2,0,-4], pos = 1<br>
 * Выход: true<br>
 * Пояснение: хвост ссылается на узел с индексом 1, образуя цикл.</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [1,2], pos = -1<br>
 * Выход: false<br>
 * Пояснение: цикла нет.</p>
 *
 * <p>Паттерн:<br>
 * Fast &amp; slow pointers (алгоритм черепахи и зайца).</p>
 *
 * <p>Идея:<br>
 * Два указателя идут по списку с разной скоростью: slow — на 1 узел,
 * fast — на 2. Если цикла нет, fast дойдёт до конца (null) и остановится.
 * Если цикл есть, fast рано или поздно догонит slow внутри цикла, потому
 * что каждый шаг сокращает разрыв между ними на 1.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>slow = head, fast = head.</li>
 *   <li>Пока fast не null и fast.next не null:
 *       <ul>
 *         <li>slow сдвинуть на 1 узел.</li>
 *         <li>fast сдвинуть на 2 узла.</li>
 *         <li>если slow == fast — цикл есть, вернуть true.</li>
 *       </ul>
 *   </li>
 *   <li>Если цикл закончился — fast дошёл до конца, цикла нет, вернуть
 *       false.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n).<br>
 * Память: O(1).</p>
 */
public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        // 1. Создаём список 3 -> 2 -> 0 -> -4
        ListNode head = createList(new int[]{3, 2, 0, -4});
//        ListNode head = createList(new int[]{1, 2, 3});

        // 2. Находим хвост (узел со значением -4, индекс 3)
        ListNode tail = getNode(head, 3);

        // 3. Находим узел, куда должен вести хвост (индекс 1, значение 2)
        ListNode posNode = getNode(head, 1);

        // 4. СОЗДАЁМ ЦИКЛ! (Без этой строки цикла нет)
        tail.next = posNode;

        System.out.println(hasCycle(head));
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // Вспомогательный метод для создания списка из массива
    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static ListNode getNode(ListNode head, int index) {
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }
}