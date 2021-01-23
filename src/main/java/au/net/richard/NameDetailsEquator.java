package au.net.richard;

import org.apache.commons.collections4.Equator;
import org.apache.commons.lang3.StringUtils;


public class NameDetailsEquator implements Equator<NameDetails> 
{

	public boolean equate(NameDetails nd1, NameDetails nd2) 
	{
		if ((nd1 != null && nd2 != null))
		{
			if (StringUtils.equals(nd1.getLastName(), nd2.getLastName()))
			{
				if (StringUtils.equals(nd1.getFirstName(), nd2.getFirstName()))
				{
					if (nd1.getOtherNames().isPresent() && nd2.getOtherNames().isPresent())
					{
						if (nd1.getOtherNames().get().equals(nd2.getOtherNames().get()))
						{
							return true;
						}
					}
					else
					{
						return true;
					}
				}
			}
		}
		
		
		return false;
	}

	public int hash(NameDetails details) 
	{
		
		int hash = details.getLastName().hashCode() * details.getFirstName().hashCode();
		if (details.getOtherNames().isPresent())
		{
			hash = hash * details.getOtherNames().get().hashCode();
		}
		
		return hash;
	}

	
	
}
