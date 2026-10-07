package com.example.fourth_step.remove_nth_node_from_end;

/**
 * <p>Given the head of a linked list, remove the nth node from the end of
 * the list and return its head.</p>
 *
 * <p>Дан головной узел связного списка. Удалить n-й узел с конца и вернуть
 * голову списка.</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [1,2,3,4,5], n = 2<br>
 * Выход: [1,2,3,5]</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [1], n = 1<br>
 * Выход: []</p>
 *
 * <p>Паттерн:<br>
 * Dummy node + два указателя с отступом.</p>
 *
 * <p>Идея:<br>
 * Ставим два указателя на dummy. Fast уводим на n шагов вперёд. Дальше
 * двигаем оба синхронно, пока fast не дойдёт до последнего узла. Тогда
 * slow окажется ровно перед удаляемым узлом, и мы перескакиваем через него.
 * Dummy — нулевой узел перед головой, чтобы не падать, если удалять надо
 * саму голову. Без него slow не сможет её удалить: нет узла, который мог бы
 * перескочить через неё.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>Создаём фиктивный узел dummy со значением 0 и next = head.</li>
 *   <li>slow = dummy, fast = dummy.</li>
 *   <li>Сдвинуть fast на n шагов вперёд</li>
 *   <li>Пока fast.next не null: сдвинуть slow и fast на 1 шаг.</li>
 *   <li>slow.next = slow.next.next — удалить узел после slow.</li>
 *   <li>Вернуть dummy.next — настоящую голову.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n).<br>
 * Память: O(1).</p>
 */
public class RemoveNthNodeFromEnd {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
//        ListNode head = createList(new int[]{1, 2, 3, 4, 5});
        ListNode head = createList(new int[]{1, 2});
        int n = 2;

        System.out.println(listToString(removeNthFromEnd(head, n)));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int index = 0; index < n; index++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }


    // Вспомогательный метод для создания списка из массива
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
