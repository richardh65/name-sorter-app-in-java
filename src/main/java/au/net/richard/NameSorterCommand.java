package au.net.richard;

import java.util.List;

public class NameSorterCommand 
{

	private NameSorterFileUtils fileUtils;
	private NameSorterScreenUtils screenUtils;
	private ExecutionState state;
	
	public NameSorterCommand(NameSorterFileUtils fileUtils, NameSorterScreenUtils screnUtils, ExecutionState state)
	{
		this.fileUtils = fileUtils;
		this.screenUtils = screnUtils;
		this.state = state;
	}
	
	public void run(String inputFileName, String targetFileName) throws Exception
	{		
		
		state.recordInterestingEvent("run started");
		state.recordInterestingEvent("inputFileName:" + inputFileName);
		state.recordInterestingEvent("targetFileName:" + targetFileName);
		
		if (fileUtils.checkFileNameExists(inputFileName) == false)
		{
			throw new RuntimeException("Filename does not exists " + inputFileName);
		}
		
		state.recordInterestingEvent("starting loading from file");
		
		List<NameDetails> names = fileUtils.loadNamesFromFile(inputFileName);
		
		state.recordInterestingEvent("done loading from file");
		
		List<NameDetails> sortedNames = NameSortProcess.sort(names);
		
		state.recordInterestingEvent("writing to file");
		state.recordInterestingEvent("targetFileName:" + targetFileName);
		state.recordInterestingEvent("names:" + sortedNames.size());
		
		fileUtils.saveNamesToFile(targetFileName, sortedNames);
		
		screenUtils.writeToScreen(sortedNames);
		
		state.recordInterestingEvent("run done");
	}
	
}
