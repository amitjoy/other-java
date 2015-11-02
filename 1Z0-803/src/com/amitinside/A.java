package com.amitinside;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class A {
    
    A(){
        
    }
    public A(int x){
        System.out.println("A");
    }
    
    public void getCalc(){
        System.out.println("A"); 
    }
    static int[] a = {1,2};
    static{
        a = new int[]{1,2};
    }
    File f = new File("");
    
    public Number getAdd() throws Exception{
        int[] a;
        a = new int[]{2,3};
        String[] s[];
        boolean b = true;
        byte b8 = 2;
        if(true)
            throw new Exception();
        b8 = (byte) (b?1:0);
        try{
            int x = 9;
        return 2;
        }
        catch(Exception e){
            return 4;
        }
        finally{
            return 5;
        }
        List l3 = new ArrayList();
        l3.add(0067);
    }

}
