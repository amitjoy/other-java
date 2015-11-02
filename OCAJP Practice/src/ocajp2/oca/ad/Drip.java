/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca.ad;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author SESA249907
 */
public class Drip //implements Comparable
{

    public static void main(String args[]) 
    {
        System.out.println(new StringBuilder("A").equals(new StringBuilder("A")));
        Set <Drip> set = new TreeSet<Drip>();
        set.add(new Drip());
        for (Drip d : set)
        {
            System.out.print(d);
        }
    }
    
//    public String toString()
//    {
//        return "Hi";
//    }
//
////    @Override
//    public int compareTo(Object o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}