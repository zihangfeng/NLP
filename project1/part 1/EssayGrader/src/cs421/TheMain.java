package cs421;

import java.io.File;
import java.util.*;

public class TheMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 File folder = null;
	     File[] pathnames;
	     EssayAnalysis grader=EssayAnalysis.getEAinstance();
	     ArrayList<Essay> essaySet = new ArrayList<Essay>();
	      try
	      {      
	         folder = new File("testFileFolder");
	         pathnames = folder.listFiles();
	         for(File path : pathnames)
	         {
	        	 if(path.isFile())
	        	 {   
	        		 Essay essays = new Essay();
	        		 essays.setEssay(path);
	        		 essaySet.add(essays);
	        	 }
	         }
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	      
	      
	   }
		

}
