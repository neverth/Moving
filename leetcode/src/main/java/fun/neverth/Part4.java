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
        // 定义两个指针一个在前一个在后
        ListNode p1 = null, p2 = head;
        while (p2 != null) {
            // 临时指针，保存后指针的下一个节点
            // 不保存的话交换之后就找不到下一个节点了
            ListNode tmp = p2.next;
            // 反转
            p2.next = p1;
            // 前进
            p1 = p2;
            // 前进
            p2 = tmp;
        }
        return p1;
    }

    /**
     * 递归写法
     * <p>
     * 206. 反转链表
     */
    ListNode newHead;

    public ListNode reverseList2(ListNode head) {
        // 链表为空直接返回
        if (head == null) {
            return head;
        }
        // 内部反转
        re(head);
        // 反转之后的尾的指针不会反转
        // 会构成环，手动帮他置为空
        head.next = null;
        return newHead;
    }

    public void re(ListNode head) {
        if (head.next == null) {
            // 代表到达链表尾，设置为新的头
            newHead = head;
            return;
        }
        // 入栈是顺序的，出栈就是反序
        // 要交换，肯定要两个值，已经有一个head，
        // 下一个值head.next最方便
        ListNode tmp = head.next;
        // 入栈
        reverseList(head.next);
        // 出栈，将指针交换
        tmp.next = head;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    public ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, next;
        pre = null;
        cur = a;
        next = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 他完了不代表其他人完了
        if (l1 == null) {
            return l2;
        }
        // 他完了不代表其他人完了
        if (l2 == null) {
            return l1;
        }
        // 出栈的时候开始构建链表，从尾向前
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 复习 25. K 个一组翻转链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
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

        if (nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            if (nums[0] > 0) {
                return res;
            }

            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }

            int target = -nums[i];

            int left = i + 1, right = nums.length - 1;

            while (left < right) {

                if ((nums[left] + nums[right]) == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if ((nums[left] + nums[right] > target)) {
                    right--;

                } else {
                    left++;

                }
            }
        }
        return res;
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int min = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {

            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 贪心算法
     */
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int res = 0;
        for (int i = 0, j = 0; j < prices.length; j++) {
            if (prices[j] - prices[i] < 0) {
                i = j;

            } else {
                res = Math.max(res, prices[j] - prices[i]);
            }
        }
        return res;
    }

    /**
     * 155. 最小栈
     */
    static class MinStack {
        // 头结点
        Node head;
        // 内部静态Node类
        static class Node{
            Node next;
            int val;
            // 在每个实例node上都保存当前最小值
            int min;

            public Node(int val, int min){
                this.val = val;
                this.min = min;
            }
        }
        public MinStack() {
            head = new Node(0, Integer.MAX_VALUE);
        }
        public void push(int x) {
            // 当前值与栈顶最小值比较，找出最小的值
            int min = x;
            if(head.next != null){
                min = Math.min(min, head.next.min);
            }
            Node node = new Node(x, min);
            // 头插法，head.next即为栈顶
            node.next = head.next;
            head.next = node;
        }
        public void pop() {
            head.next = head.next.next;
        }
        public int top() {
            return head.next.val;
        }
        public int min() {
            // 直接返回栈顶最小值
            return head.next.min;
        }
    }

    /**
     * 复习 3. 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> windows = new HashMap<>();

        int left = 0;
        int right = 0;
        int max = 0;

        while (right < s.length()) {

            char c1 = s.charAt(right++);
            windows.put(c1, windows.getOrDefault(c1, 0) + 1);

            while (windows.get(c1) > 1) {

                char c2 = s.charAt(left++);

                windows.put(c2, windows.get(c2) - 1);

            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    /**
     * 112. 路径总和
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    private int maxPathSum_max = Integer.MIN_VALUE;

    /**
     * 124. 二叉树中的最大路径和
     */
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum_max;
    }

    public int dfs(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        maxPathSum_max = Math.max(maxPathSum_max, node.val + left + right);

        return Math.max(left, right) + node.val;
    }

    public void quickSort(int[] array) {
        int len;
        if (array == null || (len = array.length) == 0 || len == 1) {
            return;
        }
        sort(array, 0, len - 1);
    }

    public void sort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    /**
     * 剑指 Offer 26. 树的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 过滤特殊格式
        if (A == null || B == null) {
            return false;
        }
        // 首先在相对根节点判断一次，然后分辨递归调用器左右再继续判断
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean dfs(TreeNode A, TreeNode B) {
        // 子结构已经匹配完成，返回true
        if (B == null) return true;
        // 子结构没有完成，但是树已经遍历完成了
        if (A == null) return false;
        // 判断其值是否相等，在递归判断其子对应左右是否能匹配上
        return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 递归遍历完左边并保存每次递归左边的值
        TreeNode tmpRight = mirrorTree(root.left);
        // 递归遍历完右边并保存每次递归右边的值
        TreeNode tmpLeft = mirrorTree(root.right);
        // 在树的最底层都是相对局部交换，最后树也可以看成局部，左边右边，一次交换。
        root.right = tmpRight;
        root.left = tmpLeft;
        return root;
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     */
    public TreeNode mirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 栈
        Stack<TreeNode> stack = new Stack<>();
        // 入栈
        stack.add(root);
        // 其实就是树的深度优先遍历，dfs
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 将其左右子树都加入栈
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
            // 交换遍历到的每一个节点
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();

        int[] a = {-1, 0, 1, 2, -1, -4};
        int[] a1 = {9, 15, 7, 20, 3};
        String[] b = {"0000"};

        int[][] c = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };


        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(7);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(9);

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);

        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = null;

        l4.next = l5;
        l5.next = l6;
        l6.next = null;

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        part4.mirrorTree1(n1);
        System.out.println();


    }
}
