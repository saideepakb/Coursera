/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hashtable;

/**
 *
 * @author sbhimara
 */
import java.io.*;
import java.util.*;

class HashTable{
    public Hashtable d = new Hashtable();
    public final int N = 1000000;
    public final String fileName = "D:\\Coursera\\Algorithms\\Programs\\algo1-programming_prob-2sum.txt";
    public long[] a = new long[N];
    public int count = 0;

    HashTable() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str;
        int k = 0;
        
        // store all numbers in an array as well as hash them into a hash table
        while((str = br.readLine()) != null){
            //System.out.println(str);
            a[k] = Long.parseLong(str);
            d.put(new Long(a[k]), new Long(a[k]));
            k++;
            }
        br.close();
    }

    public void find(int sum){
        
        System.out.println(sum);
        // for each element "ele" in the array
        for (int i=0; i<a.length; i++){

            // if (sum-ele) is present in the hash table, you have found a match
            if(d.containsKey(new Long(sum - a[i])) == true && a[i] != (sum - a[i])) {count++; break;}
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        HashTable h = new HashTable();
        for (int i=-10000; i<=10000; i++) h.find(i);
        System.out.println(h.count);
    }
}
