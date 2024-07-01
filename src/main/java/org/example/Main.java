package org.example;

import org.example.graphs.DepthFirstSearch33;
import org.example.graphs.Digraph;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Digraph graph = new Digraph(20);
        GraphReader gr = new GraphReader();
        gr.setDigraph(graph);
        gr.readArchive(20);
        gr.setEdges();

        //TODO: IMPLEMENTA A LOGICA DO DFS NA CLASSE GRAPHREADER PRA PERCORRER TUDO EM TODOS NODOS E PEGA O MAIOR
        System.out.println(gr.biggerWay());
    }


}