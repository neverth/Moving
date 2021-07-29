package cn

import "testing"

//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
//
// 进阶：
//
//
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
//
//
// 示例 3：
//
//
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
//
//
// 示例 4：
//
//
//输入：head = [1], k = 1
//输出：[1]
//
//
//
//
//
// 提示：
//
//
// 列表中节点的数量在范围 sz 内
// 1 <= sz <= 5000
// 0 <= Node.val <= 1000
// 1 <= k <= sz
//
// Related Topics 递归 链表
// 👍 1206 👎 0
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

func reverseK(list *ListNode, k int) (*ListNode, *ListNode) {
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

func reverseKGroup(head *ListNode, k int) *ListNode {
	hair := &ListNode{Next: head}
	// 每个区间的头，链表的遍历指针
	partHair, p1 := hair, hair

	for p1.Next != nil {
		// 找到下一个区间的真实头
		for i := 0; i < k; i++ {
			p1 = p1.Next
			if p1 == nil {
				return hair.Next
			}
		}
		nextPart := p1.Next
		// 反转链表，包括入参
		h, t := reverseK(partHair.Next, k)
		// 连接链表
		partHair.Next = h
		t.Next = nextPart
		// 开始处理下一个区间
		partHair = t
		// 指针被扰乱，重新赋值
		p1 = t
	}
	return hair.Next
}

//leetcode submit region end(Prohibit modification and deletion)

func TestA(t *testing.T) {
	reverseKGroup(&ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 2,
		},
	}, 2)
}
