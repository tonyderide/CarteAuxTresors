package fr.deride.sbtreasuremap.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EntryFileOutput {

    public void createFile(){
        try {
            File myObj = new File("C:\\projet\\sb-treasure-map\\output.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created to output the simulation: " + myObj.getName());
            } else {
                System.out.println(myObj.getName() + " already exists.");
            }
        } catch ( IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeOnFile(List<String> outputLines){
        try{
            FileWriter myWriter = new FileWriter("output.txt");
            outputLines.forEach(line -> {
                try {
                    myWriter.write(line + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            myWriter.close();
            System.out.println("Data successfully wrote on the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
    }
    }

}
