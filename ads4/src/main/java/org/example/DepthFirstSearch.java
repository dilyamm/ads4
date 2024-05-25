import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class DepthFirstSearch<V> implements Search<V> {
    private Set<Vertex<V>> marked;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private Vertex<V> source;
    private MyGraph<V> graph;

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        this.graph = graph;
        this.source = graph.getVertex(source);
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
        dfs(this.source);
    }

    private void dfs(Vertex<V> current) {
        marked.add(current);

        for (Vertex<V> v : graph.adjacencyList(current)) {
            if (!marked.contains(v)) {
                edgeTo.put(v, current);
                dfs(v);
            }
        }
    }

    @Override
    public List<V> pathTo(V end) {
        Vertex<V> target = graph.getVertex(end);
        if (!marked.contains(target)) return java.util.Collections.emptyList();
        LinkedList<V> path = new LinkedList<>();
        for (Vertex<V> x = target; x != null; x = edgeTo.get(x)) {
            path.addFirst(x.getData());
        }
        return path;
    }
}