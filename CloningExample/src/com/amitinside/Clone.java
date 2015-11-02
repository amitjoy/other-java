/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside;

/**
 * @author AMIT
 */
public class Clone {
    
    public ClassA classa;

    public static void main(String[] args) throws CloneNotSupportedException {
        Clone cl = new Clone();
        cl.classa = new ClassA();
        ClassA cloneOfClassA = (ClassA)cl.classa.clone();
        cl.classa.name = "AMIT";
        cl.classa.classb.name = "ANIT";
        cl.classa.classb.classc.name = "MONDAL";
        System.out.println(cloneOfClassA.name);
        System.out.println(cloneOfClassA.classb.name);
        System.out.println(cloneOfClassA.classb.classc.name);
    }
}
