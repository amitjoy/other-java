/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside;

/**
 *
 * @author AMIT
 */
public class ClassB extends ClassC implements Cloneable {

    public String name;
    public ClassC classc;

    public ClassB() {
        name = "B";
        classc = new ClassC();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ClassB classb = (ClassB) super.clone();
        classc = (ClassC) classb.classc.clone();
        return classb;
    }
}
