package demo;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class NewClass {
    
   static class B extends NewClass{
        B(){
            super(10);
        }
    }

    public int a = 10;

    public NewClass(int a) {
        this.a = a;
    }
    
     
//    public String toString(){
//         return true;
//     }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof NewClass){
            NewClass newObj = (NewClass)obj;
            return a == newObj.a;
         }
        return false;
        //Check for keywords always in code if mentioned as variables
    }
}
