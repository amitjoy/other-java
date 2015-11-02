public class TestAutomatProxy
{
  AutomatProxy automatProxy;

  public static void main(String args[])
  {
    TestAutomatProxy t = new TestAutomatProxy();
  }

  public TestAutomatProxy()
  {
    automatProxy = new AutomatProxy();
    
    automatProxy.gotApplication();
    automatProxy.checkApplication();
    automatProxy.rentApartment();
  }
}
