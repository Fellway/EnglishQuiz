package org.example;

import java.util.stream.IntStream;

public class Levenshtein {

    public static double calculate(final String s1, final String s2) {
        final int matrixXLength = s1.length();
        final int matrixYLength = s2.length();
        final double[][] matrix = new double[matrixXLength + 1][matrixYLength + 1];

        IntStream.rangeClosed(0, s1.length()).forEach(i -> matrix[i][0] = i);
        IntStream.rangeClosed(0, s2.length()).forEach(i -> matrix[0][i] = i);

        IntStream.rangeClosed(1, s1.length()).forEach(
                i -> IntStream.rangeClosed(1, s2.length()).forEach(
                        j -> matrix[i][j] =
                                findMin(
                                        remove(matrix, i, j),
                                        insert(matrix, i, j),
                                        change(matrix, i, j, s1, s2))
                )
        );

        return matrix[matrixXLength][matrixYLength];
    }

    private static double remove(final double[][] matrix, final int i, final int j) {
        return matrix[i - 1][j] + 1;
    }

    private static double insert(final double[][] matrix, final int i, final int j) {
        return matrix[i][j - 1] + 1;
    }

    private static double change(final double[][] matrix, final int i, final int j, final String s1, final String s2) {
        return matrix[i - 1][j - 1] + calculateCost(s1.charAt(i - 1), s2.charAt(j - 1));
    }

    private static double findMin(final Double n1, final Double n2, final Double n3) {
        return Math.min(n1, Math.min(n2, n3));
    }

    private static double calculateCost(final char c1, final char c2) {
        return c1 == c2 ? 0 : 1;
    }
}
