/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside.builder;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final String designation;
    private final String middleName;
    private final int age;
    private final String address;
    private static final AtomicInteger counter = new AtomicInteger();

    public static class Builder {
        /*
         * Required Properties
         */

        private final String firstName;
        private final String lastName;
        /*
         *  Optional Properties
         */
        private String designation = "";
        private String middleName = "";
        private int age = 0;
        private String address = "";

        public Builder(String fName, String lName) {
            this.firstName = fName;
            this.lastName = lName;
        }

        public Builder middleName(String mName) {
            middleName = mName;
            return this;
        }

        public Builder designation(String dsgn) {
            designation = dsgn;
            return this;
        }

        public Builder age(int a) {
            age = a;
            return this;
        }

        public Builder address(String addr) {
            address = addr;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    private Employee(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        middleName = builder.middleName;
        designation = builder.designation;
        address = builder.address;
        age = builder.age;
        counter.incrementAndGet();
    }
}
