package DepthFirstSearch;

import java.util.*;

public class DepthFirstSearch
{

    public static void main(String... args)
    {
        final var graph = Map.of(
                "Warszawa", List.of("Kolonia", "Berlin"),
                "Berlin", List.of("Warszawa", "Chicago"),
                "Chicago", List.of("Berlin"),
                "Kolonia", List.of("Warszawa", "Londyn", "Madryt"),
                "Madryt", List.of("Kolonia", "Londyn", "Nowy Jork"),
                "Nowy Jork", List.of("Madryt", "Londyn", "Tokio"),
                "Londyn", List.of("Kolonia", "Madryt", "Nowy Jork", "Tokio"),
                "Tokio", List.of("Nowy Jork", "Londyn")
        );

        dfs(graph, "Warszawa");
    }

    private static void dfs(final Map<String, List<String>> adjList, final String startingNode)
    {
        final Deque<String> stack = new LinkedList<>();
        final List<String> visited = new ArrayList<>();

        stack.add(startingNode);

        while (!stack.isEmpty())
        {
            final var vertex = stack.pollLast();
            System.out.printf("Node: %s%n", vertex);
            visited.add(vertex);
            for (var neighbour : adjList.get(vertex))
            {
                if (!visited.contains(neighbour))
                {
                    stack.add(neighbour);
                }
            }
        }
    }
}
