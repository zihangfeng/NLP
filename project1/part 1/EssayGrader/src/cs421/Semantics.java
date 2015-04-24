package cs421;

import java.util.HashMap;
import java.util.StringTokenizer;

/* This class is for semantics analysis
 * 2.a essay coherent
 * 2.b address the topic
 * 2.c 
 */
public class Semantics {
	private static final Semantics  Seminstance= new Semantics ();
	private Semantics(){}
	public static Semantics getInstance() {
		return Seminstance;
	}
	
	public void setEssaySemScore(Essay essay, HashMap<String, String> topicRelatedWords )
	{
		int coherentScore = 0;
		int addressTopicScore = 0;
		
		coherentScore = coherent(essay);
		addressTopicScore = addressTopic(essay, topicRelatedWords);
		essay.getResultObject().setResult("2.a", coherentScore);
		essay.getResultObject().setResult("2.b", addressTopicScore);
	}
	
	private int coherent(Essay essay)
	{
		int coherentScore = 0;
		int size = essay.getEssay().size();
		HashMap<String, String> coherenceWords = new HashMap<String, String>();
		coherenceWords.put("first", "first");
		coherenceWords.put("second", "second");
		coherenceWords.put("third", "third");
		coherenceWords.put("these", "these");
		coherenceWords.put("this", "this");
		coherenceWords.put("for example", "for example");
		coherenceWords.put("for instance", "for instance");
		coherenceWords.put("conclusion", "conclusion");
		coherenceWords.put("one hand", "one hand");
		coherenceWords.put("other hand", "other hand");
		coherenceWords.put("another", "another");
		coherenceWords.put("finally", "finally");
		coherenceWords.put("first", "first");
		coherenceWords.put("in addition", "in addition");
		coherenceWords.put("also", "also");
		coherenceWords.put("but", "but");
		coherenceWords.put("and", "and");
		coherenceWords.put("however", "however");
		
		
		for(int i = 0; i < size; i++)
		{
			StringTokenizer tokens = new StringTokenizer(essay.getEssay().get(i).trim());
			while(tokens.hasMoreTokens())
			{
				if(coherenceWords.containsKey(tokens.nextToken().toLowerCase()))
				{
					coherentScore++;
				}
			}
		}
		
		return coherentScore;
	}
	
	private int addressTopic(Essay essay, HashMap<String, String> topicRelatedWords)
	{
		int addressTopicScore = 0;
		int size = essay.getEssay().size();
		
		for(int i = 0; i < size; i++)
		{
			StringTokenizer tokens = new StringTokenizer(essay.getEssay().get(i).trim());
			while(tokens.hasMoreTokens())
			{
				if(topicRelatedWords.containsKey(tokens.nextToken().toLowerCase()))
				{
					addressTopicScore++;
				}
			}
		}
		
		return addressTopicScore;
	}
}
