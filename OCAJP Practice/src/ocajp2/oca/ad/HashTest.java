/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca.ad;

import java.util.Arrays;
import java.util.TreeSet;

/**
 *
 * @author SESA249907
 */
public class HashTest {
    
    int a = b = getVal();  //OK  
//  int b;
  
  {  
      b = 10;  
  }  
  int getVal()  
  {  
     return b;  
  }  
//  int a = getVal();
  int b;
    
    private String str;
    
    public HashTest(String str) {
        this.str = str;
    }
    
    @Override
    public String toString() {
        return this.str;
    }
    
    public static void book(short a) {
        System.out.print("short ");
    }
    
    public static void book(Short a) {
        System.out.print("SHORT ");
    }
    
    public static void book(Long a) {
        System.out.print("LONG ");
    }
        
    public static void main(String args[]) {
        Object [] object = new String[5][5];
        HashTest h1 = new HashTest("2");        
        String s1 = new String("1");        
        
        Object arr[] = new Object[2];
        arr[0] = h1;
        arr[1] = s1;
        //must be of equal type for sorting
//        Arrays.sort(arr);
        for (Object o : arr) {
            System.out.print(o + " ");
        }
        
        short shortRoom = 1;
        int intRoom = 2;
        
        book(shortRoom);
//        book(intRoom);
        
        TreeSet<Integer> s = new TreeSet<Integer>();
        TreeSet<Integer> subs = new TreeSet<Integer>();
        for (int i = 606; i < 613; i++) {
            if(i%2 == 0){
                s.add(i);
            }
            
        }
        subs = (TreeSet)s.subSet(608, true, 610, true);
        s.add(629);
        System.out.println(s + " " + subs);
        
        Double d = 4.2;
        Double d1 = 4.2;
        
        if(d == d1)
            System.out.println("DFG");
    }
    
//     class amit{
//        static int a = 10;
//        
//    }
}
