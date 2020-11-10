function TreeNode(val, left, right) {
  this.val   = (val === undefined ? 0 : val);
  this.left  = (left === undefined ? null : left);
  this.right = (right === undefined ? null : right);
}

//[1,2,3,4,5,6,null,null,null,7,8]
// [1,3,2,null,6,4,5,null,null,null,null,8,7]
const buildFromArray = (array) => {
  if (!array.length || array[0] === null) {
    return null;
  }
  const root = new TreeNode(array.shift());

  let children = [root];

  while (children.length > 0) {
    let currentChild = children.shift();

    if (array.length > 0) {
      const leftValue = array.shift();

      if (leftValue) {
        const treeNode    = new TreeNode(leftValue);
        currentChild.left = treeNode;
        children.push(treeNode);
      }
    }

    if (array.length > 0) {
      const rightValue = array.shift();

      if (rightValue) {
        const treeNode     = new TreeNode(rightValue);
        currentChild.right = treeNode;
        children.push(treeNode);
      }
    }
  }

  return root;
};

function NAryNode(val, children) {
  this.val      = val;
  this.children = children;
};

//[1,null,3,2,4,null,5,6]
const nAryTreeSeparatedByNulls = (array) => {
  if (array === null || !array.length) {
    return null;
  }

  const root = new NAryNode(array.shift(), []);

  array.shift();

  const children = [root];
  while (array.length > 0) {

    const currentChild = children.shift();

    while (true) {
      const value = array.shift();

      if (!value) {
        break;
      }

      const node = new NAryNode(value, []);
      children.push(node);
      currentChild.children.push(node);
    }
  }

  return root;
};

module.exports = {buildFromArray, nAryTreeSeparatedByNulls};
