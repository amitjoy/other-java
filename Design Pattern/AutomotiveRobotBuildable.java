import java.util.*;

public class AutomotiveRobotBuildable implements RobotBuildable
{
  ArrayList actions;

  public AutomotiveRobotBuildable()
  {
  }

  public final void go()
  {
    Iterator itr = actions.iterator();

    while(itr.hasNext()) {
      switch ((new Integer(itr.next().toString())).intValue()){
        case 1: 
          start();
          break;
        case 2: 
          getParts();
          break;
        case 3: 
          assemble();
          break;
        case 4: 
          test();
          break;
        case 5: 
          stop();
          break;
      }
    }
  }

  public void start()
  {
    System.out.println("Starting....");
  }

  public void getParts()
  {
    System.out.println("Getting a carburetor....");
  }

  public void assemble()
  {
    System.out.println("Installing the carburetor....");
  }

  public void test()
  {
    System.out.println("Revving the engine....");
  }

  public void stop()
  {
    System.out.println("Stopping....");
  }

  public void loadActions(ArrayList a)
  {
    actions = a;
  }
}
