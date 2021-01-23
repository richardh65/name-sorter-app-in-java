package au.net.richard;

import java.io.File;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;



@Command(
		  name = "name-sorter",
		  description = "Sorts names"
		)

public class NameSorter implements Runnable 
{
	
	@Parameters(index = "0", description = "File name")
    private String fileName;
	
	private String TARGET_FILE_NAME = "sorted-names-list.txt";  
	
	public static void main(String[] args) 
	{
        CommandLine.run(new NameSorter(), args);
    }

	private static final int ERROR = -1;
	
	public void run() 
	{
		System.out.println("Welcome to name sorter");
		
		if (fileName == null)
		{
			System.err.println("file name is required");
			System.exit(ERROR);
		}
		else
		{
			System.out.println("File=" + fileName);
		}
		
		ExecutionState state = new ExecutionStateImpl();
		
		NameDetailsFromLineBuilder lineBuilder = new NameDetailsFromLineBuilderImpl();
		
		NameSorterLinesProcess process = new NameSorterLinesProcessImpl(lineBuilder);
		
		NameSorterFileUtils fileUtils = new NameSorterFileUtilsImpl(process);
		
		NameSorterScreenUtils screenUtils = new NameSorterScreenUtilsImpl(lineBuilder);
		
		NameSorterCommand command = new NameSorterCommand(fileUtils, screenUtils, state);
		
		try
		{
			command.run(fileName, TARGET_FILE_NAME);
		}
		catch (Exception e)
		{
			state.dumpHistory();
			
			e.printStackTrace();
		}

		// Execution handleer ?
	}
	
	
	
	
	
}
