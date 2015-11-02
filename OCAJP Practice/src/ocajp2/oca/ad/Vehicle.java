/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca.ad;

/**
 *
 * @author SESA249907
 */
class Vehicle {
    public void printSound() {
        System.out.print("vehicle");
    }
}

class Car extends Vehicle {
    public void printSound() {
        System.out.print("car");
    }
}

class Bike extends Vehicle {
    public void printSound() {
        System.out.print("bike");
    }
}

class Test {
    public static void main(String[] args) {
        Vehicle v = new Car();
        Car c = (Car) v;
        
        v.printSound();
        c.printSound();
    }   
}
