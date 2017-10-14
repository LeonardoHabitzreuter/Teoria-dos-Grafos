package com.TeoriaDosGrafos;

public class Edge {
    public Edge(Vertex primeiraVertice, Vertex segundaVertice) {
        PrimeiraVertice = primeiraVertice;
        SegundaVertice = segundaVertice;
    }

    public Vertex PrimeiraVertice;
    public Vertex SegundaVertice;

    public Boolean EhLaco() {
        return PrimeiraVertice == SegundaVertice;
    }

    public Vertex ObterOutroVertice(int vertice) {
        if (vertice == PrimeiraVertice.Rotulo) {
            return SegundaVertice;
        }
        return PrimeiraVertice;
    }
}
