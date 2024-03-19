import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        Graph transportGraph = new Graph();
    // Ajoutez vos stations et connexions ici
    transportGraph.addEdge("Station1", "Station2", 10);
    transportGraph.addEdge("Station2", "Station3", 5);

    // Exécutez Dijkstra à partir d'une station de départ
    String startStation = "Station1";
    transportGraph.dijkstras(startStation);

    // Affichez le chemin le plus court vers une station de destination
    String endStation = "Station3";
    transportGraph.printPath(endStation);
        

    }
}
