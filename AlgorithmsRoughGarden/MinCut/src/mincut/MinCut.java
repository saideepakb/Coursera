/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mincut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static mincut.MinCutCount.minimumCount;


/**
 *
 * @author sbhimara
 */
public class MinCut {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        int minCount = minimumCount();
        System.out.println("minCount "+ minCount);
        
//        System.out.println(inputGraph);
    
    }
    
    public static Map<Long, List<Long>> readFile(String fileName)
    {
        
        Map<Long, List<Long>> graph = new HashMap<>();
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            long i=1;
            while ((text = reader.readLine()) != null) {
                int start = 0;
//                String text1 = text.replaceAll("\t", "d");
                
                List<Long> list = new ArrayList<>();
                while (text.contains("\t")) {
                    int tabIndex = text.indexOf("\t");
                    list.add(Long.parseLong(text.substring(start, tabIndex)));                    
                    text = text.substring(tabIndex + 1);
                    
                }
                list.remove(0);
                graph.put(i, list);
                i++;
//                System.out.println(list2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return graph;
    }
    
}
