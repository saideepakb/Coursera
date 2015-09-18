/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bfsmain;

import java.io.*;
import java.util.*;
import static java.util.Arrays.sort;

/**
 *
 * @author sbhimara
 */
public class BfsMain {
    public int t;
    public int s;
    public final int N = 875715;
    public int[] visited = new int[N];
    public int[] finish = new int[N];
    public int[] leader = new int[N];
    
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fileName = "D:\\Coursera\\Algorithms\\Programs\\SCC.txt";
        List<Map<Integer, List<Integer>>> inputGraph = createGraph(fileName);
    //    System.out.println("The input graphs are" + inputGraph);
        countScc(inputGraph);
    }
    
    public static void countScc(List<Map<Integer, List<Integer>>> inputGraphList) {
        BfsMain kos1 = dfsLoop(inputGraphList.get(1), null);
  //      System.out.println("Finish times for the reversed graph are" + Arrays.toString(kos1.finish));
                
        BfsMain kos2 = dfsLoop(inputGraphList.get(0), kos1.finish);
   //     System.out.println("Finish times for the normal graph are" + Arrays.toString(kos2.finish));
    //    System.out.println(Arrays.toString(kos2.leader));
        int key;
        int value;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i=0; i<kos2.leader.length;i++) {
            key = kos2.leader[i];
            if (countMap.containsKey(key)) {
                value = countMap.get(key);
                countMap.put(key, value + 1);
            } else {
                countMap.put(key, 1);
            }
             
        }
        
        Collection<Integer> listValues = countMap.values();
        List<Integer> valueList = new ArrayList<>(); 
        for (int values : listValues) {
            valueList.add(values);
        }
        Collections.sort(valueList);
//        System.out.println(valueList);
        for (int i = valueList.size()-1;i>valueList.size()-6;i--){
            System.out.println(valueList.get(i));
        }        
    }
    
    public static BfsMain dfsLoop(Map<Integer, List<Integer>> inputGraph, int[] finishTime) {
        BfsMain kos = new BfsMain();
        kos.t = 0;
        kos.s = -1;
        
        if (finishTime==null) {
            for (int i = 875714; i > 0; i--) {
                if (kos.visited[i] == 0) {
                    kos.s = i;            
                    dfs(inputGraph, kos, i);
                }             
            }
        } else {
            TreeMap<Integer, Integer> finishMap = new TreeMap<>(); 
            int l = finishTime.length;
            for (int i=1;i<l;i++) {
                finishMap.put(finishTime[i],i);
            }
            
            NavigableSet<Integer> finishSet = finishMap.descendingKeySet();
            for (int s : finishSet) {
                int j = finishMap.get(s);
                if (kos.visited[j]==0) {
                    kos.s = j;            
                    dfs(inputGraph, kos, j);
                }
            }
        }
        return kos;
    }
    
    public static void dfs(Map<Integer, List<Integer>> inputGraph, BfsMain kos, int i) {
        kos.visited[i] = 1;
        kos.leader[i] = kos.s;
       // System.out.println("kos.leader " + kos.leader[i]);
//        if (inputGraph!=null) {
//            System.out.println("INPUT GRAPH IS ");
//        }
        try {
        for (int elem: inputGraph.get(i)) {
            
            if(kos.visited[elem] == 0) {
                dfs(inputGraph, kos, elem);
                
            }
        } }
        catch (NullPointerException e) {
            
        }
        
        
        kos.t++;        
        kos.finish[i]=kos.t;
      //  System.out.println("Finish time " + kos.finish[i]);
    }    
    
    public static List<Map<Integer, List<Integer>>> createGraph(String fileName) throws FileNotFoundException, IOException
    {
        File file = new File(fileName);
        BufferedReader reader = null;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, List<Integer>> revGraph = new HashMap<>();
        List<Map<Integer, List<Integer>>> graphList = new ArrayList<>();
        
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            int i = 1;
            while ((text = reader.readLine()) != null) {
//                System.out.println(text);

                int firstSpace = text.indexOf(" ");
                int key = Integer.parseInt(text.substring(0, firstSpace));
                text = text.substring(firstSpace +1);
                int secondSpace = text.indexOf(" ");
                int value = Integer.parseInt(text.substring(0, secondSpace));
                
                if (!graph.containsKey(key)) {
                    List<Integer> list = new ArrayList<>();
                    graph.put(key, list);
                    graph.get(key).add(value);
                } else {
                    graph.get(key).add(value);
                }                
                
                if (!revGraph.containsKey(value)) {
                    List<Integer> list = new ArrayList<>();
                    revGraph.put(value, list);
                    revGraph.get(value).add(key);
                } else {
                    revGraph.get(value).add(key);
                }
                i++;
            }             
        
//        System.out.println(graph);
        graphList.add(graph);
        graphList.add(revGraph);
        return(graphList);
    }    
}
