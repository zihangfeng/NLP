package cs421;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// the class for each essay
public class Essay {
	private ArrayList<ArrayList<String>> content;
	private EssayResult res;
	private String markedResult;
	
	public Essay()
	{
		content = new ArrayList<ArrayList<String>>();
		res= new EssayResult();
	}
	
	 

	public void setEssay(File file, String level) throws IOException
	{
		// we also need to update the file's final result
		markedResult = level;
		BufferedReader inputStream = null;
		ArrayList<String> essay = new ArrayList<String>();

        try {
            inputStream = new BufferedReader(new FileReader(file));
            
           
            String line = null;

            while ((line = inputStream.readLine()) != null) {
            	if(line.length() != 0)
            	{
            		essay.add(line);
            	}
            }
            content.add(essay); 
		}
		catch(FileNotFoundException e)
		{
			System.out.println("No files");
		}
		catch(IOException e)
		{
			System.out.println("Reading Error");
		}
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	}
	
	
	
}
