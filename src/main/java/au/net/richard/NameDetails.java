package au.net.richard;

import java.util.List;
import java.util.Optional;

public class NameDetails 
{
	private String lastName;
	private String firstName;
	private Optional<List<String>> otherNames;
	
	
	public NameDetails(String lastName, String firstName)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		
		this.otherNames = Optional.empty();
	}
	
	public NameDetails(String lastName, String firstName, List<String> otherNames)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		
		this.otherNames = Optional.of(otherNames);
	}


	public String getLastName() 
	{
		return lastName;
	}


	public String getFirstName() 
	{
		return firstName;
	}


	public Optional<List<String>> getOtherNames() 
	{
		return otherNames;
	}
	
	
	
	
	
}
