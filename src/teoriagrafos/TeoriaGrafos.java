package teoriagrafos;

import java.util.ArrayList;
import java.util.List;
import teoriagrafos.Vertex.Cor;

public class TeoriaGrafos {

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
        grafo.addEdge(2, 4);
        System.out.println(grafo);

        System.out.println("Regular: " + grafo.isRegular());
        System.out.println("Simple: " + grafo.isSimple());
        System.out.println("Completo: " + grafo.isComplete());
        
        Vertex verticeInicial = grafo.obterVertice(1);
    }
    
    public List<Vertex> obterFilaAPartirDeUmVerticeInicial(Vertex verticeInicial){
        verticeInicial.Cor = Cor.Cinza;
        List<Vertex> fila = new ArrayList<>();
        fila.add(verticeInicial);
        return fila;
    }
    
    public void realizarBusca(List<Vertex> fila){
        while(!fila.isEmpty()){
            Vertex u = fila.get(0);
            fila.remove(u);
            for (Vertex verticeAdjacente : u.ObterAdjacencias()) {
                if (verticeAdjacente.Cor == Cor.Branco) {
                    fila.add(verticeAdjacente);
                }
            }
        }
    }
}
