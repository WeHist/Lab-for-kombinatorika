import java.util.*;

class Adjacencies {
    int source; // Исходная вершина
    int target; // Целевая вершина
    int weight; // Вес ребра

    public Adjacencies(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Adjacencies(%d -> %d, weight: %d)", source, target, weight);
    }
}
class Graph2 {
    private List<Adjacencies> edges; // Список рёбер
    private Set<Integer> vertices; // Множество вершин

    public Graph2() {
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
        edges.add(new Adjacencies(source, target, weight));
        edges.add(new Adjacencies(target, source, weight));
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
    public List<Adjacencies> getEdges() {
        return new ArrayList<>(edges);
    }

    // Получение всех вершин графа
    public Set<Integer> getVertices() {
        return new HashSet<>(vertices);
    }
    // Получение смежных вершин для заданной вершины
    public List<Integer> getAdjacentVertices(int vertex) {
        List<Integer> adjacentVertices = new ArrayList<>();
        for (Adjacencies edge : edges) {
            if (edge.source == vertex) {
                adjacentVertices.add(edge.target);
            }
        }
        return adjacentVertices;
    }
}
public class GraphListAdjacencies {
    public static void main(String[] args) {
        Graph2 graph = new Graph2();
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 5);

        System.out.println("Смежные вершины для 1: " + graph.getAdjacentVertices(1));

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
