public class CircularBuffer
{
  private int data[];
  private int head;
  private int tail;

  public CircularBuffer(int number)
  {
    data = new int[number];
    head = 0; 
    tail = 0;
  }

  public boolean store(int value)
  {
    if (!bufferFull()) { 
        data[tail++] = value;
        if(tail == data.length){
          tail = 0;
        }
        return true;
    } else {
        return false;
    }
  }

  public int read()
  {
    if (head != tail) {   
      int value = data[head++];
      if(head == data.length){
        head = 0;
      }
      return value;
    } else {
      return -1000000;
    }
  }

  private boolean bufferFull()
  {
    if(tail + 1 == head){
      return true;
    }
    if(tail == (data.length - 1) && head == 0){
      return true;
    }
    return false;
  }
}
