package cs421;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
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
		langTool = ShareInstance.getJLanguageTool();
	}
	
	public static Grammar getInstance() {
		return Grammarinstance;
	}
	


	

	// score 1.a
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
	
	// score 1.b
	public void SubAgree(String sentence)
	{
	 // make decision based on chunk and POS tag	
		
		
		
	}
	
	
	
	// score 1.c
	
	public void VerbCheck(String sentence) {
		
		
	}
	
	
	
}
