package cs421;

import java.util.ArrayList;

public class EssayResult {
	private ArrayList<String> resultLevel;
	
	public EssayResult()
	{
		resultLevel = new ArrayList<String>();
	}
	
	public void setResult(String result)
	{
		resultLevel.add(result);
	}
	
	public String getReslut(int index)
	{
		int sizeOfResult = resultLevel.size();
		if(index >= sizeOfResult)
		{
			System.out.println("the index requsted for the result is greater than" +
								" the size of result array");
			return null;
		}
		return resultLevel.get(index);
	}
}
