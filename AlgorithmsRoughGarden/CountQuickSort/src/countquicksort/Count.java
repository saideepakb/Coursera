package countquicksort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Count {
    
    public static long startCount(List<Long> inputList) {
        List<Long> countComp = new ArrayList<>();
        List<List<Long>> newList = new ArrayList<>();
        
        countComp.add((long)0);
        
        newList.add(inputList);
        newList.add(countComp);
        
        long numberOfComparisons = quickSort(newList).get(1).get(0);
        return numberOfComparisons;
//        return quickSort(newList).get(0);
    }
    
    
    public static List<List<Long>> quickSort(List<List<Long>> inputList) {
        List<Long> sortedList = inputList.get(0);
        
        int listSize = sortedList.size();
        
        
        if (listSize < 2) {
            return inputList;
        } else {     
            int mIndex = findMedian(sortedList);
            Collections.swap(sortedList, 0, mIndex);
           
            List<Long> left = new ArrayList<>();
            List<Long> right = new ArrayList<>();
            List<Long> pivotList = new ArrayList<>();
            List<Long> returnList = new ArrayList<>();
            List<Long> count = new ArrayList<>();
            
            int p = 0;
            
            long pivot = sortedList.get(p);
            int i = 1, j = 1;
            while(j < listSize){
                if (sortedList.get(j) < pivot){
                    Collections.swap(sortedList, i, j);
                    i++;
                } 
                j++;
            }

            Collections.swap(sortedList, i-1, p);
            pivotList.add(sortedList.get(i-1));
            
//            System.out.println(inputList);
            
            inputList.set(0, sortedList);
            left.addAll(sortedList.subList(0,i-1));
            right.addAll(sortedList.subList(i,listSize));
            
            List<List<Long>> leftC = new ArrayList<>();
            List<List<Long>> rightC = new ArrayList<>();
            List<List<Long>> returnListC = new ArrayList<>();            
            
            leftC.add(left);
            rightC.add(right);

            List<Long> c = new ArrayList<>();
            List<Long> d = new ArrayList<>();
            c.add((long)0);
            d.add((long)0);
            
            leftC.add(c);
            rightC.add(d);
            
            leftC = quickSort(leftC);
            rightC = quickSort(rightC);
            
            
            returnList.addAll(leftC.get(0));
            returnList.addAll(pivotList);
            returnList.addAll(rightC.get(0));
            
            returnListC.add(returnList);
            
            long numCount = (long)listSize + inputList.get(1).get(0) + leftC.get(1).get(0) + rightC.get(1).get(0) -1;
            
            count.add(numCount);
            returnListC.add(count);
            
            return returnListC;
        }
    }
    
    public static int findMedian (List<Long> sortedList) {
        int listSize = sortedList.size();
        if (listSize == 2) {
            return 0;
        } else {
            int k;
            if (listSize%2 == 0) {
                k = (listSize/2) - 1;
            } else{
                k = listSize/2;
            }

            List<Long> threePointList = new ArrayList<>();
            threePointList.add(sortedList.get(0));
            threePointList.add(sortedList.get(k));
            threePointList.add(sortedList.get(listSize-1));
            Collections.sort(threePointList);
            
            long a = threePointList.get(1);
          //  System.out.println(copyOfThreePointList);
            if (a == sortedList.get(0)) {
                return 0;
            } else if (a == sortedList.get(listSize-1)) {
                return (listSize - 1);
            } else return k;
    }
    }
}
