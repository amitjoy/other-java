package demo;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class NewMain1 {

    public static synchronized void main(String[] args) {
        try {
            return 1;
//            System.out.println("");
//            k();
//            System.out.println("");
        } catch (Exception e) {
//            return 2;
        }
        finally{
//            return 2;
//            System.out.println("");
        }
//        return 1;
                
        int[] arr = new int[-90];
//        System.out.println(arr.length);
        Byte b1 = new Byte("2");
        System.out.println(b1.toString() == b1.toString());
        String str1 = "mIT";
        String str2 = "mIT";
        System.out.println(str1 == str2);
        int i = 0;
        double dl = 3e2; //e2
        i = ++i;
        System.out.println(i);
        lab: i = i++; //no change in i
        lavbel:label:
        for (int j = 0; j < args.length; j++) {
            String string = args[j];
            
        }
//        labell: //labels should not be present in variable declaration
        Boolean bool = new Boolean(null);
        label: System.out.println(bool);
        System.out.println("Hello" == "Hel"+lo());
        System.out.println("Hello" == "Hel"+"lo");
        System.out.println("abc".substring(0)=="abc");
        System.out.println(Math.round(3.2));
        System.out.println(Math.ceil (-0.5) );
        return 1;
    };

    private static String lo() {
        return "lo";
    };

    private static void k() {
        throw new RuntimeException();
    }
    
    public static class A{
//        interface AB{
            
//        }
    }

}
