var MAX = Math.pow(2, 31);

/**
 * @param {number} x
 * @return {number}
 */
var reverse = function(x) {

  let reversedInt = 0;
  while (parseInt(x) !== 0) {
    let popped;

    if (Math.abs(x) < 10) {
      popped = parseInt(x);
    } else {
      popped = parseInt(x % 10);
    }

    if (Math.abs(reversedInt) > MAX / 10) {
      return 0;
    }

    if (reversedInt === 0) {
      reversedInt = popped;
    } else {
      reversedInt = reversedInt * 10 + popped;
    }

    x = x / 10;
  }

  return reversedInt;
};

console.log(reverse(-123));
