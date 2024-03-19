public class Heap {
    Path[] path;
    //Un tableau pour stocker les éléments du tas

    int heap_size;
    //Garde la trace de la taille actuelle du tas, c'est-à-dire le nombre d'éléments qu'il contient.

    int numberOfNode;
    //Le nombre maximum de nœuds (ou d'éléments) que le tas peut contenir.

    public Heap(int numberOfNode){
        this.numberOfNode = numberOfNode;
        heap_size = 0;
        path = new Path[this.numberOfNode+1];
        path[0] = new Path("null", (double) Integer.MIN_VALUE);
    }

    public  int size(){
        return heap_size;
        //Retourne la taille actuelle du tas.
    }

    private  int parent( int i){
        return  i >> 1;
        //Calcule et retourne l'indice du parent d'un nœud donné dans le tableau.
    }

    public int left(int i){
        return i << 1;
        //Calcule et retournent les indices des enfants gauche d'un nœud donné.
    }

    public int right(int i) {
        return (2*i + 1);
        //Calcule et retournent les indices des enfants droit d'un nœud donné.
    }

    private void swap(int i, int j) {
        //Échange deux éléments dans le tableau, utilisé lors des opérations d'insertion et de restructuration du tas.
        Path temp;
        temp = path[i];
        path[i] = path[j];
        path[j] = temp;
    }

    private void minHeapify(int i) {
        // Assure que la propriété de tas min est maintenue en commençant à l'indice
        int l = left(i);
        int r = right(i);
        int smallest;
        boolean flag = false;

    /*Si le nœud à'i' respect pas la propriété de tas min avec ses enfants, 
    il est échangé avec le plus petit de ses enfants, et minHeapify est appelé récursivement pour le sous-arbre affecté. */
        if (i - 1 >= (heap_size / 2) && i <= heap_size) {
            flag = true;
        } else {
            flag = false;
        }
        if (!flag && heap_size > 0) {
            if(path[l].distance < path[i].distance)
                smallest = l;
            else
                smallest = i;
            if(path[r].distance < path[smallest].distance)
                smallest = r;
            if(smallest !=i) {
                swap(i, smallest);
                minHeapify(smallest);
            }
        }
    }
    public void insert(Path p) {
        //ajoute un élément au tas min et le remonte jusqu'à ce que l'ordre du tas soit correct
        path[++heap_size] = p;
        int i = heap_size;
        int parent = parent(i);
        while (path[i].distance < path[parent].distance) {
            swap(i, parent);
            i = parent;
        }
    }
    public Path extractMin() {
        //enlève et retourne l'élément le plus petit du tas, puis rétablit l'ordre du tas en utilisant minHeapify.
        Path root = path[1];
        path[1] = path[heap_size--];
        minHeapify(1);
        return root;
    }


}
