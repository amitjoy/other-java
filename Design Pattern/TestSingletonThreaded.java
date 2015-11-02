public class TestSingletonThreaded implements Runnable
{
  Thread thread;

  public static void main(String args[])
  {
    TestSingletonThreaded t = new TestSingletonThreaded();
  }

  public TestSingletonThreaded()
  {
    DatabaseThreaded database;

    database = DatabaseThreaded.getInstance("products");

    thread = new Thread(this, "second");
    thread.start(); 

    System.out.println("This is the " + 
      database.getName() + " databse.");
  }

    public void run() 
    {
      DatabaseThreaded database;

      database = DatabaseThreaded.getInstance("employees");
      
      System.out.println("This is the " + 
        database.getName() + " databse.");
    }
}
