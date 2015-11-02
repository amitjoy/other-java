import java.awt.*;

public class CheckboxAdapter extends Checkbox
{
    public CheckboxAdapter(String n) 
    {
      super(n);
    }

    public boolean isSelected()
    {
      return getState();
    }
}