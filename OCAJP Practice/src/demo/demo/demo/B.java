package demo.demo.demo;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class B extends A {
    
    {
        print1("A");
    }
    static String str1 = print1("B");
    
    String str21 = print1("C");
    
    static{
        print1("D");
    }
    private static String print1(String a) {
        System.out.println(a);
        return a;
    }
    public B() {
        System.out.println("BConstructor");
    }

}
