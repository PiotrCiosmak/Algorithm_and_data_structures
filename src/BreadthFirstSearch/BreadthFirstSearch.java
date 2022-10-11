package BreadthFirstSearch;

import java.util.*;

public class BreadthFirstSearch
{

    public static void main(String... args)
    {
        final var startingNode = "Warszawa";
        final var graph = Map.of(
                "Warszawa", List.of("Berlin", "Kolonia"),
                "Berlin", List.of("Warszawa", "Chicago"),
                "Chicago", List.of("Berlin"),
                "Kolonia", List.of("Warszawa", "Madryt", "Londyn"),
                "Madryt", List.of("Kolonia", "Nowy Jork", "Londyn"),
                "Nowy Jork", List.of("Madryt", "Londyn", "Tokio"),
                "Londyn", List.of("Kolonia", "Madryt", "Nowy Jork", "Tokio"),
                "Tokio", List.of("Nowy Jork", "Londyn")
        );

        final var shortestPaths = bfs(graph, "Warszawa");

        final var shortestPath = new StringBuilder();
        var endingNode = "Tokio";

        while (shortestPaths.containsKey(endingNode) && shortestPaths.get(endingNode) != null)
        {
            shortestPath.append(endingNode).append(" <- ");
            endingNode = shortestPaths.get(endingNode);
        }

        System.out.println(shortestPath.append(startingNode));
    }

    private static Map<String, String> bfs(final Map<String, List<String>> adjList, final String startingNode)
    {
        final Queue<String> queue = new LinkedList<>();
        final List<String> visited = new ArrayList<>();
        final Map<String, Integer> levels = new HashMap<>();
        final Map<String, String> previous = new HashMap<>();

        queue.add(startingNode);
        levels.put(startingNode, 0);

        while (!queue.isEmpty())
        {
            final var vertex = queue.poll();
            visited.add(vertex);
            for (var neighbour : adjList.get(vertex))
            {
                if (!visited.contains(neighbour))
                {
                    queue.add(neighbour);
                    if (!previous.containsKey(neighbour))
                    {
                        previous.put(neighbour, vertex);
                        levels.put(neighbour, levels.get(vertex) + 1);
                    }
                }
            }
        }
        System.out.println(levels);
        return previous;
    }
}