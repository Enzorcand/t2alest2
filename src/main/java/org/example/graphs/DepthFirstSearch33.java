package org.example.graphs;

import java.util.ArrayList;

public class DepthFirstSearch33 {
    //2 vetores
    // marked
    //edgeTo
    private boolean[] marked;
    private  int[] edgeTo;

    private int s; //vertice inicial


    public DepthFirstSearch33(Graph g, int s){
        this.s = s;
        //inicializar vetores
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        //comeca o caminhamento
        dfs(g, s);
    }

    //Se tem caminho?! diz se tem caminh ou nao
    public boolean hasPathTo (int v){
        return marked[v];
    }

    // quem é o caminho
    public ArrayList<Integer> pathTo (int v){
        //verificar - validar - se tem o caminho
        if (!hasPathTo(v)){
            return null;
        }
        //sei que tem um caminho, quero a sequencia de vertice
        ArrayList<Integer> path = new ArrayList<>();
        while (v != s) { //verticie inicial s, v atual 0 !=0
            path.add(0, v); //insere no inicio do arraylist
            v = edgeTo[v];
        }
        path.add(0, s); //insere vertice inicial
        return path;
    }

    public void dfs(Graph g, int v){
        //1. marcar visitado
        //2. chamar recursivamente, os que não foram visitados e sao adjacentes.
      // System.out.println("Estou em v: " + v);

        marked[v] = true;
        for (int w : g.adj(v)){
            // CHAMA RECURSIVO SE NAO ESTIVER MARCADO
            if (!marked[w]) {
                edgeTo[w] = v; // estou em w, de onde eu vim?!
                //visita - recurso
                dfs(g, w);
            }
        }
        //System.out.println("Terminei v: " + v);
    }

    public static void main(String[] args) {
//        Graph G = new Graph(4);
//        G.addEdge(0, 1);
//        G.addEdge(1, 2);
//        G.addEdge(0, 3);
//        StdOut.println(G)
//        if (dp.hasPathTo(2)){
//            for (int v: dp.pathTo(2)){
//                System.out.println(v + " ");
//            }
//        }

        String filename = "tinyG.txt";
        In in = new In(filename);
        Graph g = new Graph(in);
//        StdOut.println(G);
        DepthFirstSearch33 dp = new DepthFirstSearch33(g, 0);//comecar pelo 0
//        if (dp.hasPathTo(3)) {
//            for (int v : dp.pathTo(3)) {
//                System.out.println(v + " ");
//            }
//        }

        System.out.println("Caminhos existentes:");
        System.out.println("0 : Vértice Inicial");
        //percorrer todos os vertices
        for (int v = 1; v < g.V(); v++){
            //se tem caminho para v, ele passa por cada um dos caminhos
            if (dp.hasPathTo(v)){
                System.out.print(v + ": ");
                for (int w : dp.pathTo(v)){
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        }

    }


}
