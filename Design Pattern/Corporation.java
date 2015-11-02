import java.util.*;

public class Corporation extends Corporate
{
  private ArrayList corporate = new ArrayList();
  private int number = 0;

  public Corporation()
  {
  }

  public void add(Corporate c)
  {
    corporate.add(c);
  }

  public void print()
  {
    Iterator iterator = corporate.iterator();

    while (iterator.hasNext()){
      Corporate c = (Corporate) iterator.next();
      c.print();
    }
  }
}
