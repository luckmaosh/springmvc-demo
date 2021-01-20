package com.niux.spring.algorithm.linkreverse;

class ResverGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    /**
     * 反转链表
     *
     * @param head
     * @param tail
     * @return
     */
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode start = head;
        while (prev != tail) {
            ListNode nex = start.next;
            start.next = prev;
            prev = start;
            start = nex;
        }
        return new ListNode[]{tail, head};
    }


    private ListNode reverse(ListNode start, ListNode end) {

        ListNode pre = end;
        ListNode n = null;
        while (start != end) {
            n = start;

            n.next = pre.next;
            pre.next = n;
            start = start.next;

        }
        return pre;
//        ListNode prev = end;
//        while(start != end) {
//            ListNode next = start.next;
//            start.next = prev;
//            prev = start;
//            start = next;
//        }
//        return prev;
    }

}
//
//作者：LeetCode-Solution1
//        链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/k-ge-yi-zu-fan-zhuan-lian-biao-by-leetcode-solutio/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。