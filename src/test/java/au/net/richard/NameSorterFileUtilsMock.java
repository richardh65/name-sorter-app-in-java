package au.net.richard;

import java.util.List;

public class NameSorterFileUtilsMock implements NameSorterFileUtils 
{

	private NameSorterLinesProcess process; 
	private String testFileName;
	private List<String> lines;
	private List<String> lastLinesWritten;
	
	public NameSorterFileUtilsMock(NameSorterLinesProcess process, String testFileName, List<String> lines)
	{
		this.process = process;
		this.testFileName = testFileName;
		this.lines = lines;
		
		lastLinesWritten = null;
	}
	
	public boolean checkFileNameExists(String fileName) 
	{		
		return true;
	}

	public List<NameDetails> loadNamesFromFile(String fileName) throws Exception 
	{
		
		List<NameDetails> names = process.parseFromText(lines);
		
		return names;
		
	}

	public void saveNamesToFile(String fileName, List<NameDetails> names) throws Exception 
	{
		lastLinesWritten = process.generateLines(names);
	}

	public List<String> getLastLinesWritten() 
	{
		return lastLinesWritten;
	}

}
