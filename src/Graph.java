import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Graph {
    private Map<Vertex, List<Vertex>> adjVertices;
    // standard constructor, getters, setters

    void addVertex(Element element) {
        adjVertices.putIfAbsent(new Vertex(element), new ArrayList<>());
    }

    void removeVertex(Element element) {
        Vertex v = new Vertex(element);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(element));
    }

    void addEdge(Element element1, Element element2) {
        Vertex v1 = new Vertex(element1);
        Vertex v2 = new Vertex(element2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    void removeEdge(Element element1, Element element2) {
        Vertex v1 = new Vertex(element1);
        Vertex v2 = new Vertex(element2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null) eV1.remove(v2);
        if (eV2 != null) eV2.remove(v1);
    }

    List<Vertex> getAdjVertices(Element element) {
        return adjVertices.get(new Vertex(element));
    }

    // Busqueda en profundidad
    //https://www.baeldung.com/java-graphs
}