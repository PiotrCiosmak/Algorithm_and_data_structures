package BellmanFord;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BellmanFord
{

    public static void main(String[] args)
    {
        final var startingNode = "W";
        final var adjList = new LinkedHashMap<String, List<Edge>>();
        adjList.put("W", List.of(
                Edge.of("W", "B", 181),
                Edge.of("W", "K", -36)));
        adjList.put("B", List.of(
                Edge.of("B", "M", 20),
                Edge.of("B", "L", 28)));
        adjList.put("K", List.of(
                Edge.of("K", "M", 187)));
        adjList.put("T", List.of(
                Edge.of("T", "M", -366)));
        adjList.put("M", List.of(
                Edge.of("M", "B", 20),
                Edge.of("M", "L", 86)));
        adjList.put("L", List.of(
                Edge.of("L", "N", 349),
                Edge.of("L", "T", 623),
                Edge.of("L", "LA", 529)));
        adjList.put("N", List.of(
                Edge.of("N", "LA", -88)));
        adjList.put("LA", List.of(
                Edge.of("LA", "T", 57)));

        final var shortestPaths = bellmanFord(adjList, "W");
        final var shortestPath = new StringBuilder();
        var endingNode = "T";

        while (shortestPaths.containsKey(endingNode) && shortestPaths.get(endingNode) != null)
        {
            shortestPath.append(endingNode).append(" <- ");
            endingNode = shortestPaths.get(endingNode);
        }

        System.out.println(shortestPath.append(startingNode));
    }

    private static Map<String, String> bellmanFord(final Map<String, List<Edge>> adjList, final String startingNode)
    {
        final Map<String, Integer> distance = new HashMap<>();
        final Map<String, String> previous = new HashMap<>();

        for (var vertex : adjList.keySet())
        {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        distance.put(startingNode, 0);

        var modified = false;

        for (var iteration = 0; iteration < adjList.size() + 1; iteration++)
        {
            modified = false;
            for (var vertex : adjList.keySet())
            {
                for (var edge : adjList.get(vertex))
                {
                    final var tempDistance = distance.get(vertex) + edge.weight;
                    if (tempDistance < distance.get(edge.destination))
                    {
                        distance.put(edge.destination, tempDistance);
                        previous.put(edge.destination, vertex);
                        modified = true;
                    }
                }
            }
            System.out.println(distance);
        }

        if (modified)
        {
            System.out.println("Detected a negative cycle!");
            throw new IllegalStateException("Detected a negative cycle!");
        }

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
}
