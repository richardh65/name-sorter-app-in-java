package au.net.richard;

import java.util.List;

public interface NameSorterLinesProcess  
{

	public List<NameDetails> parseFromText(List<String> lines);
	public List<String> generateLines(List<NameDetails> details);
	
	
}
