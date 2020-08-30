package fun.neverth;

import lombok.Data;

import java.util.*;


public class Part5 {
    private static HashMap<Integer, Boolean> dic = new HashMap<>();

    public static boolean funa(int n) {
        ArrayList<Integer> integers = new ArrayList<>();
        while (n != 0) {
            integers.add(n % 10);
            n = n / 10;
        }
        if (integers.size() == 1) {
            return integers.get(0) == 7;
        }

        int size = integers.size();
        int i = size - 1;
        while (size > 0) {

            integers.add(Math.abs(integers.remove(i) - integers.get(i - 1)));
            i--;
            if (i == 0) {
                integers.remove(0);
                size = integers.size();
                i = size - 1;

                if (size == 1) {
                    return integers.get(0) == 7;
                }
            }
        }
        return false;
    }

    public static class ThreadPrinter implements Runnable {

        private String name;
        private Object prev;
        private Object self;

        public ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (prev) {
                    synchronized (self) {
                        System.out.print(name);
                        count--;
                        self.notifyAll();
                    }
                    try {
                        if (count == 0) {

                            prev.notifyAll();
                        } else {

                            prev.wait();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 牛客网 序列交换 网易 https://www.nowcoder.com/profile/730427842/codeBookDetail?submissionId=84132355
     */
    public void printAbc() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int count = 0;
        for (int el : array) {
            if (el % 2 == 0) {
                count++;
            }
        }

        if (count != 0 && count != n) {
            Arrays.sort(array);
        }

        for (int el : array) {
            System.out.print(el + " ");
        }
    }

    public void insertSort(int[] arr) {

        int len = arr.length;

        for (int i = 1; i < len; i++) {
            int temp = arr[i];

            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            if (j != i) {
                arr[j] = temp;
            }

        }

    }

    @Data
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 时间复杂度O(nlogn)
// hash从中序遍历中找到对应值的下标，下标左右为对应的左右子树
    HashMap<Integer, Integer> map = new HashMap<>();

    // 先序遍历，方便在递归中进行访问
    int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < preorder.length; i++) {
            // 添加值和对应的下标
            map.put(inorder[i], i);
        }
        return recursive(0, 0, inorder.length - 1);
    }

    public TreeNode recursive(
            int pre_root_idx, // 根节点对应的下标
            int in_left_idx,  // 中序遍历对应范围的左下标
            int in_right_idx  // 中序遍历对应范围的右下标
    ) {
        // 中序遍历范围小于0代表已完成
        if (in_right_idx - in_left_idx < 0) {
            return null;
        }
        // 根据根节点下标创建根节点
        TreeNode root = new TreeNode(preorder[pre_root_idx]);
        // 根据根节点的值从HashMap中找到对应在中序遍历中的下标
        int idx = map.get(preorder[pre_root_idx]);
        // 根节点的左子树就是根的左子树与中序遍历缩小范围的递归过程
        root.left = recursive(pre_root_idx + 1, in_left_idx, idx - 1);
        // 根节点的右子树就是根的右子树与中序遍历缩小范围的递归过程
        root.right = recursive(
                pre_root_idx + 1 + (idx - in_left_idx),
                idx + 1, in_right_idx
        );

        return root;
    }


    static public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int i = 0, j = matrix[0].length - 1;

        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || recur(root.left, root.right);
    }

    public boolean recur(TreeNode left, TreeNode right) {
        // 都为空，代表遍历完成
        if (left == null && right == null) {
            return true;
        }
        // 判断左右是否相等
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        // 二叉树一个节点有两个节点，两个节点要分别进行比较
        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // bfs广度优先算法
        Queue<TreeNode> queue = new LinkedList<>();
        // 入队
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            // 都为空，代表这一个子树遍历完成
            if (left == null && right == null) {
                continue;
            }
            // 任何一个为空代表不匹配
            else if (left == null || right == null) {
                return false;
            }
            // 值要相等
            if (left.val != right.val) {
                return false;
            }
            // 将下一层的节点入队
            // 二叉树一个节点有两个节点，两个节点要分别进行比较
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        // 上下左右四条边各自的位置 和 结果数组当前结果的下标
        int l = 0, t = 0, r = matrix[0].length - 1, b = matrix.length - 1, len = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        // 一直循环遍历
        while (true) {
            // 从左往右
            for (int i = l; i <= r; i++) {
                res[len++] = matrix[t][i];
            }
            // 判断其是否超过边界，超过边界直接遍历结束
            if (++t > b) {
                break;
            }
            for (int i = t; i <= b; i++) {
                res[len++] = matrix[i][r];
            }
            if (--r < l) {
                break;
            }
            for (int i = r; i >= l; i--) {
                res[len++] = matrix[b][i];
            }
            if (t > --b) {
                break;
            }
            for (int i = b; i >= t; i--) {
                res[len++] = matrix[i][l];
            }
            if (++l > r) {
                break;
            }
        }
        return res;
    }

    /**
     * N个骰子出现和为 m 的概率
     */
    public int getNSumCount(int n, int m) {
        // 不满足情况的条件
        if (n < 1 || m < n || m > 6 * n) {
            return 0;
        }
        // 最后一个筛子且 m 有效，返回一种可能
        if (n == 1) {
            return 1;
        }
        // 递归遍历减掉本次遍历的情况
        int res = 0;
        for (int i = 1; i <= 6; i++) {
            res += getNSumCount(n - 1, m - i);
        }
        return res;
    }

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 创建一个辅助栈
        Stack<Integer> stack = new Stack<>();
        // 弹出序列下标
        int j = 0;
        // 遍历压入序列
        for (int k : pushed) {
            // 模拟入栈
            stack.push(k);
            // 如果栈顶出栈的话，判断能否跟弹出序列对应下标匹配
            while (!stack.empty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.empty();
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     */
    public int[] levelOrder11(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            ArrayList<Integer> ans = new ArrayList<>();
            // 或者这一层所有节点的个数，并一次性遍历完
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                ans.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(ans);
        }
        return res;
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
        }
        int depth = 0;
        while (!queue.isEmpty()) {
            LinkedList<Integer> ans = new LinkedList<>();
            // 或者这一层所有节点的个数，并一次性遍历完
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (depth % 2 == 0) {
                    ans.addLast(node.val);
                } else {
                    ans.addFirst(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(ans);
            depth++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 1, 3, 2, 5};
        Part5 part5 = new Part5();
        part5.validateStackSequences(a, b);
        System.out.println();

    }
}
