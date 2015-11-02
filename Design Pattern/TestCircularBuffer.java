public class TestCircularBuffer
{
  public static void main(String args[])
  {
    TestCircularBuffer t = new TestCircularBuffer();
  }

  public TestCircularBuffer()
  {
    CircularBuffer c = new CircularBuffer(8);

    System.out.println("Storing: 1");
    c.store(1);
    System.out.println("Reading: " + c.read());
    System.out.println("Storing: 2");
    c.store(2);
    System.out.println("Storing: 3");
    c.store(3);
    System.out.println("Storing: 4");
    c.store(4);
    System.out.println("Reading: " + c.read());
    System.out.println("Storing: 5");
    c.store(5);
    System.out.println("Storing: 6");
    c.store(6);
    System.out.println("Storing: 7");
    c.store(7);
    System.out.println("Reading: " + c.read());
    System.out.println("Storing: 8");
    c.store(8);
    System.out.println("Storing: 9");
    c.store(9);
    System.out.println("Storing: 10");
    c.store(10);
    System.out.println("Storing: 11");
    c.store(11);
    System.out.println("Storing: 12");
    c.store(12);
    System.out.println("Reading: " + c.read());
    System.out.println("Reading: " + c.read());
    System.out.println("Reading: " + c.read());
    System.out.println("Reading: " + c.read());
    System.out.println("Reading: " + c.read());
    System.out.println("Reading: " + c.read());
    System.out.println("Reading: " + c.read());
  }
}
