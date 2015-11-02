/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author SESA249907
 */
public class OCAJP2 implements interface2 {

    /**
     * @param args the command line arguments
     */
    public OCAJP2() {
        super();
        this.d = 1;
    }
    char d = 0;
    int g = d;

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("aAaA");
        strings.add("AaA");
        strings.add("aAa");
        strings.add("AAaa");
        Collections.sort(strings);
        for (String s : strings) {
            System.out.print(s + " ");
        }
        double d = 12.345;
        OCAJP2 ocajp2 = new OCAJP2();
        ocajp2.doStuff(6);
//        System.out.printf("%3.3d",d);
        int j = 9;
        j = ++j;
        System.out.println(j);
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            continue;
        }
        do {
            ;
        } while (true);
    }

    public void doStuff(int s) {
        s += EASY + ++s;
        String str = "A";
//int str3 = (Integer)str.toLowerCase();
        System.out.println("s " + s);
//List<String> str = new ArrayList<>();
    }

    public Object foo() {
        return this;
    }
}
