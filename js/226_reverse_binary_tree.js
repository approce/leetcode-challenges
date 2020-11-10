//Invert a binary tree.
//
// Example:
//
// Input:
//
//
//     4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//
// Output:
//
//
//     4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//
// Trivia:
//This problem was inspired by this original tweet by Max Howell:
//
// Google: 90% of our engineers use the software you wrote (Homebrew), but you c
//an’t invert a binary tree on a whiteboard so f*** off.
// Related Topics Tree
// 👍 4151 👎 69

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */

function TreeNode(val, left, right) {
  this.val   = (val === undefined ? 0 : val);
  this.left  = (left === undefined ? null : left);
  this.right = (right === undefined ? null : right);
}

/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var invertTree = function(root) {
  if (!root) {
    return root;
  }

  let buuf   = root.left;
  root.left  = root.right;
  root.right = buuf;

  invertTree(root.left);
  invertTree(root.right);

  return root;
};
//leetcode submit region end(Prohibit modification and deletion)
