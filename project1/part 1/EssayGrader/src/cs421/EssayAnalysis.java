package cs421;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EssayAnalysis {
	private static final EssayAnalysis EAinstance= new EssayAnalysis();

	private EssayAnalysis(){}
	
	public static EssayAnalysis getEAinstance(){
		return EAinstance;
	}
	
	public void analysisAll(ArrayList<Essay> content) throws FileNotFoundException
	{
		int size = content.size();
		
		if(size < 0)
		{
			System.out.println("You have no files input!");
			return;
		}
		
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new FileOutputStream("Reslut.txt"));
			for(int i = 0; i < size; i++)
			{
				analysisGrammer(content.get(i));
				analysisSemantic(content.get(i));
				analysisWords(content.get(i));
				
			}
		}
		finally
		{
			outputStream.close();
		}
	}
    // we need to do some grammar check here
	private void analysisGrammer(Essay essay){
		Grammar grammarcheck= Grammar.getInstance();
		
	}
	
	private void analysisSemantic(Essay essay){
		
	}
	
	private void analysisWords(Essay essay) {
		
	}
	
	
}
