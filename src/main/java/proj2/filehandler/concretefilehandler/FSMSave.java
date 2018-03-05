package proj2.filehandler.concretefilehandler;

import proj2.filehandler.Saver;
import java.io.*;
import proj2.document.Document;
import java.util.ArrayList;



public class FSMSave implements Saver
{
    Document d;
    PrintWriter writer;
    public FSMSave(Document given) {
        d=given;
    }

    public void save(){
        String fileName= "FSMSave";
        try{

       saveFile();
   }
   catch(FileNotFoundException ex) {
             System.out.println(
                 "Unable to open file '" +
                 fileName + "'");
         }
         
    }


   public void saveFile() throws FileNotFoundException{
    String fileName="FSMSave";
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/" + fileName + ".FSM");


        file.getParentFile().mkdirs();


        try{
        // Write into the text file
        writer = new PrintWriter(file);
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
             System.out.println(
                 "Unable to open file '" +
                 fileName + "'");
         }
         }


    public void loadFile() throws FileNotFoundException{
        String fileName="FSMSave";
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
                        d.addState(lineParts[0],Integer.parseInt(lineParts[1]),Integer.parseInt(lineParts[2]));

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
    

    /**
    * Simply returns the name of the class
    * Allows for a readable name to be shown in a menu
    */
    public String toString() {
        return "FSMSave";
    }
}
