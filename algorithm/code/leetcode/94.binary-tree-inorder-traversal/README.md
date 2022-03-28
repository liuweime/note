# binary-tree-inorder-traversal | 二叉树的中序遍历

## 题目

给定一个二叉树，返回它的中序遍历

## 分析

二叉树的中序遍历，了解一下中序遍历的概念，可以用递归方式很容易写出来（左、根、右）

**递归方式**

中序遍历的规则是先左节点，再根节点，最后右节点，那么递归就只要左节点不为null，就递归下去；根节点值直接取出；再递归右节点的值即可。



**迭代方式**

迭代方式与递归类型，需要主动维护迭代前的节点数据，即循环左节点时，把当前节点入栈；循环到叶子节点，将节点出栈，就是上一个节点，也是当前的根节点，出栈后循环右节点

## 代码

**递归方式**

```Go
func inorderTraversal(root *TreeNode) []int {
  if root == nil {
    return []int{}
  }
  var data []int
  mid(root, &data)
  return data
}

func mid(root *TreeNode, data *[]int) {
  if root == nil {
    return
  }
  if root.Left != nil {
    mid(root.Left, data)
  }
  *data = append(*data, root.Val)
  if root.Right != nil {
    mid(root.Right, data)
  }
}
```

**迭代方式**

```go
func inorderTraversal(root *TreeNode) []int {
  stock := []*TreeNode{}
  data := make([]int, 0)
  for root != nil || len(stock) > 0 {
    // 遍历左侧节点
    for root != nil {
      // 节点入栈
      stock = append(stock, root)
      root = root.Left
    }
    // 到这里 是左侧遍历结束
    // 弹出节点 就当前节点根节点
    root = stock[len(stock) - 1]
    stock = stock[:len(stock)-1]
    data = append(data, root.Val)
    // 右侧遍历 
    root = root.Right
  }
  return
}
```

