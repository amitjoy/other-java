import java.io.*;

public class TestRobotBuilder 
{
  public static void main(String args[])
  {
    RobotBuilder builder;
    RobotBuildable robot;
    String response = "a";

    System.out.print(
      "Do you want a cookie robot [c] or an automotive one [a]? ");
    BufferedReader reader = new 
      BufferedReader(new InputStreamReader(System.in));

    try{
      response = reader.readLine();
    } catch (IOException e){ 
      System.err.println("Error");
    }

    if (response.equals("c")){
      builder = new CookieRobotBuilder();
    } else {
      builder = new AutomotiveRobotBuilder();
    }

    //Start the construction process.

    builder.addStart();
    builder.addTest();
    builder.addAssemble();
    builder.addStop();

    robot = builder.getRobot();

    robot.go();
  }
}
