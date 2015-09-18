/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstrashortestpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author sbhimara
 */
public class DijkstraShortestPath {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        long startTime, endTime; 
        startTime = System.currentTimeMillis(); 
        String fileName = "D:\\Coursera\\Algorithms\\Programs\\dijkstraData.txt"; 
        Map<Integer, List<List<Integer>>> inputGraph = createGraph(fileName);
       // System.out.println(inputGraph);
        Map<Integer, Integer> shortestDistances = dijkstra(inputGraph);
      //  System.out.println("The shortest distances are " + shortestDistances);
        
        
        int[] problem = {7,37,59,82,99,115,133,165,188,197};
       for (int i : problem) {
            System.out.println(shortestDistances.get(i));
        }
        
        endTime = System.currentTimeMillis(); 

        System.out.println("It took " + (endTime - startTime) + " milliseconds"); 
    }
    
    public static Map<Integer, Integer> dijkstra(Map<Integer, List<List<Integer>>> inputGraph) throws java.lang.NullPointerException {
        
        
        List<Integer> processedVertices = new ArrayList<>();
        Map<Integer, Integer> shortDist = new HashMap<>();
        
        int dist = 0;
        shortDist.put(1, 0);
        processedVertices.add(1);
        
        int mapSize = inputGraph.size();
        
        while(processedVertices.size() <= mapSize){
            TreeMap<Integer, Integer> abcd = new TreeMap<>();
            for (int i=0 ; i < processedVertices.size();i++){
                int node = processedVertices.get(i);
         //       System.out.println("List of processed vertices are " + processedVertices);
           //     System.out.println("node is " + node);
                
             //   System.out.println("Vertices from " + node + " are " + inputGraph.get(node));
                for (List<Integer> vertexProp : inputGraph.get(node)) {
                    
               //     System.out.println("vertex prop is " + vertexProp);
                    if (!processedVertices.contains(vertexProp.get(0))) {
                        int vertex = vertexProp.get(0);
                 //       System.out.println("Vertex is " + vertex);
                        int existingLength = shortDist.get(node);
                        int lengthVW = vertexProp.get(1);
                        dist = existingLength + lengthVW; 
                   //     System.out.println("total distance of vertex " + vertex + " is " + dist);
                        abcd.put(dist,vertex);                         
                    }
                    
                }
//                System.out.println("list of weights for node " + node +" are " + abcd);
                
            }
            try {
                int shortestDistance = abcd.firstEntry().getKey ();
                int vertex = abcd.firstEntry().getValue();
                
                processedVertices.add(vertex);
                shortDist.put(vertex, shortestDistance);
            } catch (NullPointerException e) {
                
            }
  //          System.out.println("");
        }         
        return shortDist;
    }
    
    
    static Map<Integer, List<List<Integer>>> createGraph(String fileName) throws FileNotFoundException, IOException {
                
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String text = null;
        Map<Integer, List<List<Integer>>> inputGraph = new HashMap<>();
        while ((text = reader.readLine()) != null) {
            List<List<Integer>> firstList = new ArrayList<>();
            int firstSpace = text.indexOf("\t");
            //System.out.println("first tab is " + firstSpace);
            int key = Integer.parseInt(text.substring(0, firstSpace));
            text = text.substring(firstSpace + 1);
            
            while (text != "\t") {
                List<Integer> secondList = new ArrayList<>();
                
                int comma = text.indexOf(",");
                if (comma==-1) 
                {
                    break;
                }
               // System.out.println("comma is " + comma);
                int vertex = Integer.parseInt(text.substring(0, comma));
                
                text = text.substring(comma+1);
                int space = text.indexOf("\t");
                //System.out.println("Tab is " + space);
                int weight = Integer.parseInt(text.substring(0, space));
                
                text = text.substring(space+1);
                secondList.add(vertex);
                secondList.add(weight);
                
                firstList.add(secondList);                
            }            
            inputGraph.put(key,firstList);            
        }        
        return inputGraph;        
    }        
}
