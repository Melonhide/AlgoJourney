package interviewPrepare;

public class removeNthNodeFromEndOfListVariant_19 {

     //Definition for singly-linked list.
     public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }


    // Remove ith Node from Beginning of the list
    public static ListNode removeIthFromHead(ListNode head, int i){
         if(head == null){
             return null;
         }
         if(i == 1){
             ListNode newhead = head.next;
             head.next = null;
             return newhead;
         }
         ListNode p2 = head;
         ListNode p1 = head.next;
         int ind = 1;
         while(ind != i-1 && p1.next != null){
             p2 = p2.next;
             p1 = p1.next;
             ind++;
         }


         if(ind != i-1){
             return head;
         }

         p2.next = p1.next;
         p1.next = null;
         p1 = null;
         return head;
    }

    public static ListNode parse(int[] nums){
         ListNode[] res = new ListNode[nums.length];
         ListNode pre = null;
         for(int i = nums.length-1; i >= 0; i--){
            res[i] = new ListNode(nums[i], pre);
            pre = res[i];
         }


         return res[0];
    }

    public static void main(String[] args){
         int[] nums = new int[]{1,2,3,4,5,6};
         ListNode res = removeIthFromHead(parse(nums), 6);
         while(res != null){
             System.out.print(res.val + " ");
             res = res.next;
         }
    }


}
