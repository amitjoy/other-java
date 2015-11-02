public class DatabaseThreaded
{
  private static DatabaseThreaded singleObject = 
    new DatabaseThreaded("products");
  private int record;
  private String name;

  private DatabaseThreaded(String n)
  {
    name = n;
    record = 0;
  }

  public static synchronized DatabaseThreaded getInstance(String n)
  {
      return singleObject;
  }

  public void editRecord(String operation)
  {
    System.out.println("Performing a " + operation +
      " operation on record " + record + 
      " in database " + name);
  }

  public String getName()
  {
    return name;
  }
}
