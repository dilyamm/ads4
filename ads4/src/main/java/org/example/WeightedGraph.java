import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean isDirected;

    public WeightedGraph(boolean isDirected) {
        this.vertices = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(V source, V destination, double weight) {
        vertices.putIfAbsent(source, new Vertex<>(source));
        vertices.putIfAbsent(destination, new Vertex<>(destination));
        vertices.get(source).addAdjacentVertex(vertices.get(destination), weight);
        if (!isDirected) {
            vertices.get(destination).addAdjacentVertex(vertices.get(source), weight);
        }
    }

    public Set<Vertex<V>> getVertices() {
        return new HashSet<>(vertices.values());
    }

    public Vertex<V> getVertex(V key) {
        return vertices.get(key);
    }
}