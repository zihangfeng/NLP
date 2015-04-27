package cs421;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class graderModer {
	private ArrayList<Essay> TrainingEssaySet;
	private ArrayList<Essay> TestEssaySet;
	private EssayResult[] model;
	public graderModer(){ 
		TrainingEssaySet = new ArrayList<Essay>();
		TestEssaySet = new ArrayList<Essay>();
		model=new EssayResult[3];
		for(int i = 0; i < 3; i++)
		{
			model[i] = new EssayResult();
		}
	}
    
	public void readTrainingData() throws IOException{
		
		String[] folderPath = new String[3]; 
		folderPath[0] = "P5\\P5-tokenized\\high";
		folderPath[1] = "P5\\P5-tokenized\\medium";
		folderPath[2] = "P5\\P5-tokenized\\low";
		
		String[] folderName = new String[3];
		folderName[0] = "high";
		folderName[1] = "medium";
		folderName[2] = "low";
		
			for(int i = 0; i < 3; i++)
		   	 {	   	 
		         File folder = new File(folderPath[i]);
		   
		         File[] listFiles = folder.listFiles();
		         
		         for(File pathname : listFiles)
		         {	 	        	
			        if(pathname.isFile())
		        	{   
		        		 Essay essay = new Essay();
		        		 essay.setEssay(pathname, folderName[i]);
		        		 TrainingEssaySet.add(essay);
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
		
		getTheAverage();
		
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
	       		high.addTwoResult(er);
	       	 }
	       	 else if(TrainingEssaySet.get(i).getmarkedResult().equals("medium"))
	       	 {
		        mediumSize++;
		        medium.addTwoResult(er);	        		 
	       	 }
	       	 else if(TrainingEssaySet.get(i).getmarkedResult().equals("low"))
	       	 {
		        lowSize++;
		        low.addTwoResult(er);	        		 
	       	 }
	       	 else
	       	 {
	       		 System.out.println("Something is wrong with file");
	       	 }
        }
        
        
        PrintWriter outputStream = null;
		try
		{
			
	    	outputStream = new PrintWriter(new FileOutputStream("trainingReslutAvg.txt"));
	    	
	    	double temp[] = high.getReslutDoubleValue();
	    	//int doubleSize = high.getReslutDoubleValue().length;
	    	
	    	//for(int i = 0; i < doubleSize; i++)
			outputStream.println("high	" + temp[0]/highSize + " " + temp[1]/highSize + " " + temp[2]/highSize + " " + temp[3]/highSize + " " + temp[4]/highSize + " " + temp[5]/highSize + " " + temp[6]/highSize + " " + temp[7]/highSize);
			
			temp = medium.getReslutDoubleValue();
			outputStream.println("medium	" + temp[0]/mediumSize + " " + temp[1]/mediumSize + " " + temp[2]/mediumSize + " " + temp[3]/mediumSize + " " + temp[4]/mediumSize + " " + temp[5]/mediumSize + " " + temp[6]/mediumSize + " " + temp[7]/mediumSize);

			temp = low.getReslutDoubleValue();
			outputStream.println("low		" + temp[0]/lowSize + " " + temp[1]/lowSize + " " + temp[2]/lowSize + " " + temp[3]/lowSize + " " + temp[4]/lowSize + " " + temp[5]/lowSize + " " + temp[6]/lowSize + " " + temp[7]/lowSize);
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
	
	private void getTheAverage()
	{
		int size = TrainingEssaySet.size();
	   	
        for(int i=0; i<size;i++)
        {
	       	 TrainingEssaySet.get(i).getResultObject().update(TrainingEssaySet.get(i).getEssay().size());
        }
	}
	
	public void updateModelFromfile(){
		Scanner inputStream = null;

		try{
			inputStream = new Scanner(new FileInputStream("trainingReslutAvg.txt"));
			for(int i = 0; i < 3; i++)
			{
				System.out.println(model[i].getReslutDoubleValue()[0]);
				if(inputStream.hasNextLine())
				{
					String temp = inputStream.nextLine();
					StringTokenizer tokens = new StringTokenizer(temp);
					tokens.nextToken();// no need to get the level;
					int size = model[i].getReslutDoubleValue().length;
					for(int j = 0; j < size; j++)
					{	
						model[i].getReslutDoubleValue()[j] = Double.parseDouble(tokens.nextToken());
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			inputStream.close();
		}
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
		        		Essay essay = new Essay();
						essay.setEssay(pathname, "any");
						TestEssaySet.add(essay);
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
	
	public void outputTraingResult(){
		int size = TrainingEssaySet.size();
		PrintWriter outputStream = null;
		try
		{
			// check if the file exists and deletes
			File f = new File("trainingFile.txt");
			if(f.exists() && !f.isDirectory())
			{
				f.delete(); 
			}
			
	    	outputStream = new PrintWriter(new FileOutputStream("trainingFile.txt"), true);
			for(int j = 0; j < size; j++)
		   	{
				TrainingEssaySet.get(j).outputEssayStat(outputStream);
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
