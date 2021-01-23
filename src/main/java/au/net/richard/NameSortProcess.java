package au.net.richard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class NameSortProcess 
{
	
	private static class LastNameComparator implements Comparator<NameDetails>
	{
		public int compare(NameDetails o1, NameDetails o2) 
		{
			return StringUtils.compare(o1.getLastName(), o2.getLastName());
		}
		
	}
	
	private static class FirstNameComparator implements Comparator<NameDetails>
	{
		public int compare(NameDetails o1, NameDetails o2) 
		{
			return StringUtils.compare(o1.getFirstName(), o2.getFirstName());
		}
		
	}
	
	private static class OtherNamesComparator implements Comparator<NameDetails>
	{
		public int compare(NameDetails o1, NameDetails o2) 
		{
			if (o1.getOtherNames().isPresent() && o2.getOtherNames().isPresent())
			{
				String n1 = o1.getOtherNames().get().get(0);
				String n2 = o2.getOtherNames().get().get(0);
				
				return StringUtils.compare(n1, n2);	
			}
			else
			{
				return 0;
			}			
		}
		
	}
	
	
	private static class NameDetailsChainedComparator implements Comparator<NameDetails>
	{
		LastNameComparator lastNameComparator = new LastNameComparator();
		FirstNameComparator firstNameComparator = new FirstNameComparator();
		OtherNamesComparator otherNamesComparator = new OtherNamesComparator();
		
		List<Comparator<NameDetails>> listComparators = Arrays.asList(lastNameComparator, firstNameComparator, otherNamesComparator);
				

		public int compare(NameDetails nd1, NameDetails nd2) 
		{
			for (Comparator<NameDetails> comparator : listComparators) 
			{
				int result = comparator.compare(nd1, nd2);
				if (result != 0) 
				{
					return result;
				}
			}
			return 0;			
		}
		
	}
	
	public static List<NameDetails> sort(List<NameDetails> originalNames)
	{
		List<NameDetails> names = new ArrayList<NameDetails>(originalNames);
					
		
		NameDetailsChainedComparator  comparator = new NameDetailsChainedComparator();
		
		Collections.sort(names, comparator);
		
		
		return names;
	}
	
}
