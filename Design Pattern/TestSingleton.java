public class TestSingleton 
{
  public static void main(String args[])
  {
    Database database;

    database = Database.getInstance("products");
      
    System.out.println("This is the " + 
      database.getName() + " databse.");

    database = Database.getInstance("employees");
      
    System.out.println("This is the " + 
      database.getName() + " databse.");
  }
}
