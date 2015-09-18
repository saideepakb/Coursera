

/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threesum;

import edu.princeton.cs.introcs.*;


/**
 *
 * @author Deepak
 */
public class ThreeSum {
    
    public static int count(int[] a) {
        
        int N = a.length;
        int cnt = 0;
        
        for (int i=0; i< N; i++)
            for (int j=i+1; j< N; j++)
                for (int k=j+1; k< N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        In in = new In("D:\\Coursera\\AlgorithmsSedgewick\\Code\\ThreeSum\\src\\threesum\\4Kints.txt");
        int[] a = in.readAllInts();
        StdOut.println(count(a));
        
                
    }    
}