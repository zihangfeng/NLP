
package cs421;


import java.io.IOException;

public class TheMain {

	public static void main(String[] args) throws IOException {	    	  
	    	 graderModer grader= new graderModer();
	    	 
	    	 
	    	 System.out.println("Start the program!");
	    	 System.out.println("Once it finishes, a message will display!");
	    	 
	    	 //get the training stats
	    	 grader.readTrainingData();
	    	 grader.analysisTrainingData();
	    	 grader.updateAutoGraderModel();
	    	 System.out.println("The trainning is completed!");	    	 
	    	 grader.updateModelFromfile();
	    	 
	    	 //predict the test files
	    	 System.out.println("Reading the test files..................");	
	    	 grader.examineTestFile("input\\test\\tokenized");
	    	 grader.outputResult();
	    	 System.out.println("Finished!");
	    	 System.out.println("Please check the output result for the test files");
	}
}

