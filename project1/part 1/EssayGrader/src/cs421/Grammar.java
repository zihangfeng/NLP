package cs421;


import java.util.ArrayList;


/* we need to do some grammar check which includes
 *  1.a  spelling mistakes
 *  1.b  subject-verb agreement
 *  1.c  verb tense
 *  1.d sentence formation
 *  
 */

public class Grammar {
	private static final Grammar Grammarinstance= new Grammar();
	 

	
	private Grammar()
	{

	}
	
	public static Grammar getInstance() {
		return Grammarinstance;
	}
	

	public double checkGarmmar(ArrayList<String> essay)
	{
		double grammarScore = 0.0;
		countNumWord(essay);
		
		// the less mistake it has the more points it gets
		return (1 - grammarScore);
	}
	
	private void countNumWord(ArrayList<String> essay)
	{
		int numSen = essay.size();
		int i;
		for(i = 0; i < numSen; i++)
		{
		//	wordCount += essay.get(i).length();
		}
	}
	
	public void Spelling(ArrayList<String> essay)
	{
		
	}
	
	public void SubAgree(ArrayList<String> essay)
	{
		
	}
}
