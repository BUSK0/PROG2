public class Edge {
    public Node destName; //déclaration d'un attribut public de type Node nommé destName. Cet attribut est utilisé pour stocker le nœud de destination de l'arête.
    public double distance;
    public double time; // Temps de trajet estimé
    public double cost; // Coût du trajet
    public String connectionType; // Par exemple, "bus", "Marche"

    public Edge(Node node, double dist, double time, double cost, String type){
        destName = node;
        distance = dist;
        this.time = time;
        this.cost = cost;
        connectionType = type;
    }
}
/*cette classe est utilisée pour représenter une arête dans un graphe, 
avec des informations sur le nœud de destination de l'arête (destName) et 
sur la distance ou le poids de l'arête (distance) . */