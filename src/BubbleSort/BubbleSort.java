package BubbleSort;

import java.util.Arrays;
import java.util.List;

public class BubbleSort
{

    public static void main(String... args)
    {
        final var data = Arrays.asList(7, 3, 2, 6, 5, 4, 1);
        bubbleSort(data);
        System.out.println("Sorting finished!");
        System.out.println(data);
    }

    private static void bubbleSort(final List<Integer> data)
    {
        for (int iteration = 0; iteration < data.size() - 1; iteration++)
        {
            System.out.printf("%s iteration %s%n", data, iteration);
            for (int leftPointer = 0; leftPointer < data.size() - 1; leftPointer++)
            {
                if (data.get(leftPointer) > data.get(leftPointer + 1))
                {
                    final int temp = data.get(leftPointer);
                    data.set(leftPointer, data.get(leftPointer + 1));
                    data.set(leftPointer + 1, temp);
                }
            }
        }
    }
}
