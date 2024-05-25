import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean isDirected;

    public MyGraph(boolean isDirected) {
        this.vertices = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(V source, V destination) {
        vertices.putIfAbsent(source, new Vertex<>(source));
        vertices.putIfAbsent(destination, new Vertex<>(destination));
        vertices.get(source).addAdjacentVertex(vertices.get(destination), 1.0);
        if (!isDirected) {
            vertices.get(destination).addAdjacentVertex(vertices.get(source), 1.0);
        }
    }

    public Set<Vertex<V>> getVertices() {
        return new HashSet<>(vertices.values());
    }

    public Set<Vertex<V>> adjacencyList(Vertex<V> vertex) {
        return vertex.getAdjacentVertices().keySet();
    }

    public Vertex<V> getVertex(V key) {
        return vertices.get(key);
    }
}