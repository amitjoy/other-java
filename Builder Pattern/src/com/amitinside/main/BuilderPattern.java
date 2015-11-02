/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside.main;

import com.amitinside.builder.Employee;

/**
 *
 * @author AMIT
 */
public class BuilderPattern {

    public static void main(String[] args) {
        Employee employee = new Employee.Builder("AMIT", "MONDAL").age(20).designation("GET").address(null).build();
        System.out.println(employee);
    }
}
