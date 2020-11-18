package com.rzaiats.leetcode;
/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less
than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates_286 {
    public static void main(String[] args) {
        int[][] array = new int[4][4];

        array[0] = new int[]{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE};
        array[1] = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1};
        array[2] = new int[]{Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1};
        array[3] = new int[]{0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE};

        new Solution().wallsAndGates(array);

        System.out.println(Arrays.deepToString(array));
    }

    static class Solution {
        private static final int GATE       = 0;
        private static final int EMPTY_ROOM = Integer.MAX_VALUE;

        static class Pair {
            private final int row;
            private final int column;

            public Pair(int row, int column) {
                this.row = row;
                this.column = column;
            }
        }

        public void wallsAndGates(int[][] rooms) {
            Queue<Pair> queue = new LinkedList<>();

            for (int row = 0; row < rooms.length; row++) {
                for (int column = 0; column < rooms[0].length; column++) {
                    boolean isGate = rooms[row][column] == GATE;

                    if (isGate) {
                        queue.add(new Pair(row, column));
                    }
                }
            }

            boolean shouldInitGates = true;

            int level = 0;
            while (!queue.isEmpty()) {

                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Pair node = queue.poll();

                    if (node == null
                            || node.column < 0
                            || node.row < 0
                            || node.row > rooms.length - 1
                            || node.column > rooms[0].length - 1
                    ) {
                        continue;
                    }

                    int roomData = rooms[node.row][node.column];

                    if ((roomData == GATE && shouldInitGates) || roomData == EMPTY_ROOM) {

                        if (roomData == EMPTY_ROOM) {
                            rooms[node.row][node.column] = level;
                        }

                        queue.add(new Pair(node.row + 1, node.column));
                        queue.add(new Pair(node.row, node.column + 1));
                        queue.add(new Pair(node.row - 1, node.column));
                        queue.add(new Pair(node.row, node.column - 1));
                    }
                }

                level++;
                shouldInitGates = false;
            }
        }
    }
}
