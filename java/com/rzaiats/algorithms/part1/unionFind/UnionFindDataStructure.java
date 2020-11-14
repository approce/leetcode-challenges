package com.rzaiats.algorithms.part1.unionFind;

public class UnionFindDataStructure {
    private final int[] values;
    private final int[] weights;
    private final int[] actualValue;

    public UnionFindDataStructure(int size) {
        values = new int[size];
        weights = new int[size];
        actualValue = new int[size];

        for (int i = 0; i < size; i++) {
            values[i] = i;
            actualValue[i] = i;
            weights[i] = 1;
        }
    }

    public void delete(int index) throws NoSuccessorException {
        boolean isLastElement = values.length - 1 == index;

        if (isLastElement) {
            values[index] = -1;

            return;
        }

        union(index, index + 1);
    }

    public int successor(int index) throws NoSuccessorException {
        boolean isLastElement = values.length - 1 == index;

        if (isLastElement) {
            boolean isRemoved = values[index] == -1;

            if (isRemoved) {
                throw new NoSuccessorException();
            } else {
                return values[index];
            }
        }

        return actualValue[getRootForIndex(index)];
    }

    private int getRootForIndex(int index) throws NoSuccessorException {
        while (values[index] != index) {
            index = values[index];

            if (index == -1) {
                throw new NoSuccessorException();
            }
        }

        return index;
    }

    private void union(int leftElement, int rightElement) throws NoSuccessorException {
        int leftRoot = getRootForIndex(leftElement);
        int rightRoot = getRootForIndex(rightElement);

        int leftWeight = weights[leftRoot];
        int rightWeight = weights[rightRoot];
        int weightsSum = leftWeight + rightWeight;

        if (leftWeight > rightWeight) {
            values[rightRoot] = leftRoot;
            weights[leftRoot] = weightsSum;

            actualValue[leftRoot] = rightRoot;

        } else {
            values[leftRoot] = rightRoot;
            weights[rightRoot] = weightsSum;
        }
    }
}
