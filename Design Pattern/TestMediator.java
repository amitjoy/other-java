public class TestMediator
{
  public static void main(String args[])
  {
    TestMediator t = new TestMediator();
  }

  public TestMediator()
  {
    Mediator mediator = new Mediator();

    mediator.getWelcome().go();
  }
}
