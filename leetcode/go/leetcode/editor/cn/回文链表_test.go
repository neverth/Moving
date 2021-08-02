package cn

import (
	"testing"
)

//编写一个函数，检查输入的链表是否是回文的。
//
//
//
// 示例 1：
//
// 输入： 1->2
//输出： false
//
//
// 示例 2：
//
// 输入： 1->2->2->1
//输出： true
//
//
//
//
// 进阶：
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 栈 递归 链表 双指针
// 👍 76 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func isPalindrome(head *ListNode) bool {
	p1 := head
	var recursivelyCheck func(p2 *ListNode) bool
	recursivelyCheck = func(p2 *ListNode) bool {
		if p2 == nil {
			return true
		}
		if !recursivelyCheck(p2.Next) {
			return false
		}
		if p1.Val != p2.Val {
			return false
		}
		p1 = p1.Next
		return true
	}
	return recursivelyCheck(p1)
}

//leetcode submit region end(Prohibit modification and deletion)

func Test面试题02_06(t *testing.T) {

}
