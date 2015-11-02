package MostSpecific.function;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class NewMain {
    
    public static void print(long... var){
        System.out.println("Long");
    }
//    public static void print(Long var){
//        System.out.println("LONG");
//    }
//    public static void print(int... var){
//        System.out.println("Integer");
//    }
//    public static void print(Integer var){
//        System.out.println("IK");
//    }
//    public static void print(long var){
//        System.out.println("IKS");
//    }
    
    //primitive widening happens for vararg

    public static void main(String[] args) {
        int i = 10;
        Long k = 10L;
        Integer l = 10;
        print(10);
    }

}
