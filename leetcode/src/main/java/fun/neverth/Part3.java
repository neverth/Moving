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

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 中间元素大于左边，代表最小值范围在中间元素的右边
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
                // 中间元素小于左边，代表最小值范围就在包括中间元素之内的左边
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
                // 相等的话，缩小右边的范围，避免死循环
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
    // 方便访问
    char[] word;
    char[][] board;

    public boolean exist(char[][] board, String word) {
        this.word = word.toCharArray();
        this.board = board;
        // 可以从矩阵的任意一个点开始，所以可以在这里进行遍历
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(
            int i,      // 下标
            int j,      // 下标
            int len     // 已经匹配的长度
    ) {
        // 超过边界或者对应位置字符不匹配
        if (i < 0 || j < 0
                || i > board.length - 1
                || j > board[0].length - 1
                || word[len] != board[i][j]) {
            return false;
        }
        // 代表已经找到对应的路径
        if (len == word.length - 1) {
            return true;
        }
        // 防止走回头路，回头路跟将要匹配的字符相等就会出错
        char tmp = board[i][j];
        board[i][j] = '/';
        // 分别对应四种情况
        boolean res = dfs(i + 1, j, len + 1) || dfs(i - 1, j, len + 1)
                || dfs(i, j + 1, len + 1) || dfs(i, j - 1, len + 1);
        board[i][j] = tmp;
        return res;
    }

    /**
     * 面试题13. 机器人的运动范围
     */
    // 方便访问
    int limit, m, n;
    boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        this.limit = k;
        this.m = m;
        this.n = n;
        // 用于记录已经访问的格子
        this.visited = new boolean[m][n];
        return dfs(0, 0);
    }

    public int dfs(int i, int j) {
        // 计算行坐标和列坐标的数位之和
        int k = 0, i1 = i, j1 = j;
        while (i1 != 0) {
            k += (i1 % 10);
            i1 /= 10;
        }
        while (j1 != 0) {
            k += (j1 % 10);
            j1 /= 10;
        }
        // 下标出界或者是已经访问
        if (k > limit || i < 0 || j < 0 || i >= m || j >= n || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        // 对应四种情况，其实通过向右和向下移动，访问所有可达解。
        return dfs(i + 1, j) + dfs(i - 1, j) + dfs(i, j + 1) + dfs(i, j - 1) + 1;
    }

    public int movingCount1(int m, int n, int k) {
        // 用于记录已经访问的格子
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        // 创建一个队列用于保存一个节点周围的所有节点
        // 原理是遍历队列里面的每个节点并再将遍历节点周围的结点入队
        // 这样一圈一圈的处理就是广度优先遍历
        Queue<int[]> queue = new LinkedList<>();
        // 初始化加入第一个结点
        queue.add(new int[]{0, 0});
        while (queue.size() > 0) {
            // 开始处理第一个节点
            int[] x = queue.poll();
            // 计算行坐标和列坐标的数位之和
            int i = x[0], j = x[1];
            int k1 = 0, i1 = i, j1 = j;
            while (i1 != 0) {
                k1 += (i1 % 10);
                i1 /= 10;
            }
            while (j1 != 0) {
                k1 += (j1 % 10);
                j1 /= 10;
            }
            // 不满足条件，跳过这个结点
            if (i >= m || j >= n || i < 0 || j < 0 || k < k1 || visited[i][j]) {
                continue;
            }
            visited[i][j] = true;
            res++;
            // 将节点周围的一圈的结点加入队列
            // 其实通过向右和向下移动，访问所有可达解。
            queue.add(new int[]{i + 1, j});
            queue.add(new int[]{i - 1, j});
            queue.add(new int[]{i, j + 1});
            queue.add(new int[]{i, j - 1});
        }
        return res;
    }

    /**
     * 面试题14- I. 剪绳子 && 343. 整数拆分
     */
    public int cuttingRope(int n) {
        // dp数组，下标为n的元素的值代表对应长度绳子分解之后的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        if (n > 2) {
            dp[3] = 2;
        }
        for (int i = 4; i <= n; i++) {
            // 开始剪绳子，尝试每一个长度
            for (int j = 2; j < i; j++) {
                // 他的最乘积就是他
                // 被剪之后的最大乘积和减掉的长度相乘
                // 和被剪之后的长度和减掉的长度相乘中的最大值
                // 因为dp[x]有可能小于x，比如dp[3] = 2 < 3
                dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, (i - j) * j));
            }
        }
        return dp[n];
    }

    /**
     * 面试题14- I. 剪绳子 && 343. 整数拆分
     * 贪心算法
     * 时间复杂度O(n)
     * 为使乘积最大，只有长度为 2 和 3 的绳子不应再切分，且 3 比 2 更优
     */
    public int cuttingRope2(int n) {
        int res = 1;
        // 将n转为3的倍数，方便后面处理
        // 比3的倍数大1，
        // 此时要将n转为3的倍数并让乘积最大
        // 最好的办法是减掉4,4的最大乘积为4
        if(n % 3 == 1){
            res = 4;
            n -= 4;
        }
        // // 比3的倍数大2，直接减2并乘2
        else if(n % 3 == 2){
            res = 2;
            n -= 2;
        }
        // 此时是3的倍数
        while(n > 0){
            res *= 3;
            n -= 3;
        }
        return res;
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

        int[] a = {1, 3, 5};
        int[] a1 = {4, 5, 8, 10, 9, 3, 15, 20, 7};
        String[] b = {"0000"};

        char[][] c = new char[][]{
                {'A', 'A'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
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
        System.out.println(part3.movingCount1(1, 2, 1));

    }

}
