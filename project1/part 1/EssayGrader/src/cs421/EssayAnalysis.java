package cs421;

public class EssayAnalysis {
	private static final EssayAnalysis EAinstance= new EssayAnalysis();
	private EssayAnalysis(){}
	
	public static EssayAnalysis getEAinstance(){
		return EAinstance;
	}
	public void analysisAll(Essay essay)
	{

	}
    // we need to do some grammar check here
	public void analysisGrammer(Essay essay){
		Grammar grammarcheck= Grammar.getInstance();
		
	}
	
	public void analysisSemantic(Essay essay){
		
	}
	
	public void analysisWords(Essay essay) {
		
	}
	
}
