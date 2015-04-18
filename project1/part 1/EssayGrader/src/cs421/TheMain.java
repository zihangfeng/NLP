
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
		String[] folderPath = new String[3]; 
		folderPath[0] = "P5\\P5-tokenized\\high";
		folderPath[1] = "P5\\P5-tokenized\\medium";
		folderPath[2] = "P5\\P5-tokenized\\low";
		
		String[] folderName = new String[3];
		folderName[0] = "high";
		folderName[1] = "low";
		folderName[2] = "medium";
		
	//	chunkResult CR=new chunkResult();
    //	EssayResult essayR=new EssayResult();
	//	Grammar.getChunkPOS("why they like this room?", CR );
	//    Grammar.getInstance().SentenceSubAgree(CR, 0, essayR);	
	//    Grammar.getInstance().SentenceSpellingCheck( "ss", essayR);
		
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
	    	 

	    	 
	    	 EssayAnalysis analysisObj = EssayAnalysis.getEAinstance();
	    	 analysisObj.analysisAll(essaySet);
	    	     	 
	    	 PrintWriter outputStream = null;
	    	 outputStream = new PrintWriter(new FileOutputStream("FinalScores.txt"), true);
	    	 int size = essaySet.size();
	    	 for(int i = 0; i < size; i++)
	    	 {
	    		 essaySet.get(i).outputEssayStat(outputStream);
	    	 }
	    	 
	    	 

	         EssayResult low = new EssayResult();
	         EssayResult medium = new EssayResult();
	         EssayResult high = new EssayResult();
	         int lowSize = 0;
	         int mediumSize = 0;
	         int highSize = 0;
	    	 
	         for(int i=0; i<size;i++){
	        	 EssayResult er= essaySet.get(i).getResultV();
	        	 if(essaySet.get(i).getmarkedResult().equals("high"))
	        	 {
	        		highSize++;
	        		high.addtwo(er);
	        	 }
	        	 else if(essaySet.get(i).getmarkedResult().equals("medium"))
	        	 {
		        		mediumSize++;
		        		medium.addtwo(er);	        		 
	        	 }
	        	 else if(essaySet.get(i).getmarkedResult().equals("low"))
	        	 {
		        		lowSize++;
		        		low.addtwo(er);	        		 
	        	 }
	        	 else
	        	 {
	        		 System.out.println("Something is wrong with file");
	        	 }
	   
	        	 
	        	 
	        	 //test parts
	     		String testPath = "input\\test\\tokenized"; 
	     		
	     		ArrayList<Essay> testEssays = new ArrayList<>();
	     		File folder = new File(testPath);
	 		   
		         File[] listFiles = folder.listFiles();
		        
		         for(File pathname : listFiles)
		         {	 	        	
			        if(pathname.isFile())
			        	 {   
			        		 Essay essays = new Essay();
			        		 essays.setEssay(pathname, "any");
			        		 testEssays.add(essays);
			        	 }
		         }
	        
		         PrintWriter outputStream2 = null;
		    	 outputStream2 = new PrintWriter(new FileOutputStream("FinalTestScores.txt"), true);
		    	 int size2 = testEssays.size();
		    	 //System.out.println(size2);
		    	 //System.exit(0);
		    	 for(int j = 0; j < size2; j++)
		    	 {
		    		 testEssays.get(j).outputEssayStat(outputStream2);
		    	 }
	         }
	         
	    	 
	         
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	      
	      
	   }
		

}

