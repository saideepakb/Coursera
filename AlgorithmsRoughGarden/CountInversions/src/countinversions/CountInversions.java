/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package countinversions;

import static countinversions.Count.startCount;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sbhimara
 */
public class CountInversions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "D:\\Coursera\\Algorithms\\Programs\\IntegerArray.txt";
 //       String fileName = "D:\\Coursera\\Algorithms\\Programs\\Test.txt";
        List<Long> inputList = readFile(fileName);
        long invCount = startCount(inputList);
        System.out.println(invCount);
    }       
    
    public static List<Long> readFile(String fileName)
    {
        List<Long> list = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Long.parseLong(text));
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
        return list;
    }
}

    
