import java.util.Iterator;

public class DivisionIterator implements Iterator
{
  private Corporate[] corporate;
  private int location = 0;

  public DivisionIterator(Corporate[] c)
  {
    corporate = c;
  }

  public java.lang.Object next()
  {
    return corporate[location++];
  }

  public boolean hasNext()
  {
    if(location < corporate.length && corporate[location] != null){
      return true;
    } else {
      return false; 
    }
  }

  public void remove()
  {
  }
}
