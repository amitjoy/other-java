public class TestDivision
{
  Division division;
  DivisionIterator iterator;

  public static void main(String args[])
  {
    TestDivision d = new TestDivision();
  }

  public TestDivision()
  {
    division = new Division("Sales");

    division.add("Ted");
    division.add("Bob");
    division.add("Carol");
    division.add("Alice");

    iterator = division.iterator();

    while (iterator.hasNext()){
      VP vp = (VP) iterator.next();
      vp.print();
    }
  }
}
