package fun.neverth;

import java.util.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/19 13:56
 */
public class Part4 {

    /**
     * 复习 673. 最长递增子序列的个数
     */
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;

        int[] dp = new int[len];
        int[] combination = new int[len];

        Arrays.fill(dp, 1);
        Arrays.fill(combination, 1);

        int max = 1;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        combination[i] = combination[j];

                    } else if (dp[j] + 1 == dp[i]) {
                        combination[i] += combination[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == max) {
                res += combination[i];
            }
        }
        return res;
    }

    /**
     * 56. 合并区间
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int index = -1;

        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }

    /**
     * LeetCode US
     * 408. Valid Word Abbreviation
     */
    public boolean valid(String word, String abbr) {
        int m = word.length(), n = abbr.length();

        //双指针，i 是指源字符串， j 是指缩写字符串
        int i = 0, j = 0;

        while (i < m && j < n) {
            // 如果缩写字符串的 j 位置是数字
            if (abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {

                // 如果缩写字符串的 j 位置为0则直接返回false
                if (abbr.charAt(j) == '0') {
                    return false;
                }

                // 计算数字的大小
                int value = 0;
                while (j < n && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    value = value * 10 + abbr.charAt(j++) - '0';

                }

                // 跳过源字符串中的 value 个字符
                i += value;

            } else {
                // 不相等，直接返回false
                if (word.charAt(i++) != abbr.charAt(j++)) {
                    return false;

                }
            }
        }

        return i == m && j == n;
    }

    /**
     * 1. 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {

            int b = target - nums[i];

            if (map.containsKey(b)) {
                return new int[]{i, map.get(b)};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public int test(int a, int b, int c) {

        int[] res = new int[]{
                a * b * c,
                a * b + c,
                a * (b + c),
                a + b * c,
                (a + b) * c,
                a + b + c,
                a + (b + c)
        };

        int max = -1;
        for (int t : res) {
            max = Math.max(max, t);
        }

        return max;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 662. 二叉树最大宽度
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        Deque<Integer> d = new LinkedList<>();

        q.offer(root);
        d.offer(1);

        int max = 1;
        while (!q.isEmpty()) {

            int sz = q.size();

            for (int i = 0; i < sz; i++) {

                TreeNode node = q.poll();

                int index = d.poll();
                if (node.left != null) {
                    q.offer(node.left);
                    d.offer(index * 2);
                }

                if (node.right != null) {
                    q.offer(node.right);
                    d.offer(index * 2 + 1);
                }
            }
            if (d.size() >= 2) {
                max = Math.max(max, d.getLast() - d.getFirst() + 1);
            }
        }

        return max;
    }

    /**
     * 复习 111. 二叉树的最小深度
     * bfs
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();

                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 206. 反转链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {

            ListNode tmp = cur.next;

            cur.next = pre;

            pre = cur;

            cur = tmp;
        }
        return pre;
    }

    public ListNode reverseList1(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode cur = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    public ListNode reverse(ListNode a, ListNode b){
        ListNode pre, cur, next;
        pre = null; cur = a; next = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }

    /**
     * 复习 25. K 个一组翻转链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null){
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null){
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * 15. 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if(nums.length < 3){
            return res;
        }

        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++){

            if(nums[0] > 0){
                return res;
            }

            if(i > 0 && (nums[i] == nums[i - 1])){
                continue;
            }

            int target = -nums[i];

            int left = i + 1, right = nums.length - 1;

            while(left < right){

                if((nums[left] + nums[right]) == target){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while(left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if((nums[left] + nums[right] > target)){
                    right--;

                }else{
                    left++;

                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();

        int[] a = {-1,0,1,2,-1,-4};
        int[] a1 = {9, 15, 7, 20, 3};
        String[] b = {"0000"};

        int[][] c = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };


        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = null;

//        n1.left = n2;
//        n1.right = n3;
//        n3.left = n4;
//        n3.right = n5;
        System.out.println(part4.threeSum(a));


    }
}
