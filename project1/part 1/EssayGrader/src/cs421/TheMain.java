
package cs421;

import java.io.File;
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
		
	 	graderModer grader= new graderModer();
	    ArrayList<Essay> essaySet = new ArrayList<Essay>();
	    HashMap<String, String> topicRelatedWords = new HashMap<String, String>();
	    
	    topicRelatedWords.put("car","car");
	    topicRelatedWords.put("cars", "cars");
	    topicRelatedWords.put("vehicle", "vehicle");
	    topicRelatedWords.put("vehicles","vehicles");
	    topicRelatedWords.put("automobile","automobile");
	    topicRelatedWords.put("automobiles","automobiles");
	    
	    grader.readTrainingData(essaySet);
	    grader.analysisTrainingData(essaySet,topicRelatedWords);
	    grader.updateAutoGraderModel(essaySet);
   	  
	    
	         
	    	  String testPath = "input\\test\\tokenized";
	   
	      
	      
	   }
		

}

