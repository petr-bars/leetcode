package com.example.sixth_step.utils;


import com.example.sixth_step.TreeNode;

import java.util.*;

public class Utils {
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode parent = queue.poll();
            // левый потомок
            if (i < arr.length && arr[i] != null) {
                parent.left = new TreeNode(arr[i]);
                queue.offer(parent.left);
            }
            i++;
            // правый потомок
            if (i < arr.length && arr[i] != null) {
                parent.right = new TreeNode(arr[i]);
                queue.offer(parent.right);
            }
            i++;
        }
        return root;
    }

    public static String treeToString(TreeNode root) {
        if (root == null) return "[]";

        List<String> values = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                values.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                values.add("null");
            }
        }

        // Убираем конечные "null" (они не нужны)
        int last = values.size() - 1;
        while (last >= 0 && values.get(last).equals("null")) {
            last--;
        }
        values = values.subList(0, last + 1);

        return "[" + String.join(", ", values) + "]";
    }
}
