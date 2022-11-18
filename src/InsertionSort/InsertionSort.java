package InsertionSort;

import java.util.Arrays;
import java.util.List;

public class InsertionSort
{

    public static void main(String... args)
    {
        final var data = Arrays.asList(7, 3, 2, 6, 5, 4, 1);
        insertionSort(data);
    }

    private static void insertionSort(final List<Integer> data)
    {
        int moves = 0;
        for (int i = 1; i < data.size(); i++)
        {
            int rightPointer = i;
            int leftPointer = rightPointer - 1;
            int inMemory = data.get(rightPointer);
            while (leftPointer >= 0 && data.get(leftPointer) > inMemory)
            {
                data.set(rightPointer, data.get(leftPointer));
                leftPointer--;
                rightPointer--;
                moves++;
            }
            data.set(rightPointer, inMemory);
            System.out.printf("%s iteration %s, moves: %s%n", data, i, moves);
        }
    }
}