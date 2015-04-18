package cs421;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
/* This the methods for length and words level analysis
 * 3.a how many sentence
 * 3.b how many big words
 * 3.c how many adv words
 * maybe other features
 */

public class LengthAndWord {
	private int LengthScores;
	private static final LengthAndWord  LAWinstance= new LengthAndWord ();
	MaxentTagger tagger;
	private LengthAndWord(){
		tagger =  ShareInstance.getTagger();
	}
	public static LengthAndWord getInstance() {
		return LAWinstance;
	}
	
	public void setLengthScore(Essay essay)
	{
		if(essay.getEssay().size() == 0)
			return;
		
		int length, adAdj;
		length = EssayLength(essay.getEssay());
		adAdj = FindAdverbAdj(essay.getEssay());
		LengthScores = length + adAdj;
		EssayResult res = essay.getResultV();
		res.setResult("3a", LengthScores);
	}
	
	private int EssayLength(ArrayList<String> essay)
	{
		int size = essay.size();
		
		int sum = 0;
		for(int i = 0; i < size; i++)
		{
			StringTokenizer temp = new StringTokenizer(essay.get(i));
			sum += temp.countTokens();
		}
		
		int average = (sum/size);
		return average;
	}
	
	private int FindAdverbAdj(ArrayList<String> essay)
	{
		
		int size = essay.size();
		int j=0;
		for(int i = 0; i < size; i++)
		{
			String tagged = tagger.tagString(essay.get(i));
		    
		    Pattern p = Pattern.compile("JJ");
		    Matcher m = p.matcher(tagged);
		    while (m.find()) {
		        j++;
		    }
		    
		    p = Pattern.compile("JJR");
		    m = p.matcher(tagged);
		    while (m.find()) {
		        j++;
		    }
		    
		    p = Pattern.compile("JJS");
		    m = p.matcher(tagged);
		    while (m.find()) {
		        j++;
		    }
		    
		    p = Pattern.compile("RB");
		    m = p.matcher(tagged);
		    while (m.find()) {
		        j++;
		    }
		    
		    p = Pattern.compile("RBR");
		    m = p.matcher(tagged);
		    while (m.find()) {
		        j++;
		    }
		    
		    p = Pattern.compile("RBS");
		    m = p.matcher(tagged);
		    while (m.find()) {
		        j++;
		    }
		}
		
		int average = j/size; 
		
		return average;
	}
	
	

}
