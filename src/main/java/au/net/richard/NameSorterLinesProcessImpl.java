package au.net.richard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class NameSorterLinesProcessImpl implements NameSorterLinesProcess
{

	private NameDetailsFromLineBuilder lineBuilder;
	
	public NameSorterLinesProcessImpl(NameDetailsFromLineBuilder lineBuilder)
	{
		this.lineBuilder = lineBuilder;
	}
	
	public List<NameDetails> parseFromText(List<String> lines) 
	{
		List<NameDetails> names = new ArrayList<NameDetails>();
		
		for (String line : lines)
		{
			if (StringUtils.isNotBlank(line))
			{
				NameDetails details = lineBuilder.buildFromLine(line);
				
				names.add(details);				
			}
		}
		
		
		return names;
	}

	public List<String> generateLines(List<NameDetails> details) 
	{
		List<String> lines = new ArrayList<String>();
		
		for (NameDetails name : details)
		{
			String line = lineBuilder.generateLine(name);
			
			lines.add(line);
		}
		
		
		return lines;
	}

}
