package au.net.richard;

import java.io.File;
import java.util.List;

public interface NameSorterFileUtils 
{

	public boolean checkFileNameExists(String fileName);
	public List<NameDetails> loadNamesFromFile(String fileName) throws Exception;
	public void saveNamesToFile(String fileName, List<NameDetails> names) throws Exception;
	
}
