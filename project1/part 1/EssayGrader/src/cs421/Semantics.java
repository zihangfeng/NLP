package cs421;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

/* This class is for semantics analysis
 * 2.a essay coherent
 * 2.b address the topic
 * 2.c 
 */
public class Semantics {
	private static final Semantics  Seminstance= new Semantics ();
	private static HashMap<String, String> topicRelatedWords;
	private static HashMap<String, String> coherenceWords;
	private Semantics(){
		
		BufferedReader inputStream1 = null;
		BufferedReader inputStream2 = null;
		topicRelatedWords = new HashMap<String, String>();
		coherenceWords = new HashMap<String, String>();
	    try {
	    	inputStream1 = new BufferedReader(new FileReader("relatedTopicWords.txt"));
	    	
	    	String words = null;
	        while((words = inputStream1.readLine()) != null)
	        {
	        	StringTokenizer tokens = new StringTokenizer(words);
	        	while(tokens.hasMoreTokens())
	        	{
	        		String temp = tokens.nextToken();
	        		topicRelatedWords.put(temp, temp);

	        	}
	        	
	        }
	        
	        inputStream2 = new BufferedReader(new FileReader("coherenceWords.txt"));
	        words = null;
	        while((words = inputStream2.readLine()) != null)
	        {
	        	StringTokenizer tokens = new StringTokenizer(words);
	        	while(tokens.hasMoreTokens())
	        	{
	        		String temp = tokens.nextToken();
	        		coherenceWords.put(temp, temp);
	        	}
	        }
	        
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				inputStream1.close();
				inputStream2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	}
	public static Semantics getInstance() {
		
		return Seminstance;
	}
	
	public void setEssaySemScore(Essay essay) throws IOException
	{
		int coherentScore = 0;
		int addressTopicScore = 0;
		
		
		
		coherentScore = coherent(essay);
		addressTopicScore = addressTopic(essay);
		essay.getResultObject().setResult("2.a", coherentScore);
		essay.getResultObject().setResult("2.b", addressTopicScore);
	}
	
	private int coherent(Essay essay)
	{
		int coherentScore = 0;
		int size = essay.getEssay().size();
		
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
	
	private int addressTopic(Essay essay)
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
