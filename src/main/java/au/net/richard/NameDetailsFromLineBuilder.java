package au.net.richard;

public interface NameDetailsFromLineBuilder 
{

	public NameDetails buildFromLine(String line);
	public String generateLine(NameDetails name);
	
}
