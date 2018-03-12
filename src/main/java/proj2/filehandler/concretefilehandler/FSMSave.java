package proj2.filehandler.concretefilehandler;

import proj2.filehandler.*;
import java.io.*;
import proj2.document.Document;
import java.util.ArrayList;



public class FSMSave implements Saver, Loader
{
    Document d;
    PrintWriter writer;
    public FSMSave(Document given) {
        d=given;
    }


    public void save(String fileName){
        try{
             String home = System.getProperty("user.home");
             File file = new File(home+"/Downloads/" + fileName + ".fsm");


             file.getParentFile().mkdirs();
        // Write into the text file
        writer = new PrintWriter(file);
        writer.println("@@Contains only Information about the finite state machine, This cannot be imported back into the FiniteStateMachine With the Gui");
        ArrayList<String> states = d.getStates();
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
             System.out.println("Unable to save file");
    }
}


    public void load(String fileName){
        try {
            // Create a file object to hold the path to the text file
            String home = System.getProperty("user.home");
            File file = new File(home+"/Downloads/" + fileName + ".fsm");
            // Create file if it doesn't exist
            file.getParentFile().mkdirs();

            // This will reference one line at a time
            String line = null;
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                if(!line.contains("@")){
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

                // Always close files.
                bufferedReader.close();
        }
         catch(FileNotFoundException ex) {
             System.out.println(
                 "Unable to open file");
         }
         catch(IOException ex) {
             System.out.println("Error reading file");
         }
    }


    /**
    * Simply returns the name of the class
    * Allows for a readable name to be shown in a menu
    */
    public String toString() {
        return "FSMSave";
    }
}
