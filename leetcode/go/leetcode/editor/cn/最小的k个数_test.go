package cn

import "testing"

//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
//
//
// 示例 1：
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
//
//
// 示例 2：
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0]
//
//
//
// 限制：
//
//
// 0 <= k <= arr.length <= 10000
// 0 <= arr[i] <= 10000
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列）
// 👍 276 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

type MaxHeap struct {
	data []int
	k    int
}

func (m *MaxHeap) GetElems() []int {
	return m.data
}

// 上浮，这里写错了
func (m *MaxHeap) Push(v int) {
	if len(m.data) < m.k {
		m.data = append(m.data, v)
		var t1, t2 int
		t2 = m.data[0]
		for i := 0; i < len(m.data)-1; i++ {
			t1 = t2
			t2 = m.data[i+1]
			m.data[i+1] = t1
		}
	} else {
		if m.k == 0 || v >= m.data[0] {
			return
		}
	}
	m.data[0] = v
	m.heapify(0)
}

func (m *MaxHeap) heapify(i int) {
	// 找到其左右节点
	left := (i+1)*2 - 1
	right := (i + 1) * 2
	// 找到其左右节点和根节点的最大值
	maxIndex := i
	if left < len(m.data) && m.data[left] > m.data[maxIndex] {
		maxIndex = left
	}

	if right < len(m.data) && m.data[right] > m.data[maxIndex] {
		maxIndex = right
	}

	// 左右节点没有最大的，直接返回
	if i == maxIndex {
		return
	}
	m.swap(i, maxIndex)
	// 重新调整其左右子树
	m.heapify(maxIndex)
}

func (m *MaxHeap) swap(i, j int) {
	t := m.data[i]
	m.data[i] = m.data[j]
	m.data[j] = t
}

func getLeastNumbers(arr []int, k int) []int {
	maxHeap := MaxHeap{
		data: make([]int, 0),
		k:    k,
	}
	for _, v := range arr {
		maxHeap.Push(v)
	}
	return maxHeap.GetElems()
}

//leetcode submit region end(Prohibit modification and deletion)

func Test剑指Offer40(t *testing.T) {
	getLeastNumbers([]int{0, 1, 2, 3, 4, 0, 3, 3, 8, 1, 4, 6, 2, 8, 8, 15, 10, 0, 9, 9, 1, 2, 17, 8, 17, 25, 18, 18, 16, 13, 18, 29, 2, 3, 32, 2, 26, 23, 18, 8, 34, 8, 11, 36, 36, 39, 46, 30, 21, 25, 21, 14, 41, 10, 31, 55, 45, 16, 33, 47, 4, 52, 59, 60, 1, 43, 42, 10, 12, 56, 12, 27, 22, 52, 38, 12, 41, 42, 71, 5, 42, 76, 8, 3, 31, 65, 11, 29, 28, 68, 33, 50, 73, 87, 22, 68, 31, 1, 38, 89}, 60)
}
