/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
  var map = {};

  for (let i = 0; i < nums.length; i++) {
    const requiredComplementIndex = map[target - nums[i]];

    if (requiredComplementIndex !== undefined) {
      return [i, requiredComplementIndex];
    }

    map[nums[i]] = i;
  }
};

console.log(twoSum([2, 7, 11, 15], 9));
