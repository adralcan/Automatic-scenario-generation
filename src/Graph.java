import java.util.ArrayList;

public class Graph {
    // Variables de instancia
    private int _V; // numero de vertices
    private int _E; // numero de aristas
    private ArrayList<ArrayList<Element>> _ady; // vector de listas de adyacentes

    Graph(int v) {
        _V = v;
        _E = 0;
        _ady = new ArrayList<>(_V);
    }

    int V() { return _V;}
    int E() { return _E;}

}
