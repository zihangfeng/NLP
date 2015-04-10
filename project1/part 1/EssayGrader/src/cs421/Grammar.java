package cs421;


import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.search.spell.SpellChecker;


public class Grammar {
	private double SpellingScore;
	private double SubAgreeScore;
	private double VerbTenseScore;
	private double FormatScore;
	private double GrammarScore;
	private int wordCount;
	
	public Grammar()
	{
		SpellingScore = 0.0;
		SubAgreeScore = 0.0;
		VerbTenseScore = 0.0;
		FormatScore = 0.0;
		wordCount = 0;
		GrammarScore = 0.0;
	}
	
	public double getGrammarScore()
	{
		return GrammarScore;
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
	
	public void Spelling(ArrayList<String> essay)
	{
		
		SpellChecker spellchecker = new SpellChecker("textfiles");
	}
	
	public void SubAgree(ArrayList<String> essay)
	{
		
	}
}
