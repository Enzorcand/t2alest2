package org.example;

import lombok.Data;
import org.example.graphs.Digraph;

import java.io.File;
import java.util.*;

@Data
public class GraphReader {
    private Scanner scanner;
    private Digraph digraph;
    HashMap<Integer, ArrayList<Integer>> map;

    public int biggerWay(){
        int maior = 0;
        for (int i = 0; i < digraph.getV(); i++) {
            int temp = dfsInicial(i);
            if(temp > maior){
                maior = temp;
            }
        }
        return maior;
    }

    private int[] temp;

    public int dfsInicial(int v) {
        temp = new int[digraph.V()];  // Supondo que digraph.V() retorna o número de vértices no grafo
        Arrays.fill(temp, -1);  // Inicializa o array de memorização com -1, indicando que nenhum vértice foi processado ainda
        return dfsRecursivo(v) + 1;
    }

    private int dfsRecursivo(int v) {
        if (temp[v] != -1) {
            return temp[v];  // Retorna o valor memorizado se já foi calculado
        }

        int maior = 0;
        for (int w : digraph.adj(v)) {
            maior = Math.max(maior, 1 + dfsRecursivo(w));
        }

        temp[v] = maior;  // Armazena o resultado no array de memoização
        return maior;
    }

    public int solucaoIneficiente(int v){
        int maior = 0;
        for (int w : digraph.adj(v)) {
            int result = 0;
                result++;
                int temp = dfsRecursivo(w, result);
                if(temp > maior){
                    maior = temp;
                }
        }
        return maior;
    }

    private int dfsRecursivo(int v, int result){
        int maior = 0;
        if(digraph.adj(v) != null) {
            result++;
            for (int w: digraph.adj(v)) {
                int temp = dfsRecursivo(w, result);
                if(temp > maior){
                    maior = temp;
                }
            }
        }
        if (result < maior){
            result = maior;
        }
        return result;
    }

    public void setEdges(){
        Set<Integer> set = map.keySet();
        int n = 0;
        for (int key : set) {
            List<Integer> size = map.get(key);
            for (int key2 : set) {
                List<Integer> size2 = map.get(key2);
                if(isBigger(size, size2)){
                    digraph.addEdge(key, key2);
                    n++;
                }
            }
        }
        System.out.println(n);
    }
    private boolean isBigger(List<Integer> size, List<Integer> size2) {
        int n = 3;
        for (int i = 0; i < 3; i++) {
            int m = 0;
            for (int j = 0; j < 3; j++) {
                if(size.get(i) < size2.get(j)){
                    m++;
                }
            }
            if(m < n){
                return false;
            }
            n--;
        }
        return true;
    }

    public void readArchive(int size) {
        try {
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            File file = new File("src/main/java/org/example/testes/teste"+ size +".txt");
            scanner = new Scanner(file);
            int i = 0;
            while(scanner.hasNext()){
                String str = scanner.nextLine();
                ArrayList<Integer> arr = toList(str);
                Collections.sort(arr);
                map.put(i, arr);
                i++;
            }
             this.map = map;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private ArrayList<Integer> toList(String str) {
        String[] arr = str.split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }
}
