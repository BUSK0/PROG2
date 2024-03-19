import java.util.LinkedList;
import java.util.List;

public class Node {
    public String name;
    public List<Edge> adj;
    public Node prev;
    public Double dist;
    public double latitude;
    public double longitude;
    public String stationType; // Par exemple, "bus", "metro"

    public Node(String nm, double lat, double lon, String type) {
        name = nm;
        latitude = lat;
        longitude = lon;
        stationType = type;
        adj = new LinkedList<Edge>();
        reset();
    }
    public void reset() {
        dist = (double) Graph.INFINITY;
        prev = null;
    }

}

/*La classe Node représente un nœud dans un graphe. Chaque nœud a un nom (name),
 une liste d'arêtes adjacentes (adj) qui connectent ce nœud à d'autres nœuds, 
 un nœud précédent (prev) utilisé pour tracer le chemin le plus court dans certains algorithmes de graphes, 
 et une distance (dist) qui représente la distance minimale de ce nœud par rapport à un point de départ dans le contexte d'un algorithme de recherche de chemin le plus court, comme l'algorithme de Dijkstra. */