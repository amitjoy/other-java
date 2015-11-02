public class Link2 implements Link
{
  Link next;

  public Link2(Link n)
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
