package CountingSort;

import java.util.Arrays;
import java.util.List;

public class CountingSort
{

    public static void main(String... args)
    {
        final var array = Arrays.asList(4, 3, 2, 5, 4, 3, 5, 1, 0, 2, 6);
        countingSort(array);
        System.out.printf("%s - sorted%n", array);
    }

    private static void countingSort(List<Integer> data)
    {
        var maxValue = 0;

        for (var value : data)
        {
            if (value > maxValue)
            {
                maxValue = value;
            }
        }

        final var counts = new int[maxValue + 1];
        var number = 0;

        while (number <= maxValue)
        {
            counts[number] = 0;
            number++;
        }

        for (var element : data)
        {
            counts[element] = counts[element] + 1;
        }

        var mainArrayIndex = 0;

        for (int i = 0; i < counts.length; i++)
        {
            while (counts[i] > 0)
            {
                data.set(mainArrayIndex, i);
                counts[i] = counts[i] - 1;
                mainArrayIndex++;
            }
        }
    }
}