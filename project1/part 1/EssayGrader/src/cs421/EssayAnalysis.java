package cs421;

import java.io.IOException;
import java.util.ArrayList;


public class EssayAnalysis {
	private static final EssayAnalysis EAinstance= new EssayAnalysis();

	private EssayAnalysis(){}
	
	public static EssayAnalysis getEAinstance(){
		return EAinstance;
	}
	
	public void analysisAll(ArrayList<Essay> essaySet) throws IOException
	{
			int size = essaySet.size();
			
			if(size < 0)
			{
				System.out.println("You have no files input!");
				return;
			}
			
			
			for(int i = 0; i < size; i++)
			{
				analysisGrammer(essaySet.get(i));
				analysisSemantic(essaySet.get(i));
				analysisWords(essaySet.get(i));
			}

	}
    // we need to do some grammar check here
	private void analysisGrammer(Essay essay) throws IOException{
		Grammar gramObj= Grammar.getInstance();
		gramObj.getGrammarScore(essay);
		
	}
	
	private void analysisSemantic(Essay essay) throws IOException{
		Semantics semObj = Semantics.getInstance();
		semObj.setEssaySemScore(essay);
	}
	
	private void analysisWords(Essay essay) {
		LengthAndWord lengObj = LengthAndWord.getInstance();
		lengObj.setLengthScore(essay);
	}
	
	
}
