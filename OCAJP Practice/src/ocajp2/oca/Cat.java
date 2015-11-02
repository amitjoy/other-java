/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca;

/**
 *
 * @author SESA249907
 */
public class Cat extends Animal {
    public static void an(){
        final int a ;
        boolean b = getVal();
        if(b)
            a=10;
        else
            a=20;
        System.out.println(a);
    }

    private static boolean getVal() {
        return true;
    }
    
}
