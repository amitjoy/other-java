public class RealJet
{
 
  public static void main(String[] args) 
  {
    Jet jet = new Jet();

    jet.setGoAlgorithm(new GoByDrivingAlgorithm());
    jet.go();

    jet.setGoAlgorithm(new GoByFlyingFastAlgorithm());
    jet.go();

    jet.setGoAlgorithm(new GoByDrivingAlgorithm());
    jet.go();
  }
}


