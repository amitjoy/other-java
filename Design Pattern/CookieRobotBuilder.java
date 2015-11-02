import java.util.*;

public class CookieRobotBuilder implements RobotBuilder
{
  CookieRobotBuildable robot;
  ArrayList actions; 

  public CookieRobotBuilder()
  {
    robot = new CookieRobotBuildable();
    actions = new ArrayList();
  }
  
  public void addStart()
  {
    actions.add(new Integer(1)); 
  }

  public void addGetParts()
  {
    actions.add(new Integer(2)); 
  }

  public void addAssemble()
  {
    actions.add(new Integer(3)); 
  }

  public void addTest()
  {
    actions.add(new Integer(4)); 
  }

  public void addStop()
  {
    actions.add(new Integer(5)); 
  }

  public RobotBuildable getRobot()
  {
    robot.loadActions(actions);
    return robot;
  }

}
