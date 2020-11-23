package com.rzaiats.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CourseSchedule_210 {

    public static void main(String[] args) {
        int[] result = new Solution().findOrder(5, new int[][]{
                new int[]{0, 1},
                new int[]{1, 0},
                new int[]{2, 0},
                new int[]{3, 1},
                new int[]{3, 2},
        });

        System.out.println(Arrays.toString(result));
    }

    /**
     * Kahn's algorithm
     * 1. Find starting nodes (that have no input links) and add them to queue
     * 2. Traverse from initial nodes. Add each discovered node to queue
     * 3. Remove input edge for node
     * 4. If all input edges have been removed for node - node is cleared and can be added to queue to be processed
     */
    static class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            LinkedList<Integer> result = new LinkedList<>();

            ArrayList<List<Integer>> links = new ArrayList<>();

            for (int i = 0; i < numCourses; i++) {
                links.add(new LinkedList<>());
            }

            int[] inputEdgesCount = new int[numCourses];

            for (int[] singlePrerequisite : prerequisites) {
                int dependent = singlePrerequisite[0];
                int parent = singlePrerequisite[1];
                links.get(parent).add(dependent);

                inputEdgesCount[dependent]++;
            }

            LinkedList<Integer> queue = new LinkedList<>();

            for (int courseId = 0; courseId < inputEdgesCount.length; courseId++) {
                if (inputEdgesCount[courseId] == 0) {
                    queue.add(courseId);
                }
            }

            while (!queue.isEmpty()) {
                Integer courseId = queue.poll();

                if (inputEdgesCount[courseId] > 0) {
                    inputEdgesCount[courseId]--;

                    if (inputEdgesCount[courseId] > 0) {
                        continue;
                    }
                }

                result.add(courseId);

                List<Integer> dependants = links.get(courseId);
                queue.addAll(dependants);
            }

            int[] resultArray = result.stream().mapToInt(value -> value).toArray();

            if (resultArray.length != numCourses) {
                return new int[0];
            } else {
                return resultArray;
            }
        }
    }
}
