package genericsadvanced;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */

public class Apple extends Fruits{
    
    public String size;

    public Apple(String size, String name, String color) {
        super(name, color);
        this.size = size;
    }

    @Override
    public String toString() {
        return "Apple{" + "size=" + size + '}';
    }
    
    
}
