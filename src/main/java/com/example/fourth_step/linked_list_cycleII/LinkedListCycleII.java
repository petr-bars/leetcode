package com.example.fourth_step.linked_list_cycleII;

/**
 * <p>Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.</p>
 *
 * <p>There is a cycle in a linked list if there is some node in the list that
 * can be reached again by continuously following the next pointer. Internally,
 * pos is used to denote the index of the node that tail's next pointer is
 * connected to (0-indexed). It is -1 if there is no cycle. Note that pos is
 * not passed as a parameter.</p>
 *
 * <p>Do not modify the linked list.</p>
 *
 * <p>Дан головной узел связного списка. Вернуть узел, с которого начинается
 * цикл. Если цикла нет — вернуть null. Список изменять нельзя.</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [3,2,0,-4], pos = 1<br>
 * Выход: узел с индексом 1 (значение 2)</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [1,2], pos = -1<br>
 * Выход: null</p>
 *
 * <p>Паттерн:<br>
 * Fast &amp; slow pointers + математика расстояний.</p>
 *
 * <p>Идея:<br>
 * Сначала находим точку встречи slow и fast внутри цикла (как в задаче
 * «есть ли цикл»). Затем перезапускаем один указатель с головы, а второй
 * оставляем в точке встречи. Двигаем оба с одинаковой скоростью — на 1 узел.
 * Они встретятся ровно в узле, где начинается цикл.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>Фаза 1: slow = head, fast = head.
 *       <ul>
 *         <li>Пока fast не null и fast.next не null:
 *             <ul>
 *               <li>slow на 1 узел, fast на 2 узла.</li>
 *               <li>если slow == fast — точка встречи найдена, выходим
 *                   из цикла.</li>
 *             </ul>
 *         </li>
 *         <li>Если fast дошёл до конца — цикла нет, вернуть null.</li>
 *       </ul>
 *   </li>
 *   <li>Фаза 2: slow = head, fast остаётся в точке встречи.
 *       <ul>
 *         <li>Пока slow != fast — оба двигаем на 1 узел.</li>
 *         <li>Где встретились — там начало цикла. Вернуть slow.</li>
 *       </ul>
 *   </li>
 * </ul>
 *
 * <p>Почему фаза 2 работает:</p>
 * <ul>
 *   <li>Пусть L — расстояние от головы до начала цикла, C — длина цикла,
 *       X — расстояние от начала цикла до точки встречи.</li>
 *   <li>slow прошёл L + X. fast прошёл L + X + C (на круг больше).</li>
 *   <li>fast идёт в 2 раза быстрее: 2(L + X) = L + X + C.</li>
 *   <li>Отсюда L = C − X. То есть расстояние от головы до начала цикла
 *       равно расстоянию от точки встречи до начала цикла (по кругу).</li>
 *   <li>Значит, если пустить одного с головы, а второго с точки встречи
 *       с одинаковой скоростью — встретятся в начале цикла.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n).<br>
 * Память: O(1).</p>
 */
public class LinkedListCycleII {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        // Пример: [3,2,0,-4], pos = 1
        ListNode head = createList(new int[]{3, 2, 0, -4});
        ListNode tail = getNode(head, 3);
        ListNode posNode = getNode(head, 1);
        if (tail != null && posNode != null) {
            tail.next = posNode; // создаём цикл
        }

        ListNode cycleStart = detectCycle(head);
        if (cycleStart != null) {
            System.out.println("Цикл начинается с узла со значением: " + cycleStart.val);
        } else {
            System.out.println("Цикла нет");
        }
        // Вывод: Цикл начинается с узла со значением: 2
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
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