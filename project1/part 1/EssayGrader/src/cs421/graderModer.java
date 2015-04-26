package cs421;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class graderModer {
	private ArrayList<Essay> TrainingEssaySet;
	private ArrayList<Essay> TestEssaySet;
	private EssayResult[] model;
	public graderModer(){ 
		TrainingEssaySet = new ArrayList<Essay>();
		TestEssaySet = new ArrayList<Essay>();
		model=new EssayResult[3];
	}
    
	public void readTrainingData() throws IOException{
		
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
				        		 TrainingEssaySet.add(essays);
				        	 }
			         }
		
		   	 }
   	 
	}
    
	public void analysisTrainingData() throws IOException{

	    EssayAnalysis analysisObj = EssayAnalysis.getEAinstance();
	   	 try {
			analysisObj.analysisAll(TrainingEssaySet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateAutoGraderModel(){
		EssayResult low = new EssayResult();
        EssayResult medium = new EssayResult();
        EssayResult high = new EssayResult();
        int lowSize = 0;
        int mediumSize = 0;
        int highSize = 0;
        
        int size = TrainingEssaySet.size();
   	 
        for(int i=0; i<size;i++)
        {
	       	 EssayResult er= TrainingEssaySet.get(i).getResultObject();
	       	 if(TrainingEssaySet.get(i).getmarkedResult().equals("high"))
	       	 {
	       		highSize++;
	       		high.addtwo(er);
	       	 }
	       	 else if(TrainingEssaySet.get(i).getmarkedResult().equals("medium"))
	       	 {
		        mediumSize++;
		        medium.addtwo(er);	        		 
	       	 }
	       	 else if(TrainingEssaySet.get(i).getmarkedResult().equals("low"))
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
	
	public void updateModelFromfile(){
 	
	}

	public void examineTestFile(String path)
	{
		File folder = new File(path);
		   
	    File[] listFiles = folder.listFiles();
	    try {
		         for(File pathname : listFiles)
		         {	 	        	
		        	 if(pathname.isFile())
		        	 {   
		        		Essay essays = new Essay();
						essays.setEssay(pathname, "any");
						TestEssaySet.add(essays);
		        	 }
		         }
			}
	    	catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void outputResult(){
		int size = TestEssaySet.size();
		PrintWriter outputStream = null;
		try
		{
			// check if the file exists and deletes
			File f = new File("testFile.txt");
			if(f.exists() && !f.isDirectory())
			{
				f.delete(); 
			}
			
	    	outputStream = new PrintWriter(new FileOutputStream("testFile.txt"), true);
			for(int j = 0; j < size; j++)
		   	{
				TestEssaySet.get(j).outputEssayStat(outputStream);
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
