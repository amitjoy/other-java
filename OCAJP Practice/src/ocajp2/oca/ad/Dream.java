package ocajp2.oca.ad;

import java.util.Comparator;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class Dream    
{
    public static void main(String... args)
    {
        System.out.println(PinSize.BIG == PinSize.BIGGER); // #1
        System.out.println(PinSize.BIG.equals(PinSize.BIGGER));// #2
//        System.out.println(PinSize.BIG < PinSize.BIGGERER);// #3
        System.out.println(PinSize.BIG.toString().equals(PinSize.BIG.toString()));// #4
        PinSize b = PinSize.BIG ;
        System.out.println( b instanceof Comparable);// #5
        System.out.println(PinSize.BIGGERER.ordinal());
//        System.out.println(null.equals("B"));
    }  
}
enum PinSize { BIG, BIGGER, BIGGERER }; 

