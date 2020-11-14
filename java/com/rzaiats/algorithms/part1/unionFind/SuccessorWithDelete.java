package com.rzaiats.algorithms.part1.unionFind;

/*
  Coursera - Algorithms Part I
  Week 1 - Interview Questions
  Question 3: Successor with delete.

  Question 3
  Successor with delete. Given a set of nn integers S = \{ 0, 1, ... , n-1 \}S={0,1,...,n−1} and a sequence of requests of the following form:
  Remove xx from SS
  Find the successor of xx: the smallest yy in SS such that y \ge xy≥x.
  design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
*/

public class SuccessorWithDelete {
    public static void main(String[] args) throws NoSuccessorException {
        UnionFindDataStructure unionFindDataStructure = new UnionFindDataStructure(20);

        unionFindDataStructure.delete(11);
        unionFindDataStructure.delete(11);
        unionFindDataStructure.delete(12);
        unionFindDataStructure.delete(14);
        unionFindDataStructure.delete(13);
        unionFindDataStructure.delete(18);
        unionFindDataStructure.delete(19);

        System.out.println(unionFindDataStructure.successor(18));
    }
}
