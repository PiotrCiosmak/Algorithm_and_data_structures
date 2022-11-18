package Djikstra;

import java.util.*;

public class Djikstra
{

    public static void main(String... args)
    {
        final var startingNode = "W";
        final var adjList = new LinkedHashMap<String, List<Edge>>();
        adjList.put("W", List.of(
                Edge.of("W", "B", 169),
                Edge.of("W", "K", 36)));
        adjList.put("B", List.of(
                Edge.of("B", "W", 169),
                Edge.of("B", "M", 47),
                Edge.of("B", "L", 25)));
        adjList.put("K", List.of(
                Edge.of("K", "W", 36),
                Edge.of("K", "M", 187)));
        adjList.put("M", List.of(
                Edge.of("M", "B", 47),
                Edge.of("M", "K", 187)));
        adjList.put("L", List.of(
                Edge.of("L", "B", 25),
                Edge.of("L", "M", 86),
                Edge.of("L", "N", 349),
                Edge.of("L", "T", 623),
                Edge.of("L", "LA", 529)));
        adjList.put("N", List.of(
                Edge.of("N", "L", 349),
                Edge.of("N", "LA", 108)));
        adjList.put("LA", List.of(
                Edge.of("LA", "N", 108),
                Edge.of("LA", "T", 51),
                Edge.of("LA", "L", 529)));
        adjList.put("T", List.of(
                Edge.of("T", "LA", 51),
                Edge.of("T", "L", 623)));

        final var shortestPaths = djikstra(adjList, startingNode);
        final var shortestPath = new StringBuilder();
        var endingNode = "T";

        while (shortestPaths.get(endingNode) != null)
        {
            shortestPath.append(endingNode).append(" <- ");
            endingNode = shortestPaths.get(endingNode);
        }

        System.out.println(shortestPath.append(startingNode));
    }

    private static Map<String, String> djikstra(final Map<String, List<Edge>> adjList, String startingNode)
    {
        final Queue<Node> queue = new PriorityQueue<>(Comparator.comparing(o -> o.value));
        final Map<String, Integer> distance = new HashMap<>();
        final Map<String, String> previous = new HashMap<>();
        final List<String> visited = new ArrayList<>();

        for (var vertex : adjList.keySet())
        {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        queue.add(Node.of(startingNode, 0));
        distance.put(startingNode, 0);

        while (!queue.isEmpty())
        {
            final var vertex = queue.poll();
            visited.add(vertex.name);
            for (var edge : adjList.get(vertex.name))
            {
                if (!visited.contains(edge.destination))
                {
                    final var tempDistance = distance.get(vertex.name) + edge.weight;
                    if (tempDistance < distance.get(edge.destination))
                    {
                        distance.put(edge.destination, tempDistance);
                        previous.put(edge.destination, vertex.name);
                    }
                    queue.add(Node.of(edge.destination, tempDistance));
                }
            }
        }
        System.out.printf("%s - prices%n", distance);
        return previous;
    }

    private static class Edge
    {
        private String source;
        private String destination;
        private int weight;

        private static Edge of(String source, String destination, int weight)
        {
            var newEdge = new Edge();
            newEdge.source = source;
            newEdge.destination = destination;
            newEdge.weight = weight;
            return newEdge;
        }
    }

    private static class Node
    {
        private String name;
        private int value;

        private static Node of(String name, int value)
        {
            var node = new Node();
            node.name = name;
            node.value = value;
            return node;
        }
    }
}
