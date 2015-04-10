package cs421;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
            
            String line;

            while ((line = inputStream.readLine()) != null) {
                essay.add(line);
            }
            
            // add a new essay to it
            content.add(essay);
          
		}
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	}
	
	
	
}
