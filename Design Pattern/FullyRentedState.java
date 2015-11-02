public class FullyRentedState implements State
{
  AutomatInterface automat;

  public FullyRentedState(AutomatInterface d)
  {
    automat = d;
  }

  public String gotApplication()
  {
    return "Sorry, we're fully rented.";
  }

  public String checkApplication()
  {
    return "Sorry, we're fully rented.";
  }

  public String rentApartment()
  {
    return "Sorry, we're fully rented.";
  }

  public String dispenseKeys()
  {
    return "Sorry, we're fully rented.";
  }
}
