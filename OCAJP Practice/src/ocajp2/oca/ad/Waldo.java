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
public class Waldo   
{
    public static void main(String args[]) 
    {
        Set s = new TreeSet();
        s.add("a");
        s.add("C");
        s.add(" d");
        s.add("b");
//        s.add(null);
        Object [] o =  s.toArray();
        for (Object object : o)
        {
            System.out.print(object + " ");
        }
    }
}
