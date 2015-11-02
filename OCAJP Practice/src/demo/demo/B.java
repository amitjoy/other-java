package demo.demo;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class B {

    public void print() throws Exception{
        try {
            throw new RuntimeException();
//            return;
        } catch (Exception e) {
            throw null;
//            System.out.println("");
        }
        
    }
    public void print(int i) throws Exception{
        
    }
}
