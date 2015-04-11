package cs421;

import java.io.File;


public class TheMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 File folder = null;
	     File[] pathnames;
	     EssayAnalysis grader=EssayAnalysis.getEAinstance();
	     Essay essays = new Essay();
	      try
	      {      
	         folder = new File("testFileFolder");
	         pathnames = folder.listFiles();
	         for(File path : pathnames)
	         {
	        	 if(path.isFile())
	        	 {
	        		 essays.setEssay(path);
	        	 }
	         }
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	      
	      
	   }
		

}
