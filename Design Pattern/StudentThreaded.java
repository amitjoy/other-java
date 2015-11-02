public class StudentThreaded 
{
  String name;
  int id;
  int score;
  double averageScore;
  private static StudentThreaded singleObject = 
    new StudentThreaded();

  public static StudentThreaded getInstance()
  {
      return singleObject;
  }

  private StudentThreaded()
  {
  }

  public void setAverageScore(double a)
  { 
    averageScore = a;
  }

  public void setName(String n)
  {
      name = n;
  }

  public void setId(int i)
  {
      id = i;
  }

  public void setScore(int s)
  {
      score = s;
  }

  public String getName()
  {
      return name;
  }

  public int getID()
  {
      return id;
  }

  public int getScore()
  {
      return score;
  }

  public double getStanding()
  {
      return (((double) score) / averageScore - 1.0) * 100.0;
  }
}
