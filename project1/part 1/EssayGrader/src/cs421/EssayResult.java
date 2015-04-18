package cs421;

import java.util.ArrayList;
// 1.a 1.b 1.c 1.d  2.a 2.b 3.a
public class EssayResult {
	private int[] resultLevel;
	private double[] doubleValue;
	private double sum;
	 
	public EssayResult()
	{
		resultLevel = new int[7];
		doubleValue = new double[7];
		sum = 0.0;
	}
	
	public void update(int value)
	{
		if(value > 0)
		{
			for(int i = 0; i < 7; i++)
			{
				doubleValue[i] = (double)resultLevel[i]/value;
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
		 default:
			 System.out.println("The input result type is not correct.");
		 }
	}
	
	public void addResult(String result)
	{
		System.out.println("We find an error of type "+ result);
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
		 default:
			 System.out.println("The input result type is not correct.");
			 return -1;
		 }
	}
	 
	public String toString()
	{
		return 	Integer.toString(resultLevel[0]) + " " +
				Integer.toString(resultLevel[1]) + " " +
				Integer.toString(resultLevel[2]) + " " +
				Integer.toString(resultLevel[3]) + " " +
				Integer.toString(resultLevel[4]) + " " +
				Integer.toString(resultLevel[5]) + " " +
				Integer.toString(resultLevel[6]);
			   
	}
	
	public double getFinalValue()
	{
		for(int i = 0; i < 7; i++)
		{
			sum += doubleValue[i]; 
		}
		return sum;
	}
 
}
