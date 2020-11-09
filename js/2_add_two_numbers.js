//You are given two non-empty linked lists representing two non-negative integer
//s. The digits are stored in reverse order, and each of their nodes contains a si
//ngle digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself.
//
//
// Example 1:
//
//
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
//
//
// Example 2:
//
//
//Input: l1 = [0], l2 = [0]
//Output: [0]
//
//
// Example 3:
//
//
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
//
//
//
// Constraints:
//
//
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have leading
// zeros.
//
// Related Topics Linked List Math
// 👍 9705 👎 2441

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */

class ListNode {

  constructor(val, next) {
    this.val  = (val === undefined ? 0 : val);
    this.next = (next === undefined ? null : next);
  }
}

var addTwoNumbers = function(l1, l2) {
  let rootNode = undefined;
  let lastNode = undefined;

  let overflow = false;

  while (true) {

    let sum = 0;

    if (l1 && l2) {
      sum = l1.val + l2.val;
    } else if (l1 || l2) {
      sum = (l1 || l2).val;
    }

    if (overflow) {
      sum += 1;
      overflow = false;
    }

    if (sum > 9) {
      sum      = sum % 10;
      overflow = true;
    }

    let node = new ListNode(sum);

    if (rootNode === undefined) {
      rootNode = node;
      lastNode = node;
    } else {
      lastNode.next = node;
      lastNode      = node;
    }

    node.val = sum;

    if ((l1 && l1.next) || (l2 && l2.next) || overflow) {
      l1 = l1 && l1.next;
      l2 = l2 && l2.next;
    } else {
      break;
    }
  }

  return rootNode;
};

const l  = new ListNode(2, new ListNode(4, new ListNode(3)));
const l3 = new ListNode(5, new ListNode(6, new ListNode(4)));
console.log(addTwoNumbers(l, l3));
//leetcode submit region end(Prohibit modification and deletion)
