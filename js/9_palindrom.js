//Determine whether an integer is a palindrome. An integer is a palindrome when
//it reads the same backward as forward.
//
// Follow up: Could you solve it without converting the integer to a string?
//
//
// Example 1:
//
//
//Input: x = 121
//Output: true
//
//
// Example 2:
//
//
//Input: x = -121
//Output: false
//Explanation: From left to right, it reads -121. From right to left, it becomes
// 121-. Therefore it is not a palindrome.
//
//
// Example 3:
//
//
//Input: x = 10
//Output: false
//Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
//
//
// Example 4:
//
//
//Input: x = -101
//Output: false
//
//
//
// Constraints:
//
//
// -231 <= x <= 231 - 1
//
// Related Topics Math
// 👍 2730 👎 1636

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * @param {number} x
 * @return {boolean}
 */
var isPalindrome = function(x) {
  if (x < 0) {
    return false;
  }

  if (x === 0) {
    return true;
  }

  var digitsCount = 1;

  while (x >= Math.pow(10, digitsCount)) {
    digitsCount++;
  }

  function getDigitFromNumber(number, numberDigitsCount, indexFromRight) {
    return Math.trunc((x % Math.pow(10, indexFromRight + 1)) / Math.pow(10, indexFromRight));
  }

  for (let i = 0; i < Math.floor(digitsCount / 2); i++) {
    const j = digitsCount - i - 1;

    const rightDigit = getDigitFromNumber(x, digitsCount, i);
    const leftDigit  = getDigitFromNumber(x, digitsCount, j);

    if (rightDigit !== leftDigit) {
      return false;
    }
  }

  return true;
};
console.log(isPalindrome(10));
//leetcode submit region end(Prohibit modification and deletion)
