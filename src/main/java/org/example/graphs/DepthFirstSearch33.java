package org.example.graphs;

import java.util.ArrayList;

public class DepthFirstSearch33 {
    //2 vetores
    // marked
    //edgeTo
    private boolean[] marked;
    private  int[] edgeTo;

    private int s; //vertice inicial


    public DepthFirstSearch33(Digraph g, int s){
        this.s = s;
        //inicializar vetores
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        //comeca o caminhamento
        for(int i = 0; i < g.getV(); i++) {
            if(!hasPathTo(i)) {
                dfs(g, i);
            }
        }
    }

    //Se tem caminho?! diz se tem caminh ou nao
    public boolean hasPathTo (int v){
        return marked[v];
    }

    // quem é o caminho
    public ArrayList<Integer> pathTo (int v, int s){
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

    public void dfs(Digraph g, int v){
        //1. marcar visitado
        //2. chamar recursivamente, os que não foram visitados e sao adjacentes.
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
}
