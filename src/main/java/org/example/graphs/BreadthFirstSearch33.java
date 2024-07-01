package org.example.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch33 {
    private boolean[] marked;
    private int[] edgeTo;

    private int[] distTo;

    int s;

    public BreadthFirstSearch33(Graph g, int s) {
        //incializar a estutura base
        this.s = s;

        // inicializar os vetores
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];

        // inicializar a fila
        Queue<Integer> q = new LinkedList<Integer>();

        //ALGORITMO
        //1. add "s" na fila
        q.add(s);
        //2. marca como visitado
        marked[0] = true;

        //3. repetir ate fila vazia
        while (q.peek() != null) {
            //4. remove "v" da fila
            int v = q.remove();
            System.out.println(v);
            //5. pegar adjacentes de v
            for (int w : g.adj(v)) {
                //6. vizinhos visitados
                if (!marked[w]) {
                    //7. adiciona na fila
                    q.add(w);
                    //marca como visitado
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public ArrayList<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        ArrayList<Integer> path = new ArrayList<>();
        while (v != s) {
            path.add(0, v);
            v = edgeTo[v];
        }
        path.add(0, s); //insere vertice inicial
        return path;    //teste edgeTo: caminho entre 0 e 3
    }
    //para teste do dist
    public int distTo(int v){
        return distTo[v];
    }

    public static void main(String[] args) {
        String filename = "tinyG.txt";
        In in = new In(filename);
        Graph g = new Graph(in);
        BreadthFirstSearch33 cl = new BreadthFirstSearch33(g, 0);
        //testar edgeTo
        // verificar se tem caminho : 0 para o 3
//        if (cl.hasPathTo(3)) {
//            System.out.println("Existe caminho entre: 0 e 3");
//            for (int v : cl.pathTo(3)) {
//                System.out.println(v + " ");
//            }
//        }

        System.out.println("Caminhos existentes:");
        System.out.println("0 : Vértice Inicial");
        //percorrer todos os vertices
        for (int v = 1; v < g.V(); v++){
            //se tem caminho para v, ele da cada um dos caminhos
            if (cl.hasPathTo(v)){
                System.out.print(v + ": (" + cl.distTo[v] + ") ");
                for (int w : cl.pathTo(v)){
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        }
    }
}