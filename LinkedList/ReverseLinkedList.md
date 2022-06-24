```java
import java.util.Scanner;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        // scanf 1 2 3 4 5
        int[] arr = new int[str.length];
        for(int i = 0; i < str.length; i++){
            arr[i] = Integer.parseInt(str[i]);
        }
        // 静态方法中不能调用直接调用动态方法
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for(int i = 0; i < arr.length; i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        ListNode node = reverseList(dummy.next);
        while(node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
        sc.close();
    }
}
```

