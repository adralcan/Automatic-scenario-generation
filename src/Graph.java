import java.util.ArrayList;
import java.lang.IllegalArgumentException;

public class Graph {
    // Variables de instancia
    // Si los atributos no llevan private se asumme que son públicos
    private int _V; // numero de vertices
    private int _E; // numero de aristas
    private ArrayList<ArrayList<Element>> _ady; // vector de listas de adyacentes

    Graph(int v) {
        _V = v;
        _E = 0;
        _ady = new ArrayList<>(_V);
    }

    public int V() { return _V;}
    public int E() { return _E;}


    public void putEdge(int v, int w) {
        if (v >= _V || w >= _V) {
            throw new IllegalArgumentException();
        }
        ++_E;
        _ady.get(v).add(new Element());
        _ady.get(w).add(new Element());
    }

    public ArrayList<Element> ady(int v) {
        if (v >= _V) {
            throw new IllegalArgumentException();
        }
        return _ady.get(v);
    }
}
