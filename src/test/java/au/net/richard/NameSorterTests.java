package au.net.richard;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;


public class NameSorterTests 
{

	
	@Test
	public void testEquator1() 
	{	
		NameDetailsEquator equator = new NameDetailsEquator();
		
		NameDetails adamSmith1 = new NameDetails("Smith", "Adam");
		NameDetails adamSmith2 = new NameDetails("Smith", "Adam");
		
		boolean equals = equator.equate(adamSmith1, adamSmith2);
		
		assertTrue(equals);		
	}
	
	@Test
	public void testNameFromLineSimple1()
	{
		String line = "John Snow";
		
		NameDetailsFromLineBuilder builder = new NameDetailsFromLineBuilderImpl();
		
		NameDetails details = builder.buildFromLine(line);
		
		assertEquals("John", details.getFirstName());
		assertEquals("Snow", details.getLastName());
	}
	
	@Test
	public void testNameFromLineWithMiddleName()
	{
		String line = "John Roger Snow";
		
		NameDetailsFromLineBuilder builder = new NameDetailsFromLineBuilderImpl();
		
		NameDetails details = builder.buildFromLine(line);
		
		assertEquals("John", details.getFirstName());
		assertEquals("Snow", details.getLastName());
		assertTrue(details.getOtherNames().isPresent());
		
		List<String> others = details.getOtherNames().get();
		assertEquals(1, others.size());
		
		assertEquals("Roger", others.get(0));
	}
	
	@Test
	public void testLineFromNameSimple1()
	{
		String expectedLine = "John Snow";
		
		NameDetailsFromLineBuilder builder = new NameDetailsFromLineBuilderImpl();
		
		NameDetails details = new NameDetails("Snow", "John");
		
		String line = builder.generateLine(details);
		assertEquals(expectedLine, line);
	}
	
	@Test
	public void testLineFromNameWithMiddleName1()
	{
		String expectedLine = "John Barry Snow";
		
		NameDetailsFromLineBuilder builder = new NameDetailsFromLineBuilderImpl();
		
		List<String> others = Arrays.asList("Barry");
		
		NameDetails details = new NameDetails("Snow", "John", others);
		
		String line = builder.generateLine(details);
		assertEquals(expectedLine, line);
	}
	
	@Test
	public void testNameFromLineMultipleMiddleNames()
	{
		String line = "John Peter Roger Snow";
		
		NameDetailsFromLineBuilder builder = new NameDetailsFromLineBuilderImpl();
		
		NameDetails details = builder.buildFromLine(line);
		
		assertEquals("John", details.getFirstName());
		assertEquals("Snow", details.getLastName());
		assertTrue(details.getOtherNames().isPresent());
		
		List<String> others = details.getOtherNames().get();
		assertEquals(2, others.size());
		
		assertEquals("Peter", others.get(0));
		assertEquals("Roger", others.get(1));
	}
	
	@Test
	public void testNameFromLineWithExtraSpaces()
	{
		String line = "John    Peter     Roger    Snow";
		
		NameDetailsFromLineBuilder builder = new NameDetailsFromLineBuilderImpl();
		
		NameDetails details = builder.buildFromLine(line);
		
		assertEquals("John", details.getFirstName());
		assertEquals("Snow", details.getLastName());
		assertTrue(details.getOtherNames().isPresent());
		
		List<String> others = details.getOtherNames().get();
		assertEquals(2, others.size());
		
		assertEquals("Peter", others.get(0));
		assertEquals("Roger", others.get(1));
	}
	
	@Test
	public void testNameFromLineWithExtraSpaces2()
	{
		String line = " John    Peter     Roger    Snow ";
		
		NameDetailsFromLineBuilder builder = new NameDetailsFromLineBuilderImpl();
		
		NameDetails details = builder.buildFromLine(line);
		
		assertEquals("John", details.getFirstName());
		assertEquals("Snow", details.getLastName());
		assertTrue(details.getOtherNames().isPresent());
		
		List<String> others = details.getOtherNames().get();
		assertEquals(2, others.size());
		
		assertEquals("Peter", others.get(0));
		assertEquals("Roger", others.get(1));
	}
	
	@Test
	public void testSortNamesWhenNoNamesProvided() 
	{		
		List<NameDetails> namesToSort = Collections.emptyList(); 
		
					
		List<NameDetails> sortedList = NameSortProcess.sort(namesToSort);		
		
		assertEquals(0,  sortedList.size());
	}
	
	@Test
	public void testSortNamesSingleName() 
	{		
		NameDetails johnSmith = new NameDetails("Smith", "John");
		
		List<NameDetails> namesToSort = Arrays.asList(johnSmith); 
		
		List<NameDetails> sortedList = NameSortProcess.sort(namesToSort);		
		
		assertEquals(1,  sortedList.size());
		
		NameDetails result = sortedList.get(0);
		
		assertEquals(johnSmith, result);
		
	}
	
	@Test
	public void testSortNamesSimple() 
	{		
		NameDetails adamSmith = new NameDetails("Smith", "Adam");
		NameDetails alexSmith = new NameDetails("Smith", "Alex");
		NameDetails johnSmith = new NameDetails("Smith", "John");
		NameDetails johnBrown = new NameDetails("Brown", "John");
		
		List<NameDetails> namesToSort = Arrays.asList(adamSmith, alexSmith, johnSmith, johnBrown); 
		
		List<NameDetails> sortedList = NameSortProcess.sort(namesToSort);		
		
		assertEquals(4,  sortedList.size());
		
		List<NameDetails> expectedList = Arrays.asList(johnBrown, adamSmith, alexSmith, johnSmith);
		
		NameDetailsEquator equator = new NameDetailsEquator();
		
		assertTrue(CollectionUtils.isEqualCollection(expectedList, sortedList, equator));
		
		assertTrue(expectedList.equals(sortedList));
	}
	
	@Test
	public void testProcessNamesFromTextNoText()
	{
		NameDetailsFromLineBuilder lineBuilder = new NameDetailsFromLineBuilderImpl();
		
		NameSorterLinesProcess process = new NameSorterLinesProcessImpl(lineBuilder);
		
		List<String> lines = Arrays.asList(" ", " ");
		
		List<NameDetails> names = process.parseFromText(lines);
		
		assertEquals(0, names.size());		
	}

	@Test
	public void testProcessNamesFromTextSimpleLIne1()
	{
		NameDetailsFromLineBuilder lineBuilder = new NameDetailsFromLineBuilderImpl();
		
		NameSorterLinesProcess process = new NameSorterLinesProcessImpl(lineBuilder);
		
		List<String> lines = Arrays.asList("John Snow ", " ");
		
		List<NameDetails> names = process.parseFromText(lines);
		
		assertEquals(1, names.size());
		
		NameDetails name1 = names.get(0);
		
		assertEquals("Snow", name1.getLastName());
		assertEquals("John", name1.getFirstName());
		
	}
	
	@Test
	public void testEndToEndProcess1() throws Exception
	{		
		NameDetailsFromLineBuilder lineBuilder = new NameDetailsFromLineBuilderImpl();
		
		NameSorterLinesProcess process = new NameSorterLinesProcessImpl(lineBuilder);
		
		String expectedLine1 = "Mad as a hatter Max";
		String expectedLine2 = "Modonna Smith"; 
		String expectedLine3 = "John Snow";
		
		List<String> fileContent = Arrays.asList(expectedLine3, expectedLine1, expectedLine2);
		
		NameSorterFileUtilsMock fileUtils = new NameSorterFileUtilsMock(process, "test1.txt", fileContent);
		
		NameSorterScreenUtils screenUtils = new NameSorterScreenUtilsImpl(lineBuilder);
		
		ExecutionStateMock stateMock = new ExecutionStateMock();
		
		NameSorterCommand command = new NameSorterCommand(fileUtils, screenUtils, stateMock);
		
		command.run("test1.txt", "test1.done.txt");
		
		List<String> linesWritten = fileUtils.getLastLinesWritten();
		
		assertEquals(3, linesWritten.size());
		
		String line1 = linesWritten.get(0);
		assertEquals(expectedLine1, line1);
		
		String line2 = linesWritten.get(1);
		assertEquals(expectedLine2, line2);
		
		String line3 = linesWritten.get(2);
		assertEquals(expectedLine3, line3);
		
	}
	
}
