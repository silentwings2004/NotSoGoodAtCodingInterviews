package S16_InterviewQuestionsI;
import java.util.*;
public class DeepCopySkipList {
    /**
     * Deep Copy skipList
     */
    // S2: One pass --> hashmap
    public void skipList(ListNode head) {
        // corner case
        if (head == null) return;

        ListNode dummy = new ListNode(0);
        ListNode cur1 = head;
        ListNode cur2 = dummy;
        Map<ListNode, ListNode> map = new HashMap<>();

        while (cur1 != null) {
            if (!map.containsKey(cur1)) { // hashmap里处理可能有可能没有的非常clean的经典写法！
                map.put(cur1, new ListNode(cur1.val)); // 如果没有cur1’就让它有吧
            }
            cur2.next = map.get(cur1); // cur2.next = cur1’ → map.get(cur1)但cur1’不见得在map里
            // 更新forward,但cur1和cur2是错位的,而forward是要对齐更新的，所以这时候跟cur1对齐的是cur2.next ⇒ 用cur1.forward去
            // 更新cur2.next.forward
            if (cur1.forward != null) { // next一定有，但forward未必有
                if (!map.containsKey(cur1.forward)) { // 查重，看看历史上是否已经创建过
                    map.put(cur1.forward, new ListNode((cur1.forward.val)));
                }
                cur2.next.forward = map.get(cur1.forward); // cur1’.forward = (cur1.forward)’
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
    }
}
