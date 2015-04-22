package cs421;
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
	
	public void setSemScore(Essay essay)
	{
		int coherentScore = 0;
		int addressTopicScore = 0;
		
		EssayResult res = essay.getResultObject();
		
		coherentScore = coherent(essay);
		addressTopicScore = addressTopic(essay);
		res.setResult("2.a", coherentScore);
		res.setResult("2.b", addressTopicScore);
	}
	
	private int coherent(Essay essay)
	{
		int coherentScore = 0;
		
		
		return coherentScore;
	}
	
	private int addressTopic(Essay essay)
	{
		int addressTopicScore = 0;
		
		
		return addressTopicScore;
	}
}
