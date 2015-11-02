/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca;

/**
 *
 * @author SESA249907
 */
public class Dog extends Animal{

    public Dog(int x) {
        super(x);
    }

    public Dog() {
    }
    
    
    
    public final void getName(){
        Dog a = new Dog(10);
//        a.gamble();
//        System.out.println(a.a);
    }
    public void gamble(final int k){
//        Animal.gamble(10);
        System.out.println("Dog");
    }
}
