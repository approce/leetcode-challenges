package com.rzaiats.leetcode;
//There are N students in a class. Some of them are friends, while some are not.
// Their friendship is transitive in nature. For example, if A is a direct friend
//of B, and B is a direct friend of C, then A is an indirect friend of C. And we d
//efined a friend circle is a group of students who are direct or indirect friends
//.
//
// Given a N*N matrix M representing the friend relationship between students in
// the class. If M[i][j] = 1, then the ith and jth students are direct friends wit
//h each other, otherwise not. And you have to output the total number of friend c
//ircles among all the students.
//
// Example 1:
//
//
//Input:
//[[1,1,0],
// [1,1,0],
// [0,0,1]]
//Output: 2
//Explanation:The 0th and 1st students are direct friends, so they are in a frie
//nd circle.
//The 2nd student himself is in a friend circle. So return 2.
//
//
//
//
// Example 2:
//
//
//Input:
//[[1,1,0],
// [1,1,1],
// [0,1,1]]
//Output: 1
//Explanation:The 0th and 1st students are direct friends, the 1st and 2nd stude
//nts are direct friends,
//so the 0th and 2nd students are indirect friends. All of them are in the same
//friend circle, so return 1.
//
//
//
//
// Constraints:
//
//
// 1 <= N <= 200
// M[i][i] == 1
// M[i][j] == M[j][i]
//
// Related Topics Depth-first Search Union Find
// 👍 2356 👎 161


/*
[1,1,0,0,0,0,0,1,0,0,0,0,0,0,0],
[1,1,0,0,0,0,0,0,0,0,0,0,0,0,0],
[0,0,1,0,0,0,0,0,0,0,0,0,0,0,0],
[0,0,0,1,0,1,1,0,0,0,0,0,0,0,0],
[0,0,0,0,1,0,0,0,0,1,1,0,0,0,0],
[0,0,0,1,0,1,0,0,0,0,1,0,0,0,0],
[0,0,0,1,0,0,1,0,1,0,0,0,0,1,0],
[1,0,0,0,0,0,0,1,1,0,0,0,0,0,0],
[0,0,0,0,0,0,1,1,1,0,0,0,0,1,0],
[0,0,0,0,1,0,0,0,0,1,0,1,0,0,1],
[0,0,0,0,1,1,0,0,0,0,1,1,0,0,0],
[0,0,0,0,0,0,0,0,0,1,1,1,0,0,0],
[0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],
[0,0,0,0,0,0,1,0,1,0,0,0,0,1,0],
[0,0,0,0,0,0,0,0,0,1,0,0,0,0,1]]

[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 5, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 9, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 10, 6, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 10, 8, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 10, 13, 7, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 10, 13, 8, 8, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 10, 13, 8, 13, 9, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 10, 13, 8, 13, 11, 10, 11, 12, 13, 14]
[7, 1, 2, 6, 10, 10, 13, 8, 13, 14, 10, 11, 12, 13, 14]

14
[1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
13
[7, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
12
[7, 1, 2, 5, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
11
[7, 1, 2, 6, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
10
[7, 1, 2, 6, 9, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
9
[7, 1, 2, 6, 10, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
8
[7, 1, 2, 6, 10, 10, 6, 7, 8, 9, 10, 11, 12, 13, 14]
7
[7, 1, 2, 6, 10, 10, 8, 7, 8, 9, 10, 11, 12, 13, 14]
6
[7, 1, 2, 6, 10, 10, 13, 7, 8, 9, 10, 11, 12, 13, 14]
5
[7, 1, 2, 6, 10, 10, 13, 8, 8, 9, 10, 11, 12, 13, 14]
4
[7, 1, 2, 6, 10, 10, 13, 8, 13, 9, 10, 11, 12, 13, 14]
3
[7, 1, 2, 6, 10, 10, 13, 8, 13, 11, 10, 11, 12, 13, 14]
2
[7, 1, 2, 6, 10, 10, 13, 8, 13, 14, 10, 11, 12, 13, 14]
1
[7, 1, 2, 6, 10, 10, 13, 8, 13, 14, 11, 11, 12, 13, 14]


 */

import java.util.Arrays;

public class FriendCircles_547 {

    public static void main(String[] args) {
        int[][] friendshipMatrix = new int[3][3];
        friendshipMatrix[0] = new int[]{1, 1, 1};
        friendshipMatrix[1] = new int[]{1, 1, 1};
        friendshipMatrix[2] = new int[]{1, 1, 1};

        System.out.println(new Solution().findCircleNum(friendshipMatrix));
    }

    static class Solution {

        static class UnionFind {
            int[] grid;
            int[] weights;
            int   componentsCount;

            public UnionFind(int size) {
                grid = new int[size];
                weights = new int[size];
                componentsCount = size;
                for (int i = 0; i < size; i++) {
                    grid[i] = i;
                    weights[i] = 0;
                }
            }

            int findRoot(int el) {
                while (grid[el] != el) {
                    grid[el] = grid[grid[el]]; //path compression
                    el = grid[el];
                }

                return el;
            }

            void union(int el1, int el2) {
                int roo1 = findRoot(el1);
                int root2 = findRoot(el2);
                if (roo1 == root2) {
                    return;
                }

                componentsCount--;

                if (weights[roo1] > weights[root2]) {
                    weights[roo1] += weights[root2];
                    grid[root2] = grid[roo1];
                } else {
                    weights[root2] += weights[roo1];
                    grid[roo1] = grid[root2];
                }
            }

            public int getComponentsCount() {
                return Math.max(componentsCount, 1);
            }
        }

        public int findCircleNum(int[][] M) {
            if (M.length == 0) {
                return 0;
            }

            UnionFind unionFind = new UnionFind(M.length);

            for (int p1 = 0; p1 < M.length; p1++) {
                for (int p2 = p1 + 1; p2 < M.length; p2++) {

                    if (M[p1][p2] == 1) {
                        unionFind.union(p1, p2);
                    }
                }
            }

            return unionFind.getComponentsCount();
        }
    }
}
