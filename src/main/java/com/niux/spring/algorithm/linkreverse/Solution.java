package com.niux.spring.algorithm.linkreverse;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.reverseList(new ListNode(3));
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.5 MB
     * , 在所有 Java 提交中击败了
     * 60.17%
     * 的用户
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode i = head;
        ListNode j = head.next;
        if (j == null) {
            return head;
        }

        ListNode k = j.next;
        if (k == null) {
            j.next = i;
            i.next = null;
            return j;
        }

        i.next = null;
        while (k != null) {
            j.next = i;

            i = j;
            j = k;
            k = k.next;

        }

        j.next = i;


        return j;


    }
}


