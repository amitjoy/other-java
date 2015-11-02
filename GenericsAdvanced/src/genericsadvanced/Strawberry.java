package genericsadvanced;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */

public class Strawberry extends Fruits{

    public String taste;

    public Strawberry(String taste, String name, String color) {
        super(name, color);
        this.taste = taste;
    }

    @Override
    public String toString() {
        return "Strawberry{" + "taste=" + taste + '}';
    }
    
}
