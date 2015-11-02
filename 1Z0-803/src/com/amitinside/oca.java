package com.amitinside;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class oca {

    static int iii;
    public static void main(String[] args) {
        Sample s1, s2, s3;
        s1 = s2 = new Sample();
//        System.out.println(args[2]);
        String m = "-0.50";
        System.out.println(Math.round(-0.5));
        System.out.println(Double.parseDouble(m.substring(1, m.length()-1) ));
        int i;
        Integer ik = 1;
        int ikL = 1;
        Long lk = 1L;
        int[] gty = new int[10];
        System.out.println("rtrtrtrtr"+gty[1]);
        if(ik == ikL){
            System.out.println("jjj");
            System.out.println('b'+63);
        }
        Byte bk = 1;
        if(ik.equals(bk)){
            System.out.println("true");
        }
         boolean b1F = false;
        boolean bF2 = true;
        byte aa = 'a';
        int ag = 23;
        int fg = ~ag;
        
        if(b1F != bF2 = !bF2){
            
        }
        String sttt = ("hello" + new String("world"));
        StringBuilder b1 = new StringBuilder("snorkler");
        StringBuilder b2 = new StringBuilder("yoodler");
        StringBuffer stringBuffer = new StringBuffer(200);
        stringBuffer.setLength(100);
        System.out.println("******************************************");
//        System.out.println(b1.append("xyzabc", 1,4));
//        System.out.println(b1.substring(1, 4));
//        System.out.println(b1.insert(3, "ghttt"));
//        System.out.println(b1.replace(1, 3, "AMIT"));
        System.out.println("......******************************************");
        //System.out.println(b1.append(b2.substring(2,5).toUpperCase()));
        //System.out.println(b2.insert(3, b1.append("a")));
        System.out.println(b1.replace(3, 4, b2.substring(4)).append(b2.append(false)));
        System.out.println(b1);
        System.out.println(b2);
        b1.setLength(3);
        b1.setLength(4);
        System.out.println(b1.length());
        int i1 = 'a', i2 = 2, i3 = 3;
        short s = 'a';
        char c3 = s;
int i4 = i1 + (i2=i3 );
System.out.println(i4);
        byte b = -128 ;
      int ik = b ;
      b = (byte) ik;
      System.out.println(ik+" "+b);
        System.out.println(sttt);
        
        List<String> li = new ArrayList<String>();
        li.add("AMIT");
        li.add("ANIT");
        li.add("Brothers");
        li.add(m);
        i = iii;
        List<Integer> li2 = new ArrayList<Integer>();
        li2.add(23);
        li2.add(12);
        li2.add(16);

        for (String str : li) {
            if (li.equals("AMIT")) {
                str = "MONDAL";
                li.add("MONDAL");
            }
        }
        System.out.println(li);
        int i = 1;
        while (i <= 10) {
            System.out.print(++i + " ");
        }
        outerLoop:
        for (int j = 0; j < 2; j++) {
            int sum = 0;
            for (int element : li2) {
                sum += element;
                if (j > 0) {
                    break outerLoop;
                }
            }
            System.out.println("Sum of row " + j + " is " + sum);
        }
        float x = 0.1f;
//        while (x != 1.1f) {
//            System.out.printf("x = %f%n", x);
//            x = x + 0.1f;
//        }
        //int i;
        int j;
        for (i = 1; i < 4; i++) {
            for (j = 2; j < 4; j++) {
                if (j == 3) {
                    continue;
                }
                System.out.println("i: " + i + " j: " + j);
            }



        }
    }
}
