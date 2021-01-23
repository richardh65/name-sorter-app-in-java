package au.net.richard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class NameDetailsFromLineBuilderImpl implements NameDetailsFromLineBuilder
{
	
	
	public NameDetails buildFromLine(String line)
	{
		if (StringUtils.isBlank(line))
		{
			throw new RuntimeException("Cannot handle empty line");
		}
		
		line = line.trim();
		
		
		String[] parts = line.split(" ");
		
		if (parts.length < 2)
		{
			throw new RuntimeException("Cannot handle line with only one name");
		}
		
		String firstName = parts[0];
		String lastName = parts[parts.length -1];
		
		List<String> others = null;
		if (parts.length > 2)
		{
			others = new ArrayList<String>();
						
			for (int i = 1; i < parts.length -1; i++)
			{
				String part = parts[i];
				
				if (StringUtils.isNotBlank(part))
				{				
					others.add(parts[i]);
				}
			}
		}
		
		NameDetails details;
		
		if (others != null)
		{
			details = new NameDetails(lastName, firstName, others);
		}
		else
		{
			details = new NameDetails(lastName, firstName);
		}
		
		return details;
		
	}
	
	public String generateLine(NameDetails name)
	{
		String line = name.getFirstName() + " ";
			
		if (name.getOtherNames().isPresent())
		{
			List<String> others = name.getOtherNames().get();
				
			for (String other : others)
			{
				line = line + other + " "; 
			}				
		}
								
		line = line + name.getLastName();								
		
		return line;
	}
	
}
