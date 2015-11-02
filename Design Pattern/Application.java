public class Application implements HelpInterface
{
  final int FRONT_END_HELP = 1;
  final int INTERMEDIATE_LAYER_HELP = 2;

  public Application()
  {
  }

  public void getHelp(int helpConstant)
  {
    System.out.println("This is the MegaGigaCo application.");
  }
}
