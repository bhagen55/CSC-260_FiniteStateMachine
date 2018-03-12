package proj2.filehandler.concretefilehandler;

import proj2.document.Document;
import proj2.filehandler.*;
import java.util.ArrayList;
import proj2.document.Action;
import proj2.document.actions.*;
/*
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
*/
import java.io.*;

/**
* Saves and loads finite state machines from proprietary human readable file format
*/
public class TextSave implements Saver, Loader
{
	Document d;
	PrintWriter writer;
	public TextSave(Document given){
		d=given;
	}

	/**
	* Simply returns the name of the class
	* Allows for a readable name to be shown in a menu
	*/
	public String toString() {
		return "TextSave";
	}

	/**
	* Saves a fsm into a human readable text file format.
	* Saves directly into user's downloads folder
	*
	* @param fileName string filename for file, not including filetype or path
	*/
	public void save(String fileName) {
		try {
		// Create a file object to hold the path to the text file
		String home = System.getProperty("user.home");
		File file = new File(home+"/Downloads/" + fileName + ".txt");
		// Create file if it doesn't exist
		file.getParentFile().mkdirs();



		// Write into the text file
		writer = new PrintWriter(file);

		writer.println("@@ Top section is: StateName|xPosition|yPosition|ifAnAcceptState|nameOfActionDoneHere");
		writer.println("@@ Bottom section is: StateName than its transitions, each in parenthesis separated by a comma");
		writer.println("@@ in each parenthesis it is ordered (State this transition points to, this transition's weight)");
		writer.println("@@ the $ in the middle separates the coordiante section from the transitions section");

		ArrayList<String> states = d.getStates();
		for(String state: states){
			writer.println(state+"|"+d.getX(state)+"|"+d.getY(state)+"|"+d.getAccept(state)+"|"+d.getAction(state).toString());
		}
		writer.println("$");

		for(String state:states){
			String curLine=state;
			int x=0;
			ArrayList<String> curTransitions=d.getTransitionsForState(state);
			for(String transition:curTransitions)
			{
				String[] stateAndWeight=transition.split("Ω");
				curLine+="("+stateAndWeight[0]+","+stateAndWeight[1]+")";
				if(x<curTransitions.size()-2)
				{
					curLine+=",";
				}
			}
			writer.println(curLine);
		}

		writer.close();
	}
	catch(FileNotFoundException ex) {
		System.out.println(
			"Unable to open file");
	}

}


	/**
	* Loads a fsm from a formatted text file
	*
	* @param fileName string filename for file to load, not including filetype or path
	*
	*/
	public void load(String fileName)
	{
		try {
		// Create a file object to hold the path to the text file
		String home = System.getProperty("user.home");
		File file = new File(home+"/Downloads/" + fileName + ".txt");
		// Create file if it doesn't exist
		file.getParentFile().mkdirs();

        // This will reference one line at a time
        String line = null;


            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);
            boolean havePassedCoordinates=false;
            d.empty();
            while((line = bufferedReader.readLine()) != null) {
            	if(!line.contains("@")){
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
		           		String curStateName= lineParts[0];
		           		d.addState(curStateName,Integer.parseInt(lineParts[1]),Integer.parseInt(lineParts[2]));
		           		if(lineParts[3].equals("true"))
		           		{
		           			d.toggleAccept(curStateName);
		           		}
		           		if(lineParts[4].equals("NoAction")){
		           			Action none= new NoAction();
		           			d.addAction(curStateName,none);
		           		}
		           		else if(lineParts[4].equals("PrintAction")){
		           			Action print=new PrintAction();
		           			d.addAction(curStateName,print);
		           		}
		           		else if(lineParts[4].equals("SoundAction")){
		           			Action sound=new SoundAction();
		           			d.addAction(curStateName,sound);
		           		}
		           		else{
		           			System.out.println("No Action added");
		           		}

		           	}
		           	else{
		           		String[] lineParts=line.split("\\(");
		           		String state="";
		           		for(String part: lineParts)
		           		{
		           			if(!part.contains(")"))
		           			{
		           				state=part;
		           			}
		           			else{
		           				String[] transition=part.split(",");
		           				String temp = transition[1];
		           				transition[1]=temp.substring(0,transition[1].length()-1);
		           				d.addTransition(state,transition[0],transition[1]);
		           			}
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
