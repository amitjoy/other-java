public class TestFlyweightThreaded implements Runnable
{
  Thread thread;

  public static void main(String args[])
  {
    TestFlyweightThreaded t = new TestFlyweightThreaded();
  }

  public TestFlyweightThreaded()
  {
    String names[] = {"Ralph", "Alice", "Sam"};
    int ids[] = {1001, 1002, 1003};
    int scores[] = {45, 55, 65};

    double total = 0;
    for (int loopIndex = 0; loopIndex < scores.length; loopIndex++){
      total += scores[loopIndex];
    }
 
    double averageScore = total / scores.length;

    StudentThreaded student = StudentThreaded.getInstance();

    student.setAverageScore(averageScore);
    student.setName("Ralph");
    student.setId(1002);
    student.setScore(45);

    thread = new Thread(this, "second");
    thread.start(); 

    System.out.println("Name: " + student.getName() +
      ", Standing: " + Math.round(student.getStanding()));
  }

  public void run() 
  {
    StudentThreaded student = StudentThreaded.getInstance();

    System.out.println("Name: " + student.getName() +
      ", Standing: " + Math.round(student.getStanding()));
  }
}
