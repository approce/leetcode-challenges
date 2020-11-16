package com.rzaiats.leetcode;

//Given an m x n 2d grid map of '1's (land) and '0's (water), return the number
//of islands.
//
// An island is surrounded by water and is formed by connecting adjacent lands h
//orizontally or vertically. You may assume all four edges of the grid are all sur
//rounded by water.
//
//
// Example 1:
//
//
//Input: grid = [
//  {'1','1','1','1','0},
//  {'1','1','0','1','0},
//  {'1','1','0','0','0},
//  {'0','0','0','0','0}
//]
//Output: 1
//
//
// Example 2:
//
//
//Input: grid = [
//  {'1','1','0','0','0},
//  {'1','1','0','0','0},
//  {'0','0','1','0','0},
//  {'0','0','0','1','1}
//]
//Output: 3
//
//
//
// Constraints:
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] is '0' or '1'.
//
// Related Topics Depth-first Search Breadth-first Search Union Find
// 👍 6905 👎 221


import java.util.HashSet;
import java.util.Set;

/*
[{'1','1','1','1','1','1','1'},
{'0','0','0','0','0','0','1'},
{'1','1','1','1','1','0','1'},
{'1','0','0','0','1','0','1'},
{'1','0','1','0','1','0','1'},
{'1','0','1','1','1','0','1'},
{'1','1','1','1','1','1','1'}

 */
public class NumberOfIslands_200 {

    public static void main(String[] args) {
        char[][] params = {
                {'1', '1', '1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '0', '0', '1', '0', '1'},
                {'1', '0', '1', '0', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '0', '1'},
                {'1', '1', '1', '1', '1', '1', '1'},
        };

        System.out.println(new Solution().numIslands(params));
    }

    static class Solution {

        int rowsCount;
        int columnsCount;

        class UnionFind {
            int[]        weights;
            char[][]     grid;
            Set<Integer> uniqueIslands = new HashSet<>();

            public UnionFind(char[][] grid) {
                this.grid = grid;

                weights = new int[rowsCount * columnsCount];

                for (int i = 0; i < rowsCount; i++) {
                    for (int j = 0; j < columnsCount; j++) {
                        weights[mapToOneDimensionIndex(i, j)] = 1;
                    }
                }
            }

            int createNewIsland(int row, int column) {
                int newIslandIndex = mapToOneDimensionIndex(row, column);
                grid[row][column] = (char) newIslandIndex;
                uniqueIslands.add(newIslandIndex);

                return newIslandIndex;
            }

            int find(int oneDimensionIndex) {

                while (true) {
                    Cell cell = mapToRowAndColumn(oneDimensionIndex);

                    int gridValue = grid[cell.row][cell.column];

                    if (gridValue == oneDimensionIndex) {
                        return oneDimensionIndex;
                    } else {
                        oneDimensionIndex = gridValue;
                    }
                }
            }

            int getUniqueIslandsCount() {
                return uniqueIslands.size();
            }

            void union(int oneDimensionIndex1, int oneDimensionIndex2) {
                int root1 = find(oneDimensionIndex1);
                int root2 = find(oneDimensionIndex2);

                if (root1 == root2) {
                    return;
                }

                Cell cell1 = mapToRowAndColumn(oneDimensionIndex1);
                Cell cell2 = mapToRowAndColumn(oneDimensionIndex2);

                if (weights[root1] > weights[root2]) {
                    uniqueIslands.remove(root2);
                    grid[cell2.row][cell2.column] = (char) root1;
                    weights[root1] = weights[root2] + weights[root1];
                } else {
                    uniqueIslands.remove(root1);
                    grid[cell1.row][cell1.column] = (char) root2;
                    weights[root2] = weights[root2] + weights[root1];
                }
            }
        }

        class Cell {
            private final int row;
            private final int column;

            Cell(int row, int column) {
                this.row = row;
                this.column = column;
            }
        }

        int mapToOneDimensionIndex(int row, int column) {
            return row * columnsCount + column;
        }

        Cell mapToRowAndColumn(int oneDimensionIndex) {
            int row = oneDimensionIndex / columnsCount;
            int column = oneDimensionIndex % columnsCount;

            return new Cell(row, column);
        }

        public int numIslands(char[][] grid) {
            rowsCount = grid.length;
            columnsCount = grid[0].length;

            UnionFind unionFind = new UnionFind(grid);

            for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
                char[] row = grid[rowIndex];

                for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                    if (row[columnIndex] == '0') {
                        row[columnIndex] = (char) -1;
                        continue;
                    }

                    int newIslandIndex = unionFind.createNewIsland(rowIndex, columnIndex);

                    char leftNeighbour = columnIndex == 0 ? '0' : row[columnIndex - 1];
                    char topNeighbour = rowIndex == 0 ? '0' : grid[rowIndex - 1][columnIndex];

                    if (leftNeighbour != '0' && leftNeighbour != (char) -1) {
                        unionFind.union(newIslandIndex, leftNeighbour);
                    }

                    if (topNeighbour != '0' && topNeighbour != (char) -1) {
                        unionFind.union(newIslandIndex, topNeighbour);
                    }
                }
            }

            return unionFind.getUniqueIslandsCount();
        }
    }
}
