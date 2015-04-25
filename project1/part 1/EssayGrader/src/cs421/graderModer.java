package cs421;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class graderModer {
	//private ArrayList<Essay> essaySet;
	private EssayResult[] model;
	public graderModer(){ 
		//essaySet=new ArrayList<Essay>();
		model=new EssayResult[3];
	}
    
	public void readTrainingData(ArrayList<Essay> essaySet) throws IOException{
		
		String[] folderPath = new String[3]; 
		folderPath[0] = "P5\\P5-tokenized\\high";
		folderPath[1] = "P5\\P5-tokenized\\medium";
		folderPath[2] = "P5\\P5-tokenized\\low";
		
		String[] folderName = new String[3];
		folderName[0] = "high";
		folderName[1] = "low";
		folderName[2] = "medium";
		
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
    
	public void analysisTrainingData(ArrayList<Essay> essaySet) throws IOException{
		
  	  BufferedReader inputStream = null;
  	  HashMap<String, String> topicRelatedWords = new HashMap<String, String>();
	    try {
	    	inputStream = new BufferedReader(new FileReader("relatedTopicWords.txt"));
	    	
	    	String words = null;
	        while((words = inputStream.readLine()) != null)
	        {
	        	StringTokenizer tokens = new StringTokenizer(words);
	        	while(tokens.hasMoreTokens())
	        	{
	        		String temp = tokens.nextToken();
	        		topicRelatedWords.put(temp, temp);
	        	}
	        }
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        inputStream.close();
	    }
	    
	   	 EssayAnalysis analysisObj = EssayAnalysis.getEAinstance();
	   	 try {
			analysisObj.analysisAll(essaySet, topicRelatedWords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateAutoGraderModel(ArrayList<Essay> essaySet){
		EssayResult low = new EssayResult();
        EssayResult medium = new EssayResult();
        EssayResult high = new EssayResult();
        int lowSize = 0;
        int mediumSize = 0;
        int highSize = 0;
        
        int size = essaySet.size();
   	 
        for(int i=0; i<size;i++)
        {
	       	 EssayResult er= essaySet.get(i).getResultObject();
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
        }
        
        
		
	}
	
	public void updateModelFromfile(ArrayList<Essay> essaySet, String testPath){
		 //test parts
 		 
 		
 		File folder = new File(testPath);
		   
         File[] listFiles = folder.listFiles();
         try {
		         for(File pathname : listFiles)
		         {	 	        	
			        if(pathname.isFile())
			        	 {   
			        		Essay essays = new Essay();
							essays.setEssay(pathname, "any");
							essaySet.add(essays);
			        	 }
		         }
		    	 //System.out.println(size2);
		    	 //System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	public void outputResult(ArrayList<Essay> essaySet, String outputFileName){
		int size = essaySet.size();
		PrintWriter outputStream = null;
		try
		{
			 
			// check if the file exists and deletes
			File f = new File(outputFileName);
			if(f.exists() && !f.isDirectory())
			{
				f.delete(); 
			}
			
	    	outputStream = new PrintWriter(new FileOutputStream(outputFileName), true);
			for(int j = 0; j < size; j++)
		   	{
		   		 essaySet.get(j).outputEssayStat(outputStream);
		   	}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			outputStream.close();
		}
		
	}
}
