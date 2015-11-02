package com.amitinside;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class NewMain{

    public static void main(String... args) {
     //   ArrayList<Object> ett = new ArrayList<B>();
        //new B(10);
        int a = 10;
        if(a > 90){
            
        }
        else if(a == 10)
            System.out.println("done");
        else if(a != 9)
            System.out.println("done2");
        else if(a > 9)
            System.out.println("done 3");
        else System.out.println("done4");
//        A a = new B();
//        A a1 = (A)a;
//        a.getCalc();
        System.out.println("-----");
        ((A)new B()).getCalc();
        System.out.println("____");
        Collection c = new ArrayList();
        StringBuilder sb = null;
        List<Integer> l3 = new ArrayList<Integer>();
//        l3.add("0067");
        addTOList(l3);
        System.out.println(l3.get(0));
        long t = 0;
//        Object a[] = new int[]{1,2,3};
        boolean b = true;
        byte b8 = 2;
        b8 = (byte) (b?1:0);
        System.out.println("------"+b8);
//        a[t] = 3;
        System.out.println(l3);
        try{
        Thread.sleep(3);
        do while(true);
        }
        catch(Exception e){
            
        }
        Thread.currentThread().interrupt();
//        sb.
        block: {
           break block;
        }
        {
            {
                
            }
        }
    }

    private static void addTOList(List l3) {
        l3.add("gh"); 
    }

}
