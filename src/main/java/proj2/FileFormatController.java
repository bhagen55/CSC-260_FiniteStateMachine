package proj2;

import proj2.document.Document;
import java.io.*;


public class FileFormatController
{
	Document d;
	PrintWriter writer;
	public FileFormatController(Document given) throws IOException
	{
		d=given;
	}

	public void saveFile() {
		//writer = new PrintWriter("FiniteStateMachine.txt");
		//writer.print(d.toString());
	}


	


	public void loadFile(String FiniteStateMachine)
	{
			 // The name of the file to open.

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(FiniteStateMachine);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            boolean havePassedCoordinates=false;
            while((line = bufferedReader.readLine()) != null) {
            if(line.equals("$"))
            {
            	havePassedCoordinates=true;
            }   

           	if(!havePassedCoordinates)
           	{
           		String[] lineParts=line.split("|");
           		d.addVertex(lineParts[0],lineParts[1],lineParts[2]);
         
           	}
           	else{
           		String[] lineParts=line.split("(");
           		String vertex;
           		for(String part: lineParts)
           		{
           			if(!part.contains(")"))
           			{
           				v=part;
           			}
           			else{
           				String[] edge=part.split(",");
           				edge[1]=edge[1].(0,edge[1].length()-2);
           				d.addEdge(vertex,edge[0],edge[1]);
           			}
           		}

           	}

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FiniteStateMachine + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FiniteStateMachine + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
}
	

}
