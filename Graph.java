import java.util.*;
import java.lang.*;
import java.io.*;

class Pair {
    /* Cette classe est utilisée pour stocker des paires de chaînes,
    afin de représenter des arêtes dans le graphe avec leurs noms de nœuds source et destination.*/
    String str1;
    String str2;

    public Pair(String s1, String s2) {
        str1 = s1;
        str2 = s2;
    }
    public String key() {
        return str1;
    }
    public String value() {
        return str2;
    }
}

public class Graph {
    public static final int INFINITY = Integer.MAX_VALUE; 
    //Une constante représentant l'infini, utilisée pour initialiser les distances.

    private Map<String, Node> nodeMap = new HashMap<String, Node>();
    //Une carte associant les noms des sommets (String) à leurs objets Node.

    private LinkedList<Pair> edgeList = new LinkedList<Pair>();
    //Une liste des arêtes du graphe, stockées sous forme de paires Pair.

    
    private Node getVertex(String vertexName) {
    // cette méthode récupère un objet Node basé sur son nom. Si le nœud n'existe pas, il est créé et ajouté à nodeMap
        Node node = nodeMap.get(vertexName);
        if (node == null) {
            node = new Node(vertexName, 0, 0, vertexName);
            nodeMap.put(vertexName, node);
        }
        return node;
    }

    public void addEdge(String sourceName, String destinationName, double distance) {
        /* Cette méthode ajoute une arête au graphe, en créant ou récupérant les nœuds source et destination 
       puis en ajoutant l'arête à la liste d'adjacence du nœud source.méthode  */
        
       Node sourceNode = getVertex(sourceName);
        Node destNode = getVertex(destinationName);
    //Récupération ou création des nœuds source et destination

        Iterator<Edge> listIterator = sourceNode.adj.listIterator();
        /* Un Iterator est créé pour parcourir la liste d'adjacence (adj) du nœud source. 
        La liste d'adjacence contient toutes les arêtes sortant du nœud source.*/
        int i = 0;
        int flag = 0;

        while (listIterator.hasNext()) {
    //La boucle while itère sur chaque Edge dans la liste d'adjacence du nœud source.
           
        Edge edge = listIterator.next();
        /*Pour chaque arête (Edge) dans la liste d'adjacence,
         la méthode vérifie si le nœud de destination de cette arête correspond au destNode recherché. */

            if (destNode.name.equals(edge.destName.name)) {
                sourceNode.adj.get(i).distance = distance;
                flag = 1;
        /*Si une arête existante avec le même nœud de destination est trouvée, sa distance est mise à jour avec la nouvelle valeur fournie (distance), 
        et un drapeau (flag) est positionné à 1 pour indiquer qu'une mise à jour a eu lieu. */
            }
            i++;

        }
        if (flag == 0)
        /*Si, aucune arête correspondante au nœud de destination n'a été trouvée, 
        alors une nouvelle arête est créée et ajoutée à la liste d'adjacence du nœud source. */
            sourceNode.adj.add(new Edge(destNode, distance, distance, distance, destinationName));
        }


    private void print() {
        /* Cette méthode Affiche le graphe. Pour chaque nœud, il affiche le nœud 
        et ses arêtes sortantes avec les distances. */

        TreeSet<String> treeSet = new TreeSet<String>();
        System.out.println();
        for (String key : nodeMap.keySet()) {
            treeSet.add(key);
        }
        Iterator<String> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            String adjacentNode = iterator.next();
            System.out.print(adjacentNode);
            System.out.println();
            TreeSet<String> adjacent = new TreeSet<String>();
            HashMap<String, Double> hashMap = new HashMap<String, Double>();
            /*Un HashMap est créé pour associer chaque nœud de destination à la distance de l'arête qui le relie au nœud courant */
            
            for (Edge key : nodeMap.get(adjacentNode).adj) {
                adjacent.add(key.destName.name);
                hashMap.put(key.destName.name, key.distance);
            }
            Iterator<String> adjacentIterator = adjacent.iterator();
            while (adjacentIterator.hasNext()) {
                String node = adjacentIterator.next();
                System.out.print("  " + node + " " + hashMap.get(node));
            }
        }
    }

    private void clearAll( )
    {
        for( Node node : nodeMap.values( ) )
            node.reset();
    }

    public void dijkstras(String startNode) {
        clearAll();
        PriorityQueue<Path> minPriorityQueue = new PriorityQueue<>(Comparator.comparingDouble(p -> p.distance));
        Node initialNode = nodeMap.get(startNode);
    
        if (initialNode == null) {
            System.out.println("Point de départ incorrecte");
            return;
        }
    
        initialNode.dist = 0.0;
        minPriorityQueue.add(new Path(initialNode.name, 0.0));
    
        while (!minPriorityQueue.isEmpty()) {
            Path currentPath = minPriorityQueue.poll();
            Node currentNode = nodeMap.get(currentPath.pathName);
    
            // Vérifie si la distance actuelle est déjà optimisée
            if (currentPath.distance > currentNode.dist) {
                continue;
            }
    
            for (Edge edge : currentNode.adj) {
                Node nextNode = edge.destName;
                double newDist = currentNode.dist + edge.distance;
                if (nextNode.dist > newDist) {
                    nextNode.dist = newDist;
                    nextNode.prev = currentNode;
                    minPriorityQueue.add(new Path(nextNode.name, newDist));
                }
            }
        }
    }    
    
    private void printPath(Node dest) {
        /* Cette est une fonction récursive conçue pour afficher le chemin le plus court d'un nœud source à un nœud de destination dans un graphe,
         en utilisant l'approche de parcours inverse à partir du nœud de destination. */
        Node previous = dest.prev;
        String name = dest.name;
        if (previous != null) {
            printPath(previous);
            System.out.print(" ");
        }
        System.out.print(name);
    }


    public void printPath(String destinationName) {
        Node node = nodeMap.get(destinationName);
        if (node == null){
            System.out.println("Adresse de destination incorrecte");
            return;
        }
        else if (node.dist == INFINITY)
            System.out.println(destinationName + " is unreachable");
        else {
            System.out.println();
            printPath(node);
            System.out.printf(" %.2f", node.dist);
            System.out.println();
        }
    }


}
/*Ce code définit deux classes en Java, Pair et Graph, 
destinées à représenter respectivement des paires de chaînes de caractères 
et un graphe orienté pondéré. */
