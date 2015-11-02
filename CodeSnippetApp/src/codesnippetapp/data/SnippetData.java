package codesnippetapp.data;

import java.io.Serializable;

public class SnippetData implements Serializable {
	public String name;
	public String code;
	public String description;
	
	public SnippetData()
	{
	}
	
	public SnippetData (String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
}
