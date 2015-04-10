package cs421;

import java.io.BufferedReader;
import java.io.File;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Essay {
	private ArrayList<ArrayList<String>> content;
	
	public Essay()
	{
		content = new ArrayList<ArrayList<String>>();
	}
	

	public void setEssay(File file) throws IOException
	{
		BufferedReader inputStream = null;
		ArrayList<String> essay = new ArrayList<String>();

        try {
            inputStream = new BufferedReader(new FileReader(file));
            
            String theEssay = null;
            String line = null;

            while ((line = inputStream.readLine()) != null) {
                theEssay += line;
            }
            
            // add a new essay to it
            if(theEssay != null)
            {
            	StringTokenizer tokenline = new StringTokenizer(theEssay, ".");
            	while(tokenline.hasMoreTokens())
            	{
            		essay.add(tokenline.nextToken());
            	}
            	content.add(essay);
            }
		}
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	}
	
	
	
}
