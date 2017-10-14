package teoriadosgrafos;

public class TeoriaDosGrafos {

    public static void main(String[] args) {
        Graph grafo = new Graph();
        grafo.addVertex(1);
        grafo.addVertex(2);
        grafo.addVertex(3);
        grafo.addVertex(4);
        grafo.addEdge(1, 2);
        grafo.addEdge(1, 3);
        grafo.addEdge(1, 4);
        grafo.addEdge(2, 3);
        grafo.addEdge(2, 4);
        grafo.addEdge(3, 4);
        //grafo.addEdge(2, 4);   
        System.out.println(grafo);
        
        System.out.println("Regular: " + grafo.isRegular());
        System.out.println("E: " + grafo.E());
        System.out.println("Simple: " + grafo.isSimple());
    }
}
