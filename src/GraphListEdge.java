import java.util.*;

class Edge {
    int source; // Исходная вершина
    int target; // Целевая вершина
    int weight; // Вес ребра

    public Edge(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Edge(%d -> %d, weight: %d)", source, target, weight);
    }
}
class Graph1 {
    private List<Edge> edges; // Список рёбер
    private Set<Integer> vertices; // Множество вершин

    public Graph1() {
        edges = new ArrayList<>();
        vertices = new HashSet<>();
    }

    // Добавление вершины
    public void addVertex(int vertex) {
        vertices.add(vertex);
    }

    // Удаление вершины
    public void removeVertex(int vertex) {
        vertices.remove(vertex);
        edges.removeIf(edge -> edge.source == vertex || edge.target == vertex);
    }

    // Добавление ребра
    public void addEdge(int source, int target, int weight) {
        addVertex(source); // Убедимся, что вершина существует
        addVertex(target); // Убедимся, что вершина существует
        edges.add(new Edge(source, target, weight));
        edges.add(new Edge(target, source, weight));
    }

    // Удаление ребра
    public void removeEdge(int source, int target) {
        edges.removeIf(edge -> edge.source == source && edge.target == target);
        edges.removeIf(edge -> edge.source == target && edge.target == source);
    }

    // Поиск ребра
    public boolean hasEdge(int source, int target) {
        return edges.stream().anyMatch(edge -> edge.source == source && edge.target == target);
    }

    // Получение всех рёбер
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    // Получение всех вершин графа
    public Set<Integer> getVertices() {
        return new HashSet<>(vertices);
    }
}
public class GraphListEdge { 
    public static void main(String[] args) {
        Graph1 graph = new Graph1();
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 5);

        System.out.println("Вершины графа: " + graph.getVertices());
        System.out.println("Все рёбра графа: " + graph.getEdges());
        System.out.println("Существует ли ребро 1 -> 2? " + graph.hasEdge(1, 2));

        graph.removeEdge(2, 1);
        System.out.println("Существует ли ребро 1 -> 2 после удаления? " + graph.hasEdge(1, 2));

        graph.removeVertex(3);
        System.out.println("Вершины графа после удаления 3: " + graph.getVertices());
        System.out.println("Все рёбра графа после удаления 3: " + graph.getEdges());
        
    }
}
