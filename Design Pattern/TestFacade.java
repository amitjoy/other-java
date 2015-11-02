public class TestFacade 
{
  public static void main(String args[])
  {
    TestFacade t = new TestFacade();
  }

  public TestFacade()
  {

    SimpleProductFacade simpleProductFacade = 
      new SimpleProductFacade();

    simpleProductFacade.setName("printer");

    System.out.println("The product is a " + 
      simpleProductFacade.getName());
  }
}
