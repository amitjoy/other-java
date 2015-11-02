public class Link3 implements Link
{
  Link next;

  public Link3(Link n)
  {
    next = n;
  }

  public void check(boolean b)
  {
    if(b){
      next.check(true);
    } else {
      next.check(false);
    }
  }
}
