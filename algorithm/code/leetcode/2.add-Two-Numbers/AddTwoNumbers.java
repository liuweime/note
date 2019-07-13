public class AddTwoNumbers {

       class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode run(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0);
        ListNode target = head;
        while (l1 != null || l2 != null) {
            // null 节点补 0
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            // 节点值 + 进位值
            int val = a + b + carry;
            // 取个位
            target.next = new ListNode(val % 10);
            // 移动节点
            target = target.next;
            // 取十位
            carry = val / 10;

            // 移动两个链表
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 进位不为0
        if (carry != 0) {
            target.next = new ListNode(carry);
        }

        return head.next;
    }

}
