/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside;

/**
 *
 * @author AMIT
 */
public class ClassC implements Cloneable{
    
    public String name;
    
    public ClassC(){
        name = "C";
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        ClassC classc = (ClassC)super.clone();
        return classc;
        
    }
    
}
