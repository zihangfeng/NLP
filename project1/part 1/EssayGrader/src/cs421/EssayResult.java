package cs421;

// 1.a 1.b 1.c 1.d  2.a 2.b 3.a 3.b
public class EssayResult {
	private int sizeOfRes = 8;
	private int[] resultLevel;
	private double[] doubleValue;
	private double sum;
	 
	public EssayResult()
	{
		resultLevel = new int[8];
		doubleValue = new double[8];
		sum = 0.0;
		
	}
	
	public void update(int numSentence)
	{
		if(numSentence > 0)
		{
			for(int i = 0; i < sizeOfRes; i++)
			{
				doubleValue[i] = (double)resultLevel[i]/numSentence;
			}
		}
		else
		{
			System.out.println("value can't be 0 or under");
			System.exit(0);
		}
		
	}
	
	public double[] getReslutDoubleValue()
	{
		return doubleValue;
	}
	
	public void setResult(String result, int count)
	{
		 switch (result) {
		 case "1.a" :
			 resultLevel[0]=count; break;
		 case "1.b" :
			 resultLevel[1]=count; break;
		 case "1.c" :
			 resultLevel[2]=count; break;
		 case "1.d" :
			 resultLevel[3]=count; break;
		 case "2.a" :
			 resultLevel[4]=count; break;
		 case "2.b" :
			 resultLevel[5]=count; break;
		 case "3.a" :
			 resultLevel[6]=count; break;
		 case "3.b" :
			 resultLevel[7]=count; break;
		 default:
			 System.out.println("The input result type is not correct.");
		 }
	}
	
	public void addResult(String result)
	{
		
		 switch (result) {
		 case "1.a" :
			 resultLevel[0]++; break;
		 case "1.b" :
			 resultLevel[1]++; break;
		 case "1.c" :
			 resultLevel[2]++; break;
		 case "1.d" :
			 resultLevel[3]++; break;
		 case "2.a" :
			 resultLevel[4]++; break;
		 case "2.b" :
			 resultLevel[5]++; break;
		 case "3.a" :
			 resultLevel[6]++; break;
		 case "3.b" :
			 resultLevel[7]++; break;
		 default:
			 System.out.println("The input result type is not correct.");
		 }
	}
	public int getResult(String result)
	{
		 switch (result) {
		 case "1.a" :
			return resultLevel[0];  
		 case "1.b" :
			 return  resultLevel[1]; 
		 case "1.c" :
			 return  resultLevel[2]; 
		 case "1.d" :
			 return resultLevel[3]; 
		 case "2.a" :
			 return resultLevel[4];
		 case "2.b" :
			 return resultLevel[5];
		 case "3.a" :
			 return resultLevel[6];
		 case "3.b" :
			 return resultLevel[7];
		 default:
			 System.out.println("The input result type is not correct.");
			 return -1;
		 }
	}
	 
	public void addTwoResult(EssayResult res1)
	{
		for(int i = 0; i < sizeOfRes; i++)
		{
			doubleValue[i]+= res1.getReslutDoubleValue()[i];
		}
	}

	public double getFinalValue()
	{
		for(int i = 0; i < sizeOfRes; i++)
		{
			sum += doubleValue[i]; 
		}
		return sum/sizeOfRes;
	}
	
	public String toString()
	{
		return 	Double.toString(doubleValue[0]) + " " +
				Double.toString(doubleValue[1]) + " " +
				Double.toString(doubleValue[2]) + " " +
				Double.toString(doubleValue[3]) + " " +
				Double.toString(doubleValue[4]) + " " +
				Double.toString(doubleValue[5]) + " " +
				Double.toString(doubleValue[6]) + " " +
				Double.toString(doubleValue[7]);
			   
	}
}
