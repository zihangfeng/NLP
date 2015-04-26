
package cs421;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

import opennlp.tools.postag.POSSample;
import opennlp.tools.util.Span;

public class TheMain {

	public static void main(String[] args) throws IOException {
		//  

		
	//	chunkResult CR=new chunkResult();
    //	EssayResult essayR=new EssayResult();
	//	Grammar.getChunkPOS("why they like this room?", CR );
	//    Grammar.getInstance().SentenceSubAgree(CR, 0, essayR);	
	//    Grammar.getInstance().SentenceSpellingCheck( "ss", essayR);
		
	       	  String testPath = "input\\test\\tokenized";
	    	  
	    	 graderModer grader= new graderModer();
	    	    
	    	    
	    	 grader.readTrainingData();
	    	 grader.analysisTrainingData();
	    	 grader.updateAutoGraderModel();
	       	  
	   }
		

}

