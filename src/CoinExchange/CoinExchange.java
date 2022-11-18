package CoinExchange;

import java.util.*;

public class CoinExchange
{
    private static int operationsMemo = 0;
    private static int operations = 0;
    private static final Map<Integer, Long> memo = new HashMap<>();

    public static void main(String... args)
    {
        final var coins = Arrays.asList(5, 3, 2);
        final var sum = 9;

        final var value = minCoins(coins, sum);
        System.out.printf("Min coins = %s / operations = %s - classic%n", value, operations);

        final var valueDp = minCoinsDp(coins, sum);
        System.out.printf("value = %s / operations = %s - dynamic programming%n", valueDp, operationsMemo);

        final var valueBu = minCoinsBu(coins, sum);
        System.out.printf("value = %s - bottom up%n", valueBu);
    }

    private static Long minCoins(final List<Integer> coins, final int value)
    {
        Long shortestPath = null;

        for (var coin : coins)
        {
            long currentPath = 0;
            operations++;
            if (value - coin == 0)
            {
                currentPath++;
            }
            else if (value - coin > 0)
            {
                currentPath += minCoins(coins, value - coin) + 1;
            }
            else
            {
                currentPath = Integer.MAX_VALUE;
            }

            if (shortestPath == null || shortestPath > currentPath)
            {
                shortestPath = currentPath;
            }
        }

        return shortestPath;
    }

    private static Long minCoinsDp(final List<Integer> coins, final int value)
    {
        Long shortestPath = null;

        for (var coin : coins)
        {
            long currentPath = 0;
            operationsMemo++;
            if (memo.containsKey(value - coin))
            {
                currentPath = memo.get(value - coin);
            }
            else if (value - coin == 0)
            {
                currentPath++;
            }
            else if (value - coin > 0)
            {
                currentPath += minCoinsDp(coins, value - coin) + 1;
            }
            else
            {
                currentPath = Integer.MAX_VALUE;
            }

            memo.put(value - coin, currentPath);

            if (shortestPath == null || shortestPath > currentPath)
            {
                shortestPath = currentPath;
            }
        }

        return shortestPath;
    }

    private static Long minCoinsBu(final List<Integer> coins, final int value)
    {
        final var minCoins = new ArrayList<Long>();

        for (int i = 0; i <= value; i++)
        {
            minCoins.add((long) Integer.MAX_VALUE);
        }

        minCoins.set(0, 0L);

        for (int i = 0; i <= value; i++)
        {
            for (var coin : coins)
            {
                if ((i - coin) >= 0)
                {
                    minCoins.set(i, Long.min(minCoins.get(i), minCoins.get(i - coin) + 1));
                }
            }
        }

        return minCoins.get(value);
    }
}
