/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Hotel {
    public int bookings;
    public void book() {
        bookings++;
    }
}

public class SuperHotel extends Hotel {
    public void book() {
        bookings--;
    }
    
    public void book(int size) {
        book();
        super.book();
        bookings += size;
    }
    
    public static void getList(List<Object> list){
        System.out.println("A");
    }
    public static void getList2(List list){
        System.out.println("B");
    }
    public static void getList3(List<String> list){
        System.out.println("C");
    }
    
    public static void main(String args[]) {
        List<String> listStr = new ArrayList<>();
        listStr.add("AMIT");
        listStr.add("ANIT");
        listStr.add("KUMAR");
        listStr.add("MONDAL");
        System.out.println("********************");
        getList(null);
        System.out.println("********************");
        List listStr2 = new ArrayList<Integer>();
        List<Object> listStr3 = new ArrayList<>();
        listStr2.add(12);
//        listStr2.add("ANIT");
//        listStr2.add("KUMAR");
//        listStr2.add("MONDAL");
        SuperHotel hotel = new SuperHotel();
        hotel.book(2);
        System.out.print(hotel.bookings);
        getList3(listStr);
        getList2(listStr);
        getList(listStr2);
        
         LinkedList<String> list = new LinkedList<String>();
        list.add("BbB1");
        list.add("bBb2");
        list.add("bbB3");
        list.add("BBb4");
        Collections.sort(list);
        for (String str : list) {
            System.out.print(str + ":");
        }
        String test = "This is a test string";
String[] tokens = test.split("\\s");
System.out.println(tokens.length);  
for(String token: tokens){
    System.out.println(token);
}
    }
}