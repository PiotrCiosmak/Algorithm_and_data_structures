package Fibonacci;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci
{
    private static int operationsMemo = 0;
    private static int operations = 0;
    private static final Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String... args)
    {
        final var valueDp = fibonacci_dp(6);
        System.out.printf("value = %s / operations = %s - dynamic programming%n", valueDp, operationsMemo);

        final var value = fibonacci(6);
        System.out.printf("value = %s / operations = %s - classic%n", value, operations);

        final var valueBu = fibonacci_bu(40);
        System.out.printf("value = %s - bottom up%n", valueBu);
    }

    private static int fibonacci(final int n)
    {
        operations++;
        if (n == 0)
        {
            return 0;
        }
        if (n <= 2)
        {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static int fibonacci_dp(final int n)
    {
        operationsMemo++;
        if (memo.containsKey(n))
        {
            return memo.get(n);
        }
        if (n == 0)
        {
            return 0;
        }
        if (n <= 2)
        {
            return 1;
        }
        final var value = fibonacci_dp(n - 1) + fibonacci_dp(n - 2);
        memo.put(n, value);
        return value;
    }

    private static int fibonacci_bu(final int n)
    {
        final Map<Integer, Integer> fibonacciNumbers = new HashMap<>();
        for (int i = 0; i <= n; i++)
        {
            if (i == 0)
            {
                fibonacciNumbers.put(i, 0);
            }
            else if (i == 1)
            {
                fibonacciNumbers.put(i, 1);
            }
            else
            {
                fibonacciNumbers.put(i, fibonacciNumbers.get(i - 2) + fibonacciNumbers.get(i - 1));
            }
        }
        return fibonacciNumbers.get(n);
    }
}