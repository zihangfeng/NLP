package cs421;

import java.util.ArrayList;

public class EssayAnalysis {
	private static final EssayAnalysis EAinstance= new EssayAnalysis();

	private EssayAnalysis(){}
	
	public static EssayAnalysis getEAinstance(){
		return EAinstance;
	}
	
	public void analysisAll(ArrayList<Essay> content)
	{
		int size = content.size();
		
		if(size < 0)
		{
			System.out.println("You have no files input!");
			return;
		}
		
			for(int i = 0; i < size; i++)
			{
				analysisGrammer(content.get(i));
				analysisSemantic(content.get(i));
				analysisWords(content.get(i));
				
			}


	}
    // we need to do some grammar check here
	private void analysisGrammer(Essay essay){
		Grammar gramObj= Grammar.getInstance();
		gramObj.getGrammarScore(essay);
		
	}
	
	private void analysisSemantic(Essay essay){
		Semantics semObj = Semantics.getInstance();
	}
	
	private void analysisWords(Essay essay) {
		LengthAndWord lengObj = LengthAndWord.getInstance();
	}
	
	
}
