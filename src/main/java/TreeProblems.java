package main.java;

import java.util.LinkedList;
import java.util.Queue;
import main.java.tree.Node;

public class TreeProblems {

/********************************************************************************************/

  /* Given two trees, return true if they are
       structurally identical */
  boolean identicalTrees(Node a, Node b) {
    /*1. both empty */
    if (a == null && b == null)
      return true;

    /* 2. both non-empty -> compare them */
    if (a != null && b != null)
      return (a.data == b.data
          && identicalTrees(a.left, b.left)
          && identicalTrees(a.right, b.right));

    /* 3. one empty, one not -> false */
    return false;
  }

/********************************************************************************************/

  /* computes number of nodes in tree */
  int size(Node node)
  {
    if (node == null)
      return 0;
    else
      return(size(node.left) + 1 + size(node.right));
  }
/********************************************************************************************/
  /*
     Given a tree and a sum, return true if there is a path from the root
     down to a leaf, such that adding up all the values along the path
     equals the given sum.

     Strategy: subtract the node value from the sum when recurring down,
     and check to see if the sum is 0 when you run out of tree.
   */

  boolean haspathSum(Node node, int sum)
  {
    if (node == null)
      return (sum == 0);
    else
    {
      boolean ans = false;

      /* otherwise check both subtrees */
      int subsum = sum - node.data;
      if (subsum == 0 && node.left == null && node.right == null)
        return true;
      if (node.left != null)
        ans = ans || haspathSum(node.left, subsum);
      if (node.right != null)
        ans = ans || haspathSum(node.right, subsum);
      return ans;
    }
  }

/********************************************************************************************/

  /* Function to find Lowest Common Ancestor of n1 and n2. The function assumes that both
       n1 and n2 are present in BST */
  Node lowestCommonAncestor(Node node, int n1, int n2)
  {
    if (node == null)
      return null;

    // If both n1 and n2 are smaller than root, then LCA lies in left
    if (node.data > n1 && node.data > n2)
      return lowestCommonAncestor(node.left, n1, n2);

    // If both n1 and n2 are greater than root, then LCA lies in right
    if (node.data < n1 && node.data < n2)
      return lowestCommonAncestor(node.right, n1, n2);

    return node;
  }
/********************************************************************************************/

  // Tree traversals

  public void postOrder(Node root) {
    if(root == null)return;
    postOrder(root.left); // first left
    postOrder(root.right); // and right
    System.out.print(root.data + " "); // then process
  }

  public void preOrder(Node root) {
    if(root == null)return;
    System.out.print(root.data + " "); // process first
    preOrder(root.left); // go to left
    preOrder(root.right); // then right
  }

  public void inOrder(Node root) {
    if(root == null)return;
    inOrder(root.left); // first left
    System.out.print(root.data + " "); // process
    inOrder(root.right); // then go to right
  }

  public void levelOrder(Node root) {
    if (root == null) return;
    Queue<Node> q = new LinkedList<>();
    q.add(root); // add first level to queue
    int nodeCountInLevel = 1;
    while (!q.isEmpty()) {
      Node x = q.remove();
      nodeCountInLevel--;
      if (x.left != null)
        q.add(x.left);
      if (x.right != null)
        q.add(x.right);

      // move to next level when all nodes are processed in current level
      if (nodeCountInLevel == 0 && !q.isEmpty()) {
        nodeCountInLevel += q.size();
        System.out.println(q);
      }
    }
  }

/********************************************************************************************/

  /*
  Invert Binary Tree

    Time Complexity is O(n), becuase each node is vitised only once.
    Space Complexity is O(n) because of recursion h (height) number of stack calls.
   */

  public Node invertTree(Node root) {
    if (root == null) return null;
    Node tempRight = root.right;
    root.right = invertTree(root.left);
    root.left = invertTree(tempRight);
    return root;
  }
/********************************************************************************************/

  /*
  Given two binary trees, check if they are structurally identical and the nodes have the same value.

    Time Complexity is O(n), becuase each node is vitised only once.
    Space Complexity is O(n) because of recursion h (height) number of stack calls.

   */
  boolean isSameTree(Node p, Node q) {
    // base case
    if(p == null || q == null)return p ==q ;
    // recursion, check if values are equal
    return p.data == q.data && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

/********************************************************************************************/

  /*
  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
  all the values along the path equals the given sum.
   */

  public boolean hasPathSum(Node root, int sum) {
    if(root == null)return false;
    sum-=root.data;
    if(sum == 0 && root.left == null && root.right == null) return true;
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
  }

/********************************************************************************************/
  /*
  Determine if binary tree is binary search tree
   */

  private static Boolean isBST(Node root)
  {
    //return if root is null
    //check if value of root is less than value of right child
    //check if value of root is greater than left child
    //
    if(root == null)
      return true;

    if(root.left != null)
    {
      if(!(root.left.data < root.data && isBST(root.left))
          || (root.left.data >= root.data))
        return false;
    }

    if(root.right != null)
    {
      if(!(root.right.data >= root.data && isBST(root.right))
          || (root.right.data < root.data))
        return false;
    }

    return true;
  }

/********************************************************************************************/

  /*
  Second largest element in binary search tree
   */

  private static int secondLargestElement(Node root)
  {
    if (root == null)
      return -1;

    return secondLargestElementUtil(root, Integer.MIN_VALUE);
  }

  private static int secondLargestElementUtil(Node root, int secondLargest)
  {
    if (root == null)
    {
      return secondLargest;
    }

    if (root.right != null)
    {
      secondLargest = root.data;
      secondLargestElementUtil(root.right, secondLargest);
    }
    else if (root.left != null && secondLargest < root.left.data)
    {
      //largest in left subtree
      secondLargest = largestElement(root.left);
    }

    return secondLargest;
  }

  private static int largestElement(Node root)
  {
    if (root == null)
      return 0;

    if (root.right != null)
      return largestElement(root.right);

    return root.data;
  }

/********************************************************************************************/
  /*
    Minimum hieght tree from sorted array
   */

  public Node buildMinimumHeightTree(int[] arr, int start, int end)
  {
    if (end < start)
      return null;

    int mid = (start + end) / 2;
    Node n = new Node(arr[mid]);

    n.left = buildMinimumHeightTree(arr, start, mid-1);
    n.right = buildMinimumHeightTree(arr, mid+1, end);

    return n;
  }

/********************************************************************************************/
}
