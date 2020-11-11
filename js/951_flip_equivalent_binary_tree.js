//For a binary tree T, we can define a flip operation as follows: choose any nod
//e, and swap the left and right child subtrees.
//
// A binary tree X is flip equivalent to a binary tree Y if and only if we can m
//ake X equal to Y after some number of flip operations.
//
// Given the roots of two binary trees root1 and root2, return true if the two t
//rees are flip equivelent or false otherwise.
//
//
// Example 1:
//
//
//Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,nul
//l,null,null,null,8,7]
//Output: true
//Explanation: We flipped at nodes with values 1, 3, and 5.
//
//
// Example 2:
//
//
//Input: root1 = [], root2 = []
//Output: true
//
//
// Example 3:
//
//
//Input: root1 = [], root2 = [1]
//Output: false
//
//
// Example 4:
//
//
//Input: root1 = [0,null,1], root2 = []
//Output: false
//
//
// Example 5:
//
//
//Input: root1 = [0,null,1], root2 = [0,1]
//Output: true
//
//
//
// Constraints:
//
//
// The number of nodes in each tree is in the range [0, 100].
// Each tree will have unique node values in the range [0, 99].
//
// Related Topics Tree
// 👍 814 👎 45

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */


const {buildFromArray} = require('./utils/binaryTreeBuilder');

//clean version from solution
/**
 * @param {TreeNode} root1
 * @param {TreeNode} root2
 * @return {boolean}
 */
const flipEquiv2 = function(root1, root2) {
  if (!root1 && !root2) {
    return true;
  }

  if (root1 === null || root2 === null || root1.val !== root2.val) {
    return false;
  }

  return (flipEquiv2(root1.left, root2.left) && flipEquiv2(root1.right, root2.right))
    || (flipEquiv2(root1.left, root2.right) && flipEquiv2(root1.right, root2.left));
};

//my solution
const flipEquiv = function(root1, root2) {
  if (!root1 && !root2) {
    return true;
  }

  if (!root1 || !root2 || root1.val !== root2.val) {
    return false;
  }

  const isTr1LeftNodeEmpty  = root1.left === null;
  const isTr1RightNodeEmpty = root1.right === null;

  const isTr2LeftNodeEmpty  = root2.left === null;
  const isTr2RightNodeEmpty = root2.right === null;

  const isNotEvenEmptyNodes = [isTr1RightNodeEmpty, isTr1LeftNodeEmpty, isTr2LeftNodeEmpty, isTr2RightNodeEmpty].filter(val => val === true).length % 2 === 1;

  if (isTr1LeftNodeEmpty && isTr1RightNodeEmpty && isTr2LeftNodeEmpty && isTr2RightNodeEmpty) {
    return true;
  }

  if (isNotEvenEmptyNodes) {
    return false;
  }

  if (isTr1LeftNodeEmpty && isTr2LeftNodeEmpty) {

    return flipEquiv(root1.right, root2.right);

  } else if (isTr1RightNodeEmpty && isTr2RightNodeEmpty) {

    return flipEquiv(root1.left, root2.left);

  } else if (isTr1LeftNodeEmpty && isTr2RightNodeEmpty) {
    //flip case

    return flipEquiv(root1.right, root2.left);

  } else if (isTr1RightNodeEmpty && isTr2LeftNodeEmpty) {
    //flip case

    return flipEquiv(root1.left, root2.right);

  }

  if (
    root1.left.val === root2.left.val
    && root1.right.val === root2.right.val
  ) {

    return flipEquiv(root1.left, root2.left) & flipEquiv(root1.right, root2.right);

  } else if (
    root1.left.val === root2.right.val
    && root1.right.val === root2.left.val
  ) {

    return flipEquiv(root1.left, root2.right) & flipEquiv(root1.right, root2.left);

  }

  return false;
};

(async () => {
  const result =
          flipEquiv2(
            buildFromArray([1,2,3,4,5,6,null,null,null,7,8]),
            buildFromArray([1,3,2,null,6,4,5,null,null,null,null,8,7]));

  console.log(result);
})().catch(e => console.error(e));
