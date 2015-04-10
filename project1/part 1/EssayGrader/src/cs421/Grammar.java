package cs421;


import java.util.ArrayList;


public class Grammar {
	private double Spelling;
	private double SubAgree;
	private double VerbTense;
	private double Format;
	private int wordCount;
	
	public Grammar()
	{
		Spelling = 0.0;
		SubAgree = 0.0;
		VerbTense = 0.0;
		Format = 0.0;
		wordCount = 0;
	}
	
	public double checkGarmmar(ArrayList<String> essay)
	{
		double grammarScore = 0.0;
		countNumWord(essay);
		
		// the less it is the more points it gets
		return (1 - grammarScore);
	}
	
	private void countNumWord(ArrayList<String> essay)
	{
		int numSen = essay.size();
		int i;
		for(i = 0; i < numSen; i++)
		{
			wordCount += essay.get(i).length();
		}
	}
}
