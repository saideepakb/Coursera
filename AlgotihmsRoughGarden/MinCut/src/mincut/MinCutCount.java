/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mincut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import static mincut.MinCut.readFile;

/**
 *
 * @author sbhimara
 */
public class MinCutCount {
        public static int minimumCount() {
            int i=1;
            int count;
            int min = 0;
            while (i<100000){
                String fileName = "D:\\Coursera\\Algorithms\\Programs\\kargerMinCut.txt";
                Map<Long, List<Long>> input = readFile(fileName);
//                System.out.println("\nLoop Number" + i);                
//                System.out.println("INPUT is " + input);
                count = startCount(input);
                if (i==1) {
                    min = count;
                } 
                if (min > count) {
                    min = count;
                }
                i++;
                
            }
//            System.out.println("Ad Hoc min is" + min);
            return min;
        }
        
        private static int startCount(Map<Long, List<Long>> input) {                            
            while (input.size() > 2) {
  //              System.out.println("Number of nodes " + input.size());
                List<Long> edgeToContract = selectRandomEdge(input);
//                System.out.println("Graph is " + input);
//                System.out.println("Edge is " + edgeToContract);
                input = mergeVertices(edgeToContract, input);             
            }
            Long[] key = input.keySet().toArray(new Long[2]);
            return input.get(key[0]).size();
        }
        
        private static List<Long> selectRandomEdge(Map<Long, List<Long>> input) {
            List<Long> edge = new ArrayList<>();
            
            Set<Long> keySet = input.keySet();
            Long[] keySetArray = keySet.toArray(new Long[keySet.size()]);
            int idx = new Random().nextInt(keySetArray.length);
            long firstNode = (keySetArray[idx]);
            
            List<Long> listFirstNode = input.get(firstNode);
            int jdx = new Random().nextInt(listFirstNode.size());
            long secondNode = listFirstNode.get(jdx);
            
            edge.add(firstNode);
            edge.add(secondNode);
            Collections.sort(edge);
            return edge;
        }
        
        private static Map<Long, List<Long>> mergeVertices (List<Long> edgeToContract, Map<Long, List<Long>> input) {
            long firstNode = edgeToContract.get(0);
            long secondNode = edgeToContract.get(1);
            
            List<Long> firstNodeList = input.get(firstNode);
            List<Long> secondNodeList = input.get(secondNode);
            
//            System.out.println(firstNode + " first node");
//           System.out.println(secondNode + " second node");
            
            
//            if (firstNodeList == null){
//              System.out.println("BYEJYVGEUY");  
//            }
                        
            while (firstNodeList.contains(secondNode)) {
                firstNodeList.remove(secondNode);
            }
            while (secondNodeList.contains(firstNode)) {
                secondNodeList.remove(firstNode);
            }
            
            for (long key: input.keySet()){
                while (input.get(key).contains(firstNode)){
                    int i = input.get(key).indexOf(firstNode);
                    input.get(key).set(i, secondNode);
                }
            }
            
            secondNodeList.addAll(firstNodeList);
            input.remove(firstNode);
            input.put(secondNode, secondNodeList);
            return input;
        }        
}