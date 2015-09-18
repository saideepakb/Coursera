/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package countinversions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sbhimara
 */
public class Count {
    
    public static long startCount(List<Long> inputList) {
        List<Long> countInv = new ArrayList<>();
        List<List<Long>> newList = new ArrayList<>();
        
        newList.add(inputList);
        newList.add(countInv);
        
        long numberOfInversions = count(newList).get(1).get(0);
        return numberOfInversions;
    }
    
    public static List<List<Long>> count(List<List<Long>> inputList){
      //  System.out.println("INPUTLIST" + inputList);
        int lengthList = (inputList.get(0)).size();
        if (lengthList<=1) {
            List<List<Long>> returnList = new ArrayList<>();
            List<Long> nullList = new ArrayList<>();
            nullList.add((long)0);
            returnList.add(inputList.get(0));
            returnList.add(nullList);
            return returnList;
            
        } else {
            int midPoint = lengthList/2;
            List<List<Long>> firstHalf = new ArrayList<>();
            List<List<Long>> secondHalf = new ArrayList<>();
            List<List<Long>> sortedList = new ArrayList<>();
            
            firstHalf.add(((List<Long>)inputList.get(0)).subList(0, midPoint));
            secondHalf.add(((List<Long>)inputList.get(0)).subList(midPoint, lengthList));
            List<Long> countList = new ArrayList<>();
            countList.add((long)0);
            firstHalf.add(countList);
            secondHalf.add(countList);
            
            firstHalf = count(firstHalf);
            secondHalf = count(secondHalf);
            sortedList = countSplitInv(firstHalf, secondHalf);
            countList.set(0, secondHalf.get(1).get(0) + sortedList.get(1).get(0) + firstHalf.get(1).get(0));
            sortedList.set(1, countList);
            return sortedList;
            }
        
    }
 
    public static List<List<Long>> countSplitInv(List<List<Long>> firstHalf, List<List<Long>> secondHalf) {
        List<Long> firstHalfInt = firstHalf.get(0);
        List<Long> secondHalfInt = secondHalf.get(0);
        int fHLen=firstHalfInt.size();
        
        int sHLen = secondHalfInt.size();
        List<Long> sortedList = new ArrayList<>();
        int i=0,j=0;
        long k=0;        
        while (i < fHLen && j < sHLen){
            Long fE = firstHalfInt.get(i);
            Long sE = secondHalfInt.get(j);
            if (fE <= sE) {
                sortedList.add(fE);
                i++;
            } else {
                k = k + fHLen - i;
                sortedList.add(sE);
                j++;
            }
        }
            
        while(i<fHLen) {
                sortedList.add(firstHalfInt.get(i));
                i++;
        }
        while(j<sHLen) {
                sortedList.add(secondHalfInt.get(j));
                j++;
        }
        //System.out.println(sortedList);
        List<List<Long>> returnList = new ArrayList<>();
        returnList.add(sortedList);
        List<Long> countList = new ArrayList<>();
        countList.add(k);
        returnList.add(countList);
        return returnList;
    }
}
