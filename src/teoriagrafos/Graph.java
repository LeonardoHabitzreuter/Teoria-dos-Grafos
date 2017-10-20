package teoriagrafos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private final HashMap<Integer, Vertex> _hashVertices;

    public Graph() {
        this._hashVertices = new HashMap<>();
    }

    public boolean addVertex(int id) {
        if (VerticesExistemNoGrafo(id)) {
            return false;
        }

        Vertex vertice = new Vertex(id);
        _hashVertices.put(id, vertice);
        return true;
    }

    public boolean removeVertex(int idVertice) {
        Vertex vertice = _hashVertices.get(idVertice);

        if (!VerticesExistemNoGrafo(idVertice)) {
            return false;
        }

        List<Edge> arestrasVertice = vertice.Arestras;

        arestrasVertice.forEach((arestra) -> {
            Collection<Vertex> vertices = getVertexes();

            vertices.forEach((indice) -> {
                indice.RemoverArestra(arestra);
            });
        });

        _hashVertices.remove(idVertice);
        return true;

    }

    public boolean addEdge(int idVertice1, int idVertice2) {
        Vertex vertice1 = _hashVertices.get(idVertice1);
        Vertex vertice2 = _hashVertices.get(idVertice2);

        if (!VerticesExistemNoGrafo(idVertice1, idVertice2)) {
            return false;
        }

        Edge arestra = new Edge(vertice1, vertice2);
        vertice1.AdicionarArestra(arestra);

        if (!arestra.EhLaco()) {
            vertice2.AdicionarArestra(arestra);
        }

        return true;
    }

    public boolean removeEdge(int idVertice1, int idVertice2) {
        Vertex vertice1 = _hashVertices.get(idVertice1);
        Vertex vertice2 = _hashVertices.get(idVertice2);

        List<Edge> arestrasVertice1 = vertice1.Arestras;

        arestrasVertice1.forEach((arestra) -> {
            boolean removeu = vertice2.RemoverArestra(arestra);
            if (removeu) {
                vertice1.RemoverArestra(arestra);
            }
        });
        return true;
    }

    public boolean adjacent(int idVertice1, int idVertice2) {
        Vertex vertice1 = _hashVertices.get(idVertice1);
        return vertice1.isAdjacent(idVertice2);
    }

    public Collection<Vertex> getVertexes() {
        return _hashVertices.values();
    }

    public LinkedList<Edge> getEdges() {
        ArrayList<Edge> ligacoes = obterLigacoes();

        LinkedList<Edge> arestrasLinkadas = new LinkedList<>();
        ligacoes.stream().filter((ligacao) -> (!arestrasLinkadas.stream().anyMatch((arestra) ->
                (arestra.PrimeiraVertice == ligacao.PrimeiraVertice || arestra.PrimeiraVertice == ligacao.SegundaVertice) &&
                        (arestra.SegundaVertice == ligacao.PrimeiraVertice || arestra.SegundaVertice == ligacao.SegundaVertice)))).forEachOrdered((ligacao) -> {
            arestrasLinkadas.add(ligacao);
        });

        return arestrasLinkadas;
    }

    @Override
    public String toString() {
        String descricaoGrafo = "";

        Collection<Vertex> vertices = getVertexes();

        descricaoGrafo = vertices.stream().map((indice) -> {
            String descricaoVertice = Integer.toString(indice.Rotulo) + ":";
            List<Edge> arestras = indice.Arestras;
            for (Edge arestra : arestras) {
                if (arestra.PrimeiraVertice != indice) {
                    descricaoVertice += " " + arestra.PrimeiraVertice.Rotulo;
                } else {
                    descricaoVertice += " " + arestra.SegundaVertice.Rotulo;
                }
            }
            return descricaoVertice;
        }).map((descricaoVertice) -> System.getProperty("line.separator") + descricaoVertice).reduce(descricaoGrafo, String::concat);
        return descricaoGrafo;
    }

    public int V(){
        return _hashVertices.size();
    }

    public int E(){
        return getEdges().size();
    }

    public int[] degreeSequence(){
        return getVertexes().stream().map((vertice) -> vertice.degree()).sorted().mapToInt(x -> x).toArray();
    }

    public boolean isSimple(){
        ArrayList<Edge> ligacoes = obterLigacoes();

        if (ligacoes.stream().anyMatch((ligacao) -> ligacao.EhLaco())) {
            return false;
        }

        for (Edge ligacao : ligacoes) {
            if (ligacoes.stream().anyMatch((arestra) ->
                    (arestra.PrimeiraVertice.Rotulo + arestra.SegundaVertice.Rotulo) == (ligacao.PrimeiraVertice.Rotulo + ligacao.SegundaVertice.Rotulo))){
                return false;
            }
        }

        return true;
    }

    public boolean isRegular() {
        int grauPrimeiroElemento = getVertexes().stream().findFirst().get().degree();

        return getVertexes().stream().allMatch((vertice) -> vertice.degree() == grauPrimeiroElemento);
    }

    public boolean isComplete(){
        return true;
    }

    private boolean VerticesExistemNoGrafo(int... idsVertices) {
        Boolean todosOsVerticesInformadosExistem = true;

        for (int idVertice : idsVertices) {
            Boolean verticeExiste = _hashVertices.containsKey(idVertice);
            if (!verticeExiste) {
                todosOsVerticesInformadosExistem = false;
            }
        }
        return todosOsVerticesInformadosExistem;
    }

    private ArrayList<Edge> obterLigacoes(){
        ArrayList<Edge> ligacoes = new ArrayList<>();
        getVertexes()
                .stream()
                .map((indice) -> indice.Arestras)
                .forEach((arestras) -> {
                            ligacoes.addAll(arestras);
                        }
                );
        return ligacoes;
    }
}