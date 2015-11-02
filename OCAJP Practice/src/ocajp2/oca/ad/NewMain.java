/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca.ad;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NewMain {
    
    private String str;
    
    public NewMain(String str) {
        this.str = str;
    }
    
    @Override
    public String toString() {
        return this.str;
    }
        
    public static void main(String args[]) {
        NewMain h1 = new NewMain("2");        
        String s1 = new String("1");        
        
        List list = new LinkedList<Object>();
//        List<Object> list = new LinkedList<Object>();
        list.add(h1);
        list.add(s1);
        //it should also has values of similar type
        Collections.sort(list);
        for (Object o : list) {
            System.out.print(o + " ");
        }
    }
}