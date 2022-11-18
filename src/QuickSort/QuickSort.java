package QuickSort;

import java.util.Arrays;
import java.util.List;

public class QuickSort
{

    public static void main(String... args)
    {
        final var data = Arrays.asList(7, 3, 2, 6, 5, 1, 4);
        quickSort(data, 0, data.size());
        System.out.printf("%s - sorted%n", data);
    }

    private static void quickSort(final List<Integer> data, final int leftEdge, final int rightEdge)
    {
        if (leftEdge >= rightEdge)
        {
            return;
        }

        int bluePointer = leftEdge - 1;
        int pivot = data.get(rightEdge - 1);

        for (int yellowPointer = leftEdge; yellowPointer < rightEdge; yellowPointer++)
        {
            if (data.get(yellowPointer) <= pivot)
            {
                bluePointer++;
                final int temp = data.get(bluePointer);
                data.set(bluePointer, data.get(yellowPointer));
                data.set(yellowPointer, temp);
            }
        }

        System.out.printf("%s - pivot: %s%n", data, pivot);
        quickSort(data, leftEdge, bluePointer);
        quickSort(data, bluePointer + 1, rightEdge);
    }
}