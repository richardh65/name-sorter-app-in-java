package au.net.richard;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class NameSorterFileUtilsImpl implements NameSorterFileUtils 
{
	private NameSorterLinesProcess process;

	public NameSorterFileUtilsImpl(NameSorterLinesProcess process)
	{
		this.process = process;
	}
	
	public List<NameDetails> loadNamesFromFile(String fileName) throws Exception 
	{
		File readFile = new File(fileName);
		
		List<String> lines = FileUtils.readLines(readFile);
		
		List<NameDetails> list = process.parseFromText(lines);	
		
		return list;
	}

	public void saveNamesToFile(String fileName, List<NameDetails> names) throws Exception 
	{
		File writeFile = new File(fileName);
		
		List<String> lines = process.generateLines(names);
		
		FileUtils.writeLines(writeFile, lines);		
	}

	public boolean checkFileNameExists(String fileName) 
	{
	
		File newFile = new File(fileName);
		
		return newFile.exists();
	}

	

	
}
