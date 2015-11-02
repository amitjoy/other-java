package ocajp2.oca.ad;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class Order {

    private static int getVal() {
        return b;
    }
//   static int a = b = 10;
     
    static{
        b = 10;
//        System.out.println("A"+b);
    }
    static{
//        a= c = 10;
        a = getVal();
    }
    static int a;
    static int c;
    static int b;
    String str = print("B");
    static{
        System.out.println("D");
    }
    static String str2 = print("C");
    
    public static String print(String str){
        System.out.println(str);
        return str;
    }
    
    public static void main(String... argv){
        Byte b1 = (byte)10;
        Byte b2 = (byte)10;
        
        Integer i1= -1000;
        Integer i2 = -1000;
        
        System.out.println(b1 == b2);
        System.out.println(i1 == i2);
        new Order();
//        switch(34){
//            case 45:continue;
//        }
    }

}
