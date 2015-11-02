public class TestVeto implements Link
{
  Link link1, link2, link3;

  public static void main(String args[])
  {
    TestVeto t = new TestVeto();
  }

  public TestVeto()
  {
    link3 = new Link3(this);
    link2 = new Link2(link3);
    link1 = new Link1(link2);

    getOK();
  }

  public void getOK()
  {
    link1.check(true); 
  }

  public void check(boolean b)
  {
    if(b){
      System.out.println("OK");
    } else {
      System.out.println("Not OK");
    }
  }
}
