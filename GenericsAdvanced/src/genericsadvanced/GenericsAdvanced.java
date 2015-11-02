package genericsadvanced;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */

public class GenericsAdvanced {

    public static void main(String[] args) {
        List<? extends Fruits> list = Lists.newArrayList();
        List<Apple> apples = Lists.newArrayList();
        List<Strawberry> strawberrys = Lists.newArrayList();
        strawberrys.add(new Strawberry("sour", "sb1", "sb2"));
        strawberrys.add(new Strawberry("sweet", "sb2", "sf"));
        apples.add(new Apple("big", "apple1", "red"));
        apples.add(new Apple("small", "apple2", "lightred"));
        list = apples;
        System.out.println(list.iterator().next().toString());
        System.out.println(list.get(0).toString());
        list = strawberrys;
        System.out.println(list.iterator().next().toString());
        
    }

}
