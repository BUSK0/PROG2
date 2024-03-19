public class Path {
    String pathName;
    //Stocke le nom ou l'identifiant du chemin ou du nœud de destination.

    double distance;
    //Représente la distance ou le coût associé à ce chemin.

    public Path(String name, double dist ){
        pathName = name;
        distance = dist;
    }
}
