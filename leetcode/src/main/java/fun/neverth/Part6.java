package fun.neverth;

import java.util.PriorityQueue;

/**
 * @author neverth.li
 * @date 2020/9/9 17:43
 */
public class Part6 {
    static class MaxHeap {

        private final int[] data;

        public MaxHeap(int[] data) {
            this.data = data;
            buildHeap();
        }

        // 获取对中的最大的元素，根元素
        public int getRoot() {
            return data[0];
        }

        // 替换根元素，并重新堆调整
        public void setRoot(int root) {
            data[0] = root;
            heapify(0);
        }

        // 将数组转换成最大堆
        private void buildHeap() {
            // 根据性质，
            // 完全二叉树只有数组下标小于或等于 (data.length) / 2 - 1 的元素有孩子结点
            // 对有孩子结点的元素堆调整，从下往上调整
            for (int i = (data.length) / 2 - 1; i >= 0; i--) {
                heapify(i);
            }
        }

        private void heapify(int i) {
            // 获取左右结点的数组下标
            int l = ((i + 1) << 1) - 1;
            int r = (i + 1) << 1;
            // 临时变量，表示 跟结点、左结点、右结点中最大的值的结点的下标
            int max = i;
            // 左结点的值大于根结点的值
            if (l < data.length && data[l] > data[i]) {
                max = l;
            }
            // 右结点的值大于以上比较的较大值
            if (r < data.length && data[r] > data[max]) {
                max = r;
            }
            // 左右结点的值都小于根节点，直接return，不做任何操作
            if (i == max) {
                return;
            }
            // 交换根节点和左右结点中最大的那个值，把根节点的值替换下去
            swap(i, max);
            // 由于替换后左右子树会被影响，所以要对受影响的子树再进行堆调整
            heapify(max);
        }

        // 交换元素位置
        private void swap(int i, int j) {
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] topArr = new int[k];
        // 构造长度为 K 的最大堆
        if (k >= 0) System.arraycopy(arr, 0, topArr, 0, k);
        MaxHeap maxHeap = new MaxHeap(topArr);
        // 遍历元素小于最大堆的时候替换元素并调整堆
        for (int i = k; i < arr.length; i++) {
            if (maxHeap.getRoot() > arr[i]) {
                maxHeap.setRoot(arr[i]);
            }
        }
        return topArr;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * <p>
     * 库函数
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        int v[] = new int[k];
        // 最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int j : arr) {
            pq.offer(j);
        }
        for (int i = 0; i < k; i++) {
            v[i] = pq.poll();
        }
        return v;
    }

    public static void main(String[] args) {
        Part6 part6 = new Part6();
        int[] a = {0, 1, 1, 2, 4, 4, 1, 3, 3, 2};
        part6.getLeastNumbers1(a, 6);
    }
}
