package teoriagrafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Vertex {

    public Vertex(int rotulo) {
        Rotulo = rotulo;
        Arestras = new ArrayList();
    }

    public int Rotulo;
    public List<Edge> Arestras;

    public void AdicionarArestra(Edge arestra) {
        Arestras.add(arestra);
    }

    public boolean RemoverArestra(Edge arestra) {

        if (Arestras.contains(arestra)) {
            Arestras.remove(arestra);
            return true;
        }
        return false;
    }

    public int degree() {
        int numeroLigacoes = 0;
        for (Edge arestra : Arestras) {

            if (arestra.EhLaco()) {
                numeroLigacoes += 2;
            } else {
                numeroLigacoes++;
            }
        }
        return numeroLigacoes;
    }

    public LinkedList<Vertex> ObterAdjacencias() {
        return Arestras.stream().map(arestra -> {
            return arestra.ObterOutroVertice(this.Rotulo);
        }).collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean isAdjacent(int rotuloVertice) {
        boolean saoAdjacentes = false;
        for (Edge arestra : Arestras) {
            Vertex outroVertice = arestra.ObterOutroVertice(this.Rotulo);
            if (outroVertice.Rotulo == rotuloVertice) {
                saoAdjacentes = true;
            }
        }

        return saoAdjacentes;
    }
}