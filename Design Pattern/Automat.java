public class Automat implements AutomatInterface
{
  State waitingState;
  State gotApplicationState;
  State apartmentRentedState;
  State fullyRentedState;
  State state;
  int count;

  public Automat(int n)
  {
    count = n;
    waitingState = new WaitingState(this);
    gotApplicationState = new 
      GotApplicationState(this);
    apartmentRentedState = new 
      ApartmentRentedState(this);
    waitingState = new WaitingState(this);
    state = waitingState;
  }
  
  public void gotApplication()
  {
    System.out.println(state.gotApplication());
  }

  public void checkApplication()
  {
    System.out.println(state.checkApplication());
  }

  public void rentApartment()
  {
    System.out.println(state.rentApartment());
    System.out.println(state.dispenseKeys());
  }

  public State getWaitingState()
  {
    return waitingState;
  }

  public State getGotApplicationState()
  {
    return gotApplicationState;
  }

  public State getApartmentRentedState()
  {
    return apartmentRentedState;
  }

  public State getFullyRentedState()
  {
    return fullyRentedState;
  }

  public int getCount()
  {
    return count;
  }

  public void setCount(int n)
  {
    count = n;
  }

  public void setState(State s)
  {
    state = s;
  }
}
