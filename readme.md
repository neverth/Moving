# Moving
学习总结

## leetCode刷题总结
**淦**！！！他瞄的太难受了，🤬🤬🤬自己，敲过几遍的题目面试的还是还是还是敲不出来。

为了代码简洁，省略了一些异常处理。

### JZ 03. 数组中重复的数字

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```

限制：

```
2 <= n <= 100000
```

#### 解法一（排序）

直接排序，之后遍历前一个元素跟后一个元素是否相等，相等直接返回元素

```java
// 时间复杂度 O(nlogn)
public int findRepeatNumber(int[] nums) {
    Arrays.sort(nums);
    for(int i = 0; i < nums.length - 1; i++){
        if(nums[i] == nums[i + 1]){
            return nums[i];
        }
    }
    return -1;
}
```

#### 解法二（HASH）

把元素一个个的加入`hashSet`集合中，加入有重复则直接返回

```java
// 时间复杂度O(n)
public int findRepeatNumber(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int num : nums) {
        if (!set.add(num)) {
            return num;
        }
    }
    return -1;
}
```

#### 解法三（原地置换）

因为长度为 `n` 且所有数字都在 `0 到 n - 1`之间，所以如果没有重复数字，那么正常排序后，数字就在对应下标的地方。因此遍历数组，如果不对应则交换到对应的位置，如果对应位置已经有对应的数字则发生重复。

```java
// 时间复杂度O(n)，空间复杂度O(1)
public int findRepeatNumber(int[] nums) {
    for(int i = 0; i < nums.length; i++){
        // 不相等则一直交换，知道出现重复或者相等。
        while(nums[i] != i){
            if(nums[i] == nums[nums[i]]){
                return nums[i];
            }
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
    }
    return -1;
}
```

### JZ 04. 二维数组中的查找

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

示例:

现有矩阵 matrix 如下：

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

给定 target = `5`，返回 `true`。

给定 target = `20`，返回 `false`。

限制：

```
0 <= n <= 1000
0 <= m <= 1000
```

#### 解法一 （线性查找）

从数组**右上角**开始查找数组，如果元素大于目标值，`j--`，如果小于 `i++`，依次循环直至找到这个数。

```java
// 时间复杂度O(m + n)
public boolean find(int[][] matrix, int target) {
    int i = 0, j = matrix[0].length - 1;
    
    while(i < matrix.length && j >= 0){
        if(matrix[i][j] > target){
            j--;
        }else if(matrix[i][j] < target){
            i++;
        }else{
            return true;
        }
    }
    return false;
}
```

### JZ 05. 替换空格

请实现一个函数，把字符串 `s` 中的每个空格替换成`"%20"`。

示例 1：

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

**限制：**

```
0 <= s 的长度 <= 10000
```

#### 解法一（常规）

直接StringBuilder就OK。

时间复杂度O(n)

#### 解法二（逆向思维）

先遍历一般数组记录空格的个数，然后创建对应长度的字符数组

时间复杂度O(n)

### JZ 06. 从尾到头打印链表

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：

```
输入：head = [1,3,2]
输出：[2,3,1]
```

限制：

```
0 <= 链表长度 <= 10000
```

#### 解法一（递归法）

递归到链表末端，回溯时依次将节点值加入列表。 

```java
// 时间复杂度O(n)
ArrayList<Integer> res = new ArrayList<>();

public int[] reversePrint(ListNode head) {
    reverse(head);
    int[] a = new int[res.size()];
    for(int i = 0; i < a.length; i++)
        a[i] = res.get(i);
    return a;
}
public void reverse(ListNode head){
    if(head != null){
        // 先递归再输出
        reverse(head.next);
        res.add(head.val);
    }
}
```

#### 解法二（辅助栈）

从头到尾遍历链表，并用一个栈存储每个结点的值，之后出栈输出值即可。

```java
// 时间复杂度O(n)
public int[] reversePrint(ListNode head) {
    LinkedList<Integer> stack = new LinkedList<>();
    while (head != null) {
        stack.addLast(head.val);
        head = head.next;
    }
    int[] res = new int[stack.size()];
    for (int i = 0; i < res.length; i++)
        res[i] = stack.removeLast();
    return res;
}
```

### JZ 07. 重建二叉树

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。从下往上的构建过程。

例如，给出

```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：

```
    3
   / \
  9  20
    /  \
   15   7
```

**限制：**

```
0 <= 节点个数 <= 5000
```

#### 解法一（递归）

先序遍历的第一个节点就是根节点，在中序遍历中通过根节点区分左右子树树，根节点的左右节点代表着范围更小的对应先序遍历和中序遍历的递归过程。

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0) {
        return null;
    }
    // 前序遍历的第一个位置就是根节点
    TreeNode root = new TreeNode(preorder[0]);
    // TODO: 这里为了简洁，可以通过HASH
    for (int i = 0; i < preorder.length; i++) {
        // 在中序遍历中找到与根节点对应的位置
        if (preorder[0] == inorder[i]) {
            root.left = buildTree(
                // copyOfRange对应的范围 [a, b)
                Arrays.copyOfRange(preorder, 1, i + 1),
                Arrays.copyOfRange(inorder, 0, i)
            );
            root.right = buildTree(
                // copyOfRange对应的范围 [a, b)
                Arrays.copyOfRange(preorder, i + 1, preorder.length),
                Arrays.copyOfRange(inorder, i + 1, inorder.length)
            );
            break;
        }
    }
    return root;
}
```

先序遍历的第一个节点就是根节点，直接创建根节点，然后在中序遍历中通过根节点区分左右子树树，根的左右节点就分别等于缩小范围的跟的左节点与左子树和跟的右节点与右子树的递归调用，从下往上的构建过程。

```java
// 时间复杂度O(n)
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
    // 根节点的左子树就是根的左节点与中序遍历缩小范围的递归过程
    root.left = recursive(pre_root_idx + 1, in_left_idx, idx - 1);
    // 根节点的右子树就是根的右节点与中序遍历缩小范围的递归过程
    root.right = recursive(
        // 根节点的右节点 = 根节点的下一个节点 + 左子树的长度
        pre_root_idx + 1 + (idx - in_left_idx), 
        idx + 1, in_right_idx
    );
	// 返回已经构建好的根节点
    return root;
}
```

#### 解法二（迭代）

使用栈保存**右节点还没有赋值**的节点，通过前序遍历两个相邻节点的关系来判断，其存在两种关系

* 后一个节点是前一个节点的左节点，直接赋值。因为前序遍历和后续遍历相对应不等
* 前节点不存在左节点，后节点是**已经入栈节点的右节点**。将已经入栈的节点出栈与中序遍历节点比较，如果相等对应了中序遍历节点没有右节点的过程，跳过，不相等则代表前序遍历后节点就是这个出栈节点的右节点。

就这样循环遍历前序遍历两两相邻节点，根据中序遍历匹配两种不同的情况就可以重建二叉树。从上往下的构建过程。

```java
// 时间复杂度O(n)
public TreeNode buildTree1(int[] preorder, int[] inorder) {
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
```

### JZ 09. 用两个栈实现队列

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

示例 1：

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

示例 2：

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

**提示：**

- `1 <= values <= 10000`
- `最多会对 appendTail、deleteHead 进行 10000 次调用`

#### 解法一