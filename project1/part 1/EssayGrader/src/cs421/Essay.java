package cs421;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// the class for each essay
public class Essay {
	private ArrayList<String> essay;
	private EssayResult res;
	private String markedResult;
	private String fileName;
	
	public Essay()
	{
		essay = new ArrayList<String>();
		res= new EssayResult();
		fileName = null;
	}
	
	 

	public void setEssay(File file, String level) throws IOException
	{
		// we also need to update the file's final result
		markedResult = level;
		BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(file));
            fileName = file.getName();
           
            String line = null;

            while ((line = inputStream.readLine()) != null) {
            	if(line.length() != 0)
            	{
            		essay.add(line);
            	}
            }
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
	
	public ArrayList<String> getEssay()
	{
		return essay;
	}
	
	public EssayResult getResultV()
	{
		return res;
	}
	
	public void outputEssayStat(PrintWriter outputStream)
	{
		outputStream.println(fileName + " " + res.toString() + " " + markedResult);
	}
}
