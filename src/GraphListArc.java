import java.util.*;

class Arc {
    int source; // Исходная вершина
    int target; // Целевая вершина
    int weight; // Вес ребра

    public Arc(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Arc(%d -> %d, weight: %d)", source, target, weight);
    }
}
class Graph {
    private List<Arc> arcs; // Список рёбер
    private Set<Integer> vertices; // Множество вершин

    public Graph() {
        arcs = new ArrayList<>();
        vertices = new HashSet<>();
    }

    // Добавление вершины
    public void addVertex(int vertex) {
        vertices.add(vertex);
    }

    // Удаление вершины
    public void removeVertex(int vertex) {
        vertices.remove(vertex);
        arcs.removeIf(arc -> arc.source == vertex || arc.target == vertex);
    }

    // Добавление дуги
    public void addEdge(int source, int target, int weight) {
        addVertex(source); // Убедимся, что вершина существует
        addVertex(target); // Убедимся, что вершина существует
        arcs.add(new Arc(source, target, weight));
    }

    // Удаление дуги
    public void removeEdge(int source, int target) {
        arcs.removeIf(arc -> arc.source == source && arc.target == target);
    }

    // Поиск дуги
    public boolean hasEdge(int source, int target) {
        return arcs.stream().anyMatch(arc -> arc.source == source && arc.target == target);
    }

    // Получение всех дуг
    public List<Arc> getEdges() {
        return new ArrayList<>(arcs);
    }

    // Получение всех вершин графа
    public Set<Integer> getVertices() {
        return new HashSet<>(vertices);
    }
}
public class GraphListArc {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 5);

        System.out.println("Вершины графа: " + graph.getVertices());
        System.out.println("Все дуги графа: " + graph.getEdges());
        System.out.println("Существует ли дуги 1 -> 2? " + graph.hasEdge(1, 2));

        graph.removeEdge(2, 1);
        System.out.println("Существует ли дуга 1 -> 2 после удаления дуги 2 -> 1? " + graph.hasEdge(1, 2));

        graph.removeVertex(3);
        System.out.println("Вершины графа после удаления 3: " + graph.getVertices());
        System.out.println("Все дуги графа после удаления 3: " + graph.getEdges());

    }
}
