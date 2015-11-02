/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside;

public class Main  
{
    public static void main(String... args)
    {
        new Main().go();
        int[] arr2 = {};
        Integer[][] arr = new Integer[56][34];
        short s = 2311;
        Double d1 = 4.2;
        Double d2 = 4.2;
        byte b = 10;
        Byte b1 = 10;
        short s1 = 10;
        Short s2 = 10;
        char ch = 10;
//        Float f = 4.2;
        if(s1 == d1){
            System.out.println("B");
        }
        if(d1 == d2){
            System.out.println("M");
        }
    }
    
    public void go()
    {
//        ripper(10,11,15,-1);
    }
    
    public void ripper(short... args)
    {
        System.out.print(args.length);
    }    
    
    //Ternary Operators are tricky. If present in sout, check whether they return void or not
}
