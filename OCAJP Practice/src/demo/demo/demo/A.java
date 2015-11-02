package demo.demo.demo;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 * 11 33 b d 00 22 ACon AC BCon
 */
public class A {

    {
        print("00");
    }
    static String str = print("11");
    
    String str2 = print("22");
    
    static{
        print("33");
    }

    private static String print(String a) {
        System.out.println(a);
        return a;
    }

    public A() {
        System.out.println("AConstructor");
    }
}
