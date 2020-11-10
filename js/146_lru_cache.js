//Design a data structure that follows the constraints of a Least Recently Used
//(LRU) cache.
//
// Implement the LRUCache class:
//
//
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//
// int get(int key) Return the value of the key if the key exists, otherwise ret
//urn -1.
// void put(int key, int value) Update the value of the key if the key exists. O
//therwise, add the key-value pair to the cache. If the number of keys exceeds the
// capacity from this operation, evict the least recently used key.
//
//
// Follow up:
//Could you do get and put in O(1) time complexity?
//
//
// Example 1:
//
//
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
//
//
//
// Constraints:
//
//
// 1 <= capacity <= 3000
// 0 <= key <= 3000
// 0 <= value <= 104
// At most 3 * 104 calls will be made to get and put.
//
// Related Topics Design
// 👍 7021 👎 291

/**
 * @param {number} capacity
 */
var LRUCache = function(capacity) {
  this.map       = {};
  this.capacity  = capacity;
  this.size      = 0;
  this.head      = new Entry(null);
  this.tail      = new Entry(null);
  this.head.prev = this.tail;
  this.head.next = null;

  this.tail.next = this.head;
  this.tail.prev = null;
};

/**
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function(key) {
  const entry = this.map[key];
  if (!entry) {
    return -1;
  }

  this.updateNodeRank(entry);

  return entry.value;
};

/**
 * @param {number} key
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function(key, value) {
  const previousEntry = this.map[key];
  if (previousEntry) {
    previousEntry.removeNode();
    this.size--;
  }

  if (this.size === this.capacity) {
    this.removeLeastUsed();

    this.size--;
  }

  this.size++;

  const entry = new Entry(key, value);

  this.addNode(entry);

  this.map[key] = entry;
};

LRUCache.prototype.removeLeastUsed = function() {
  const removeTarget = this.tail.next;
  if (removeTarget) {
    removeTarget.removeNode();
  }
  delete this.map[removeTarget.key];
};

LRUCache.prototype.updateNodeRank = function(node) {
  node.removeNode();

  this.addNode(node);
};

LRUCache.prototype.addNode = function(node) {
  node.next           = this.head;
  this.head.prev.next = node;

  node.prev      = this.head.prev;
  this.head.prev = node;
};

function Entry(key, value) {
  this.key   = key;
  this.value = value;
  this.next  = this;
  this.prev  = this;
}

Entry.prototype.removeNode = function() {
  this.prev.next = this.next;
  this.next.prev = this.prev;
};

const lruCache = new LRUCache(2);

lruCache.put(2, 6);
lruCache.put(1, 5);
lruCache.put(1, 2);
lruCache.get(1);
lruCache.get(2);
console.log(lruCache.map);

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
//leetcode submit region end(Prohibit modification and deletion)
