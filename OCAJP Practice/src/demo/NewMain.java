package demo;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class NewMain {

    public int[] arr = new int[3];

//     static{
//         if(true)
//         throw null;
//     }
    class InnerOne {

        static final int i = 99;
    }

//    public NewMain() {
//        this(10);
//    }
//
//    
//
//    public NewMain(int x) {
//        this();
//    }
    

    public static void main(String[] args) {
        final int k =10;
        System.out.println(-0.0 == +0.0);
        Double x = +0.0;
        Double y = -0.0;
        System.out.println(x.equals(y));
        NewClass newClass = new NewClass(10);
        NewClass newClass2 = new NewClass(10);
        System.out.println(newClass.equals(newClass2));
        System.out.println(Math.ceil(-7.89));
        int num = 0b10101111;
        System.out.println(num >>> (byte) -2);
        NewClass1 nc = new NewClass1() {
//            NewClass1(){
//                
//            }
            @Override
            public void click() {
                System.out.println("A");
            }
        };

    }
}
