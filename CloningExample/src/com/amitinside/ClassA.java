/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside;

/**
 *
 * @author AMIT
 */
public class ClassA extends ClassB implements Cloneable {

    public String name;
    public ClassB classb;
    
    public ClassA(){
        classb = new ClassB();
        name = "A";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ClassA classa = (ClassA) super.clone();
        classb = (ClassB) classa.classb.clone();
        return classa;
    }
}
