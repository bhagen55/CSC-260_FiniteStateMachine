package proj2;

import proj2.document.Document;
/*
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
*/
import java.io.*;


public class FileFormatController
{
	Document d;
	PrintWriter writer;
	public FileFormatController(Document given){
		d=given;
	}

	public void saveFile(String fileName) throws FileNotFoundException{
		// Create a file object to hold the path to the text file
		String home = System.getProperty("user.home");
		File file = new File(home+"/Downloads/" + fileName + ".txt");
		// Create file if it doesn't exist
		file.getParentFile().mkdirs();



		// Write into the text file
		writer = new PrintWriter(file);


		writer.println(d.toString());

		writer.close();

	}





	public void loadFile(String fileName) throws FileNotFoundException
	{
		// Create a file object to hold the path to the text file
		String home = System.getProperty("user.home");
		File file = new File(home+"/Downloads/" + fileName + ".txt");
		// Create file if it doesn't exist
		file.getParentFile().mkdirs();

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);
            boolean havePassedCoordinates=false;
            while((line = bufferedReader.readLine()) != null) {
	            if(line.compareTo("$") == 0)
	            {
	            	havePassedCoordinates=true;
	            }

	           	if(!havePassedCoordinates)
	           	{
	           		String[] lineParts=line.split("\\|");
					for (String part: lineParts) {
						System.out.println(part);
					}
	           		d.addVertex(lineParts[0],Integer.parseInt(lineParts[1]),Integer.parseInt(lineParts[2]));

	           	}
	           	else{
	           		String[] lineParts=line.split("\\(");
	           		String vertex="";
	           		for(String part: lineParts)
	           		{
	           			if(!part.contains(")"))
	           			{
	           				vertex=part;
	           			}
	           			else{
	           				String[] edge=part.split(",");
	           				String temp = edge[1];
	           				edge[1]=temp.substring(0,edge[1].length()-2);
	           				d.addEdge(vertex,edge[0],edge[1]);
	           			}
	           		}

	           	}
	           }

	            // Always close files.
	            bufferedReader.close();
        }
         catch(FileNotFoundException ex) {
             System.out.println(
                 "Unable to open file '" +
                 fileName + "'");
         }
         catch(IOException ex) {
             System.out.println(
                 "Error reading file '"
                 + fileName + "'");

         }
	}
}
