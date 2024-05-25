import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Set;
import java.util.List;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distances;
    private Map<Vertex<V>, Vertex<V>> predecessors;
    private Set<Vertex<V>> visited;
    private WeightedGraph<V> graph;
    private Vertex<V> source;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        this.graph = graph;
        this.source = graph.getVertex(source);
        this.distances = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.visited = new HashSet<>();
        dijkstra();
    }

    private void dijkstra() {
        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));
        distances.put(source, 0.0);
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> current = priorityQueue.poll();

            if (visited.contains(current)) continue;
            visited.add(current);

            for (Map.Entry<Vertex<V>, Double> adjacentEntry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = adjacentEntry.getKey();
                double weight = adjacentEntry.getValue();
                double newDist = distances.get(current) + weight;

                if (newDist < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V end) {
        Vertex<V> target = graph.getVertex(end);
        if (!distances.containsKey(target)) return java.util.Collections.emptyList();
        LinkedList<V> path = new LinkedList<>();
        for (Vertex<V> at = target; at != null; at = predecessors.get(at)) {
            path.addFirst(at.getData());
        }
        return path;
    }
}