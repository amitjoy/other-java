import java.util.Iterator;

public class DivisionIterator implements Iterator
{
  private VP[] VPs;
  private int location = 0;

  public DivisionIterator(VP[] v)
  {
    VPs = v;
  }

  //public VP next()
  public java.lang.Object next()
  {
    return VPs[location++];
  }

  public boolean hasNext()
  {
    if(location < VPs.length && VPs[location] != null){
      return true;
    } else {
      return false; 
    }
  }

  public void remove()
  {
  }
}
