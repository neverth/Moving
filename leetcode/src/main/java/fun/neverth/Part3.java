package fun.neverth;

import java.util.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/10 0:02
 */
public class Part3 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 53. 最大子序和
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;

        int[] dp = new int[len];

        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int value : dp) {
            res = Math.max(value, res);
        }
        return res;
    }

    /**
     * 面试题03. 剑指offer-数组中重复的数字-修改原数组
     */
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {

            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];

        nums[a] = nums[b];

        nums[b] = temp;
    }

    /**
     * 287. 寻找重复数 剑指offer-数组中重复的数字-不修改原数组
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;

        int left = 1, right = len - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int n = 0;
            for (int value : nums) {
                if (value <= mid) {
                    n++;
                }
            }

            if (n > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return left;
    }

    /**
     * 442. 数组中重复的数据-技巧型
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                res.add(Math.abs(index + 1));
            }

            nums[index] = -nums[index];

        }

        return res;
    }

    /**
     * 217. 存在重复元素-哈希表解决,或者排序
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);

        for (int num : nums) {

            if (set.contains(num)) {
                return true;
            }
            set.add(num);

        }

        return false;
    }

    /**
     * 219. 存在重复元素 II-哈希表解决
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            if (map.get(nums[i]) != null && (i - map.get(nums[i])) <= k) {
                return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }

    /**
     * 240. 搜索二维矩阵 II && 面试题04. 二维数组中的查找
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;

        if (n == 0) {
            return false;
        }

        int m = matrix[0].length;

        int r = 0, c = m - 1;

        while (r < n && c >= 0) {

            if (matrix[r][c] == target) {
                return true;

            } else if (matrix[r][c] > target) {
                c--;

            } else {
                r++;
            }
        }

        return false;
    }


    /**
     * 面试题05. 替换空格
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 面试题 10.01. 合并排序的数组
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int k = m + n - 1, i = m - 1, j = n - 1;

        while (i >= 0 && j >= 0) {
            if (A[i] < B[j]) {
                A[k--] = B[j--];

            } else {
                A[k--] = A[i--];

            }
        }

        while (j >= 0) {
            A[k--] = B[j--];
        }
    }

    /**
     * 面试题06. 从尾到头打印链表
     */
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        int cnt = 0;
        while (!stack.isEmpty()) {
            res[cnt++] = stack.pop();
        }
        return res;
    }


    /**
     * 面试题07. 重建二叉树 && 105. 从前序与中序遍历序列构造二叉树-递归写法
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        // 前序遍历的第一个位置就是根节点
        TreeNode root = new TreeNode(preorder[0]);
        // TODO: 这里为了简洁，可以通过HASH
        for (int i = 0; i < preorder.length; i++) {
            // 在中序遍历中找到与根节点对应的位置
            if (preorder[0] == inorder[i]) {
                root.left = buildTree(
                        // copyOfRange对应的范围 {a, b)
                        Arrays.copyOfRange(preorder, 1, i + 1),
                        Arrays.copyOfRange(inorder, 0, i)
                );

                root.right = buildTree(
                        Arrays.copyOfRange(preorder, i + 1, preorder.length),
                        Arrays.copyOfRange(inorder, i + 1, inorder.length)
                );
                break;
            }
        }
        return root;
    }


    /**
     * 面试题07. 重建二叉树 && 105. 从前序与中序遍历序列构造二叉树-非递归写法
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {

        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        // 根节点就是前序节点的第一个
        TreeNode root = new TreeNode(preorder[0]);
        // 用栈维护右节点还没有赋值的节点
        // 左子树可以立即赋值，通过判断中序遍历的第一个结点是否等于根节点即可
        Stack<TreeNode> stack = new Stack<>();
        // 根节点入栈
        stack.push(root);
        // 中序遍历下标
        int inorderIndex = 0;
        // 从前序遍历下一个相邻节点开始判断，判断其是在根节点的左 or 右子树
        for (int i = 1; i < preorder.length; i++) {
            // peek一下栈顶(右节点还没有赋值的节点)，并不会出栈
            TreeNode node = stack.peek();
            // 前序遍历和中序遍历对应下标不等，代表节点存在左节点
            if (node.val != inorder[inorderIndex]) {
                // 前序遍历下一个相邻节点是前节点的左节点，赋值
                node.left = new TreeNode(preorder[i]);
                // node.left的右节点还没有赋值，入栈
                stack.push(node.left);
            }
            // 前序遍历和中序遍历对应下标相等，即节点不存在左节点，
            else {
                // 前序遍历下一个相邻节点是已经入栈节点的右节点，但是不知道具体是哪一个节点
                while (!stack.isEmpty()
                        && stack.peek().val == inorder[inorderIndex]
                ) {
                    // 所以在这里进行过滤，从前序遍历已经遍历的节点逆序(对应出栈)开始和
                    // 中序遍历比较，相等就代表该节点不存在右子节点直接跳过
                    node = stack.pop();
                    inorderIndex++;
                }
                // 直到不相等，代表前序遍历下一个相邻节点就是这个节点的右节点
                node.right = new TreeNode(preorder[i]);
                // 其不存在右节点，入栈
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);

        for (int i = 0; i < inorder.length; i++) {

            if (root.val == inorder[i]) {

                root.left = buildTree(
                        Arrays.copyOfRange(inorder, 0, i),
                        Arrays.copyOfRange(postorder, 0, i)
                );

                root.right = buildTree(
                        Arrays.copyOfRange(inorder, i + 1, inorder.length),
                        Arrays.copyOfRange(postorder, i, postorder.length - 1)

                );
                break;
            }
        }
        return root;
    }

    /**
     * 面试题07. 二叉树的下一个节点
     */
    public TreeNode getNext(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode next = null;

        if (node.right != null) {

            TreeNode temp = node.right;

            while (temp.left != null) {
                temp = temp.left;
            }

            next = temp;

        } else if (node.parent != null) {

            TreeNode cur = node;
            TreeNode parent = node.parent;

            while (parent != null && cur == parent.left) {
                cur = parent;
                parent = parent.parent;
            }

            next = parent;

        }

        return next;
    }

    /**
     * 面试题09. 用两个栈实现队列
     */
    private final Stack<Integer> stack1 = new Stack<>();
    private final Stack<Integer> stack2 = new Stack<>();

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        int res;

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        if (!stack2.isEmpty()) {
            res = stack2.pop();
        } else {
            res = -1;
        }


        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        return res;
    }

    /**
     * 面试题10- I. 斐波那契数列 && 509. 斐波那契数
     */
    public int fib(int n) {

        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] = dp[i] % 1000000007;
        }

        return dp[n];
    }

    /**
     * 面试题10- II. 青蛙跳台阶问题 && 70. 爬楼梯
     */
    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] = dp[i] % 1000000007;
        }

        return dp[n];
    }

    /**
     * 面试题11. 旋转数组的最小数字 && 153. 寻找旋转排序数组中的最小值
     * && 154. 寻找旋转排序数组中的最小值 II
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (numbers[mid] > numbers[right]) {
                left = mid + 1;

            } else if (numbers[mid] < numbers[right]) {
                right = mid;

            } else {
                right--;
            }

        }
        return numbers[left];
    }

    /**
     * 33. 搜索旋转排序数组
     */
    public int search(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (numbers[mid] == target) {
                return mid;

            } else if (numbers[left] <= numbers[mid]) {

                if (target >= numbers[left] && target < numbers[mid]) {
                    right = mid - 1;

                } else {
                    left = mid + 1;
                }

            } else if (numbers[mid] <= numbers[right]) {

                if (target > numbers[mid] && target <= numbers[right]) {
                    left = mid + 1;

                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 面试题12. 矩阵中的路径 && 79. 单词搜索
     */
    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {

                    if (backtrack(board, word, i, j, 0)) {
                        return true;
                    }

                }
            }
        }

        return false;
    }

    public boolean backtrack(char[][] board, String word, int i, int j, int k) {

        if (
                i >= board.length
                        || i < 0
                        || j >= board[0].length
                        || j < 0
                        || board[i][j] != word.charAt(k)
        ) {

            return false;
        }

        if (k == word.length() - 1) {
            return true;
        }

        char temp = board[i][j];

        board[i][j] = '0';

        boolean res = backtrack(board, word, i + 1, j, k + 1)
                || backtrack(board, word, i - 1, j, k + 1)
                || backtrack(board, word, i, j + 1, k + 1)
                || backtrack(board, word, i, j - 1, k + 1);

        board[i][j] = temp;

        return res;
    }

    /**
     * 面试题13. 机器人的运动范围
     */
    public int movingCount(int m, int n, int k) {
        return backtrack(m, n, k, 0, 0, new boolean[m][n]);
    }

    public int backtrack(int m, int n, int k, int i, int j, boolean[][] visited) {

        if (i >= m || i < 0 || j >= n || j < 0 || visited[i][j]) {
            return 0;
        }

        int sum = 0, num1 = i, num2 = j;

        while (num1 > 0) {
            sum += num1 % 10;
            num1 /= 10;
        }

        while (num2 > 0) {
            sum += num2 % 10;
            num2 /= 10;
        }

        if (sum > k) {
            return 0;
        }

        visited[i][j] = true;

        return backtrack(m, n, k, i + 1, j, visited) +
                backtrack(m, n, k, i - 1, j, visited) +
                backtrack(m, n, k, i, j + 1, visited) +
                backtrack(m, n, k, i, j - 1, visited) + 1;

    }

    /**
     * 面试题14- I. 剪绳子 && 343. 整数拆分
     */
    public int cuttingRope(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        int[] dp = new int[n + 1];

        dp[2] = 1;
        dp[3] = 2;

        /*
         * 关键一步 dp[i]由四种状态组成
         * 1.拆j数字分成小块整数拆分求最大 i - j不拆开 就是dp[j] * (i - j)
         * 2.拆i - j这个数字整数拆分求最大j不拆开就是dp[i - j] * j
         * 3.两个都整数拆分分别求最大就是 dp[i - j] * dp[j]
         * 4. i和i - j都不整数拆分 就是i * (i - j)
         * 然后四个求出最大值
         */
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }

        return dp[n];
    }

    /**
     * 面试题14- II. 剪绳子 II
     */
    public int cuttingRope1(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        long res = 1;
        if (n % 3 == 1) {
            res = 4;
            n -= 4;

        } else if (n % 3 == 2) {
            res = 2;
            n -= 2;

        }

        while (n > 0) {
            res = res * 3;
            res = res % 1000000007;
            n -= 3;
        }
        return (int) res;
    }

    /**
     * 面试题15. 二进制中1的个数 && 191. 位1的个数
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }

        return res;
    }

    public int hammingWeight1(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }

    /**
     * 面试题16. 数值的整数次方 && 50. Pow(x, n)
     */
    public double myPow(double x, int n) {

        if (x == 0) {
            return 0;
        }

        long b = n;

        double res = 1.0;

        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }

    /**
     * 面试题17. 打印从1到最大的n位数
     */
    public int[] printNumbers(int n) {
        int num = (int) Math.pow(10, n) - 1;

        int[] res = new int[num];

        for (int i = 0; i < num; i++) {
            res[i] = i + 1;
        }

        return res;
    }

    /**
     * 面试题17. 打印从1到最大的n位数 -- 使用字符串构造
     */
    public void printNumbers1(int n) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append('0');
        }

        while (!increment(str)) {

            int index = 0;
            while (index < str.length() && str.charAt(index) == '0') {
                index++;
            }
            System.out.println(str.toString().substring(index));
        }
    }

    public boolean increment(StringBuilder str) {
        boolean isOverflow = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            char s = (char) (str.charAt(i) + 1);

            if (s > '9') {
                str.replace(i, i + 1, "0");
                if (i == 0) {
                    isOverflow = true;
                }
            } else {
                str.replace(i, i + 1, String.valueOf(s));
                break;
            }
        }
        return isOverflow;
    }

    /**
     * 面试题17. 打印从1到最大的n位数 -- 全排列
     */
    public int[] printNumbers2(int n) {
        List<Integer> list = new ArrayList<>();

        dfs(list, n, 0, new StringBuilder());

        int[] res = new int[list.size()];

        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    private void dfs(List<Integer> list, int n, int i, StringBuilder sb) {
        if (i == n) {

            while (sb.length() != 0 && sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            }

            if (sb.length() != 0) {
                list.add(Integer.valueOf(sb.toString()));
            }

            return;
        }
        for (int j = 0; j < 10; j++) {

            sb.append(j);

            dfs(list, n, i + 1, sb);

            if (sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

        }
    }

    /**
     * 面试题18. 删除链表的节点
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode p = head;

        if (p.val == val) {
            if (p.next == null) {
                return null;
            }
            head = head.next;
        }

        while (p.next.val != val) {
            p = p.next;

            if (p.next == null) {
                return head;
            }
        }

        p.next = p.next.next;

        return head;
    }

    /**
     * 面试题19. 正则表达式匹配 && 10. 正则表达式匹配
     */
    public boolean isMatch(String s, String p) {

        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean first = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (first && isMatch(s.substring(1), p));

        } else {
            return first && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * 面试题19. 正则表达式匹配 && 10. 正则表达式匹配
     */
    public boolean isMatch1(String s, String p) {

        if (p.isEmpty()) {
            return s.isEmpty();
        }

        HashMap<String, Boolean> isMatchMemo = new HashMap<>();

        return isMatch1Dp(isMatchMemo, s, p, 0, 0);
    }

    public boolean isMatch1Dp(HashMap<String, Boolean> isMatchMemo,
                              String s, String p, int i, int j) {
        if (isMatchMemo.containsKey(i + "-" + j)) {
            return isMatchMemo.get(i + "-" + j);
        }

        if (j == p.length()) {
            return i == s.length();
        }

        boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        boolean res;

        if (j <= p.length() - 2 && p.charAt(j + 1) == '*') {
            res = isMatch1Dp(isMatchMemo, s, p, i, j + 2)
                    || (first && isMatch1Dp(isMatchMemo, s, p, i + 1, j));

        } else {
            res = first && isMatch1Dp(isMatchMemo, s, p, i + 1, j + 1);
        }

        isMatchMemo.put(i + "-" + j, res);
        return res;
    }

    /**
     * 1014. 最佳观光组合
     */
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0, mx = A[0];
        for (int j = 1; j < A.length; ++j) {
            ans = Math.max(ans, mx + A[j] - j);

            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }

    public void printDp(int[][] dp) {

        for (int[] ints : dp) {
            for (int anInt : ints) {

                System.out.print(anInt);
                System.out.print('\t');
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    /**
     * 复习 1143. 最长公共子序列
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] ch1 = text1.toCharArray();
        char[] ch2 = text2.toCharArray();

        int[][] dp = new int[ch1.length + 1][ch2.length + 1];

        for (int i = 1; i <= ch1.length; i++) {
            for (int j = 1; j <= ch2.length; j++) {

                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
//        printDp(dp);
        return dp[ch1.length][ch2.length];
    }

    public void printDp(int[] dp) {

        for (int ints : dp) {
            System.out.print(ints);
            System.out.print('\t');
        }
        System.out.println("\n------------------");
    }

    /**
     * 复习 300. 最长上升子序列
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        printDp(dp);

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();

        int[] a = {3,9,8,5,4,10,20,15,7};
        int[] a1 = {4,5,8,10,9,3,15,20,7};
        String[] b = {"0000"};

        int[][] c = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };


        Common.TreeNode n1 = new Common.TreeNode(3);
        Common.TreeNode n2 = new Common.TreeNode(9);
        Common.TreeNode n3 = new Common.TreeNode(20);
        Common.TreeNode n4 = new Common.TreeNode(15);
        Common.TreeNode n5 = new Common.TreeNode(7);

        Part2.ListNode l1 = new Part2.ListNode(1);
        Part2.ListNode l2 = new Part2.ListNode(2);
        Part2.ListNode l3 = new Part2.ListNode(3);
        Part2.ListNode l4 = new Part2.ListNode(4);
        Part2.ListNode l5 = new Part2.ListNode(3);

        l1.next = null;
        l2.next = null;
        l3.next = null;
        l4.next = null;

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        System.out.println(part3.buildTree1(a, a1));

    }

}
