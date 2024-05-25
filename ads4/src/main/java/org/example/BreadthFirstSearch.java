import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> predecessors;
    private Set<Vertex<V>> visited;
    private MyGraph<V> graph;
    private Vertex<V> source;

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        this.graph = graph;
        this.source = graph.getVertex(source);
        this.predecessors = new HashMap<>();
        this.visited = new HashSet<>();
        bfs();
    }

    private void bfs() {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            for (Vertex<V> neighbor : graph.adjacencyList(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V end) {
        Vertex<V> target = graph.getVertex(end);
        if (!visited.contains(target)) return java.util.Collections.emptyList();
        LinkedList<V> path = new LinkedList<>();
        for (Vertex<V> at = target; at != null; at = predecessors.get(at)) {
            path.addFirst(at.getData());
        }
        return path;
    }
}