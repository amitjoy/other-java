package com.amitinside;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class B extends A{

    private final String stt;
    int _5 = 9;
    
    B(){
    stt = "Str";   
    
    }
     B(int x){
//         double f = 1.3f;
        super(x);
        stt = "";
        System.out.println("B");
    }
    public final void getCalc(){
        int vb = 10_10;
        System.out.println("B");
    }
     
    public Integer getAdd(){
        return 4;
    }
}
