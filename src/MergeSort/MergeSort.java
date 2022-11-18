package MergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort
{

    public static void main(String... args)
    {
        final var data = Arrays.asList(7, 3, 2, 6, 5, 4, 1);
        System.out.println(mergeSort(data));
    }

    private static List<Integer> mergeSort(final List<Integer> data)
    {
        System.out.println(data);
        if (data.size() < 2)
        {
            return data;
        }
        final int mid = data.size() / 2;
        final var leftSubArray = mergeSort(data.subList(0, mid));
        final var rightSubArray = mergeSort(data.subList(mid, data.size()));
        return merge(leftSubArray, rightSubArray);
    }

    private static List<Integer> merge(final List<Integer> leftSubArray, final List<Integer> rightSubArray)
    {
        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        final List<Integer> merged = new ArrayList<>();

        while (leftArrayIndex < leftSubArray.size() && rightArrayIndex < rightSubArray.size())
        {
            if (leftSubArray.get(leftArrayIndex) < rightSubArray.get(rightArrayIndex))
            {
                merged.add(leftSubArray.get(leftArrayIndex++));
            }
            else
            {
                merged.add(rightSubArray.get(rightArrayIndex++));
            }
        }

        while (leftArrayIndex < leftSubArray.size())
        {
            merged.add(leftSubArray.get(leftArrayIndex++));
        }
        while (rightArrayIndex < rightSubArray.size())
        {
            merged.add(rightSubArray.get(rightArrayIndex++));
        }

        System.out.printf("%s - merged%n", merged);

        return merged;
    }
}