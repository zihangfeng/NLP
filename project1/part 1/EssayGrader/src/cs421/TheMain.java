
package cs421;

import java.io.File;
import java.util.*;

import opennlp.tools.postag.POSSample;
import opennlp.tools.util.Span;

public class TheMain {

	public static void main(String[] args) {
		//  
		String[] folderPath = new String[3]; 
		folderPath[0] = "P5\\P5-tokenized\\high";
		folderPath[1] = "P5\\P5-tokenized\\medium";
		folderPath[2] = "P5\\P5-tokenized\\low";
		
		String[] folderName = new String[3];
		folderName[0] = "high";
		folderName[1] = "medium";
		folderName[2] = "low";
		chunkResult CR=new chunkResult();
		EssayResult essayR=new EssayResult();
		Grammar.getChunkPOS("Is they like this room?", CR );
	    Grammar.getInstance().SentenceSubAgree(CR, 0, essayR);	 
		EssayAnalysis grader=EssayAnalysis.getEAinstance();
	     ArrayList<Essay> essaySet = new ArrayList<Essay>();
	      try
	      {      
	    	 for(int i = 0; i < 3; i++)
	    	 {
	    	 
		         File folder = new File(folderPath[i]);
		   
		         File[] listFiles = folder.listFiles();
		         
		         for(File pathname : listFiles)
		         {	 	        	
			        if(pathname.isFile())
			        	 {   
			        		 Essay essays = new Essay();
			        		 essays.setEssay(pathname, folderName[i]);
			        		 essaySet.add(essays);
			        	 }
		         }
	    	 }
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	      
	      
	   }
		

}

