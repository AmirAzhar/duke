package main.java.Storage;

import main.java.Types.Deadline;
import main.java.Types.Event;
import main.java.Types.Task;
import main.java.Types.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
//    private ArrayList<Task> saveText;
//
//    public Storage(ArrayList<Task> txt) {
//        this.saveText = txt;
//    }

    public static void save(ArrayList<Task> arr) throws FileNotFoundException {
        File readThis = new File("C:\\Users\\itoot\\Desktop\\duke\\src\\dukeData.txt");
        PrintWriter output = new PrintWriter(readThis);
        for (Task x : arr) {
            output.println(x.getFormat());
        }
        output.close();
    }

    public static void update(ArrayList<Task> texts) throws FileNotFoundException {

        File readThis = new File("C:\\Users\\itoot\\Desktop\\duke\\src\\dukeData.txt");
        Scanner scannedFile = new Scanner(readThis);
        while (scannedFile.hasNextLine()) {

            String line = scannedFile.nextLine();
            String[] splitStr = line.split(" \\| ");

            if(splitStr[0].equals("T"))
                texts.add(new ToDos(splitStr[1], splitStr[2]));
            else if(splitStr[0].equals("E"))
                texts.add(new Event(splitStr[1], splitStr[2], splitStr[3]));
            else if(splitStr[0].equals("D"))
                texts.add(new Deadline(splitStr[1], splitStr[2], splitStr[3]));
            else
                texts.add(new Task(splitStr[1]));
        }
    }
}
