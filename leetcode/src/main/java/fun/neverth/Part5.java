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
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     *
     * @author neverth.li
     * @date 2020/8/24 17:41
     */
    public int[] exchange(int[] nums) {
        // 双指针
        int i = 0, j = nums.length - 1;
        while (i < j) {
            // 找到一个偶数
            while (i < j && nums[i] % 2 == 1) {
                i++;
            }
            // 找到一个奇数
            while (i < j && nums[j] % 2 == 0) {
                j--;
            }
            // 交换
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     *
     * @author neverth.li
     * @date 2020/8/24 17:45
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        // 标记是否遇到数位、小数点、E，因为正负号最好判断，只有两种情况，所以也不需要额外定义变量
        boolean hasNum = false, hasDot = false, hasE = false;
        // leetcode给的case中存在空格，去掉
        char[] str = s.trim().toCharArray();
        // 遍历字符串，跟已经遍历的情况比较
        for (int i = 0; i < str.length; i++) {
            // 因为正负号最好判断，只有两种情况，所以也不需要额外定义变量
            if (str[i] == '-' || str[i] == '+') {
                // 1. 正负号只可能出现在第一个位置 2. 出现在 e 的后面一个位置
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') {
                    return false;
                }
            }
            // 判断数字
            else if (str[i] >= '0' && str[i] <= '9') {
                hasNum = true;
            }
            // 判断小数点
            else if (str[i] == '.') {
                // 两种情况 1. 小数点只能有一个 2. e的后面不能有小数点
                if (hasDot || hasE) {
                    return false;
                }
                // 标记已经遇到一个小数点
                hasDot = true;
            }
            // 判断e
            else if (str[i] == 'e' || str[i] == 'E') {
                // 三种情况 1. e只能有一个 2. e前面必须有整数 3. e的后面也必须是一个整数
                if (!hasNum || hasE) {
                    return false;
                }
                // 标记已经遇到一个e
                hasE = true;
                // 重置isNum，因为e之后也必须接上整数
                hasNum = false;
            }
            // 其它情况均为不合法字符
            else {
                return false;
            }
        }
        // 结尾必须为数字
        return hasNum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        Part5 part5 = new Part5();
        System.out.println(part5.isNumber("1"));
    }
}
