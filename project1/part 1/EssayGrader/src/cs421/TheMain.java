
package cs421;


import java.io.IOException;

public class TheMain {

	public static void main(String[] args) throws IOException {
		//  

		
	//	chunkResult CR=new chunkResult();
    //	EssayResult essayR=new EssayResult();
	//	Grammar.getChunkPOS("why they like this room?", CR );
	//    Grammar.getInstance().SentenceSubAgree(CR, 0, essayR);	
	//    Grammar.getInstance().SentenceSpellingCheck( "ss", essayR);
		
	       	  
	    	  
	    	 graderModer grader= new graderModer();
	    	 grader.readTrainingData();
	    	 grader.analysisTrainingData();
	    	 grader.updateAutoGraderModel();
	    	 grader.updateModelFromfile();
	    	 grader.examineTestFile("input\\test\\tokenized\\trainingFile");
	    	 grader.outputTraingResult();
	    	 grader.outputResult();
	       	  
	   }
		

}

