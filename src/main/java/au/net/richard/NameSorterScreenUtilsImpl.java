package au.net.richard;

import java.util.List;

public class NameSorterScreenUtilsImpl implements NameSorterScreenUtils 
{

	private NameDetailsFromLineBuilder lineBuiler;
	
	public NameSorterScreenUtilsImpl(NameDetailsFromLineBuilder lineBuiler)
	{
		this.lineBuiler = lineBuiler;
	}
	
	public void writeToScreen(List<NameDetails> names) 
	{
		for (NameDetails name : names)
		{
			String line = lineBuiler.generateLine(name);
									
			System.out.println(line);
		}		
		
	}

}
