package cn

import "testing"

//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
//
//
// 示例 2：
//
//
//输入：head = [5], left = 1, right = 1
//输出：[5]
//
//
//
//
// 提示：
//
//
// 链表中节点数目为 n
// 1 <= n <= 500
// -500 <= Node.val <= 500
// 1 <= left <= right <= n
//
//
//
//
// 进阶： 你可以使用一趟扫描完成反转吗？
// Related Topics 链表
// 👍 970 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func re(list *ListNode, k int) (*ListNode, *ListNode) {
	var p1, p2, p3 *ListNode
	p2 = list
	for i := 0; i < k; i++ {
		p3 = p2.Next
		p2.Next = p1
		p1 = p2
		p2 = p3
	}
	return p1, list
}

func reverseBetween(head *ListNode, left int, right int) *ListNode {
	hari := &ListNode{Next: head}

	leftP, rightP := hari, hari
	for i := 0; i < left-1; i++ {
		leftP = leftP.Next
	}

	rightP = leftP
	for i := 0; i < right-left+2; i++ {
		rightP = rightP.Next
	}

	h, t := re(leftP.Next, right-left+1)
	leftP.Next = h
	t.Next = rightP
	return hari.Next
}

//leetcode submit region end(Prohibit modification and deletion)
func Test92(t *testing.T) {
	reverseBetween(&ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 2,
			Next: &ListNode{
				Val: 3,
				Next: &ListNode{
					Val: 4,
					Next: &ListNode{
						Val: 5,
					},
				},
			},
		},
	}, 2, 4)
}
