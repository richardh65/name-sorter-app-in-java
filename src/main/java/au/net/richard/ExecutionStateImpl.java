package au.net.richard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ExecutionStateImpl implements ExecutionState
{
	private List<String> history;
	
	
	public ExecutionStateImpl()
	{
		this.history = new ArrayList<String>();
	}
	
	
	public void recordInterestingEvent(String line)
	{
		
		if (StringUtils.isNotBlank(line))
		{
			history.add(line);			
		}
		
		if (history.size() > 100)
		{
			history.remove(0);
		}		
	}
	
	public void dumpHistory()
	{
		System.out.println("============= something went wrong =========== ");
		for (String line : history)
		{
			System.out.println(line);
		}
		System.out.println("============= something went wrong =========== ");
	}
	
}
