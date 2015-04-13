package cs421;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;


/* we need to do some grammar check which includes
 *  1.a  spelling mistakes
 *  1.b  subject-verb agreement
 *  1.c  verb tense
 *  1.d sentence formation
 *  
 */

public class Grammar {
	private static final Grammar Grammarinstance= new Grammar();
	JLanguageTool langTool ;

	
	private Grammar()
	{
		langTool = new JLanguageTool(new AmericanEnglish());
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
	
	public void SpellingCheck(String sentence) throws IOException
	{
		List<RuleMatch> matches = langTool.check(" He have all my documents .");
		 
		for (RuleMatch match : matches) {
		  System.out.println("Potential error at line " +
		      match.getLine() + ", column " +
		      match.getColumn() + ": " + match.getMessage());
		  System.out.println("Suggested correction: " +
		      match.getSuggestedReplacements());
		}
		
	}
	
	public void SubAgree(ArrayList<String> essay)
	{
		
	}
}
