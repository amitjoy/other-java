import java.util.Iterator;

public class VPIterator implements Iterator
{
  private VP vp;

  public VPIterator(VP v)
  {
    vp = v;
  }

  public java.lang.Object next()
  {
    return vp;
  }

  public boolean hasNext()
  {
    return false; 
  }

  public void remove()
  {
  }
}
