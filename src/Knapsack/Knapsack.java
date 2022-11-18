package Knapsack;

import java.util.Arrays;
import java.util.List;

public class Knapsack
{
    private static int operationsMemo = 0;
    private static int operations = 0;
    private static int[][] memo;

    public static void main(String... args)
    {
        final var weights = Arrays.asList(3, 4, 2, 5, 3);
        final var values = Arrays.asList(3, 5, 1, 3, 2);
        final int capacity = 13;
        final int n = values.size() - 1;
        memo = new int[weights.size() + 1][capacity + 1];

        var value = knapsack(n, capacity, values, weights);
        System.out.printf("value = %s / operations = %s - classic%n", value, operations);

        var valueDp = knapsackDp(n, capacity, values, weights);
        System.out.printf("value = %s / operations = %s - dynamic programming%n", valueDp, operationsMemo);

        var valueBu = knapsackBu(n, capacity, values, weights);
        System.out.printf("value = %s - bottom up%n", valueBu);
    }

    private static int knapsack(final int n, final int capacity, final List<Integer> values, final List<Integer> weights)
    {
        operations++;
        if (n < 0 || capacity == 0)
        {
            return 0;
        }
        else if (weights.get(n) > capacity)
        {
            return knapsack(n - 1, capacity, values, weights);
        }
        else
        {
            final var temp1 = knapsack(n - 1, capacity, values, weights);
            final var temp2 = values.get(n) + knapsack(n - 1, capacity - weights.get(n), values, weights);
            return Integer.max(temp1, temp2);
        }
    }

    private static int knapsackDp(final int n, final int capacity, final List<Integer> values, final List<Integer> weights)
    {
        operationsMemo++;
        int result;
        if (n < 0 || capacity == 0)
        {
            return 0;
        }
        else if (memo[n][capacity] != 0)
        {
            return memo[n][capacity];
        }
        else if (weights.get(n) > capacity)
        {
            result = knapsackDp(n - 1, capacity, values, weights);
        }
        else
        {
            final var temp1 = knapsackDp(n - 1, capacity, values, weights);
            final var temp2 = values.get(n) + knapsackDp(n - 1, capacity - weights.get(n), values, weights);
            result = Integer.max(temp1, temp2);
        }

        memo[n][capacity] = result;
        return result;
    }


    private static int knapsackBu(final int n, final int capacity, final List<Integer> values, final List<Integer> weights)
    {
        final int[][] table = new int[n + 1][capacity + 1];

        for (int row = 0; row <= n; row++)
        {
            for (int column = 0; column <= capacity; column++)
            {
                if (row == 0 && (column - weights.get(row)) >= 0)
                {
                    table[row][column] = values.get(row);
                }
                else if (row > 0 && (column - weights.get(row)) >= 0)
                {
                    table[row][column] = Integer.max(table[row - 1][column], table[row - 1][column - weights.get(row)] + values.get(row));
                }
            }
        }

        return table[n][capacity];
    }
}