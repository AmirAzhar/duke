package main.java;

import main.java.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static ArrayList<Task> texts = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        update();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();


        //as long as command is not bye
        while (!myString.equals("bye")) {

            String[] splitter1 = myString.split(" ");

            try {
                //commands have no description
                if (myString.equals("todo") || myString.equals("event") || myString.equals("deadline")) {
                    throw new DukeExceptions("☹ OOPS!!! The description of a " + myString + " cannot be empty.");
                }

                //command is list
                else if (myString.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < texts.size(); i++){
                        System.out.print(i + 1 + ". ");
                        System.out.println(texts.get(i).toString());
                    }
                }

                //command is done
                else if (splitter1[0].equals("done")) {
                    int taskNumber = Integer.parseInt(splitter1[1]);
                    texts.get(taskNumber - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + texts.get(taskNumber-1).getDescription());
                    save(texts);
                }

                //command is deadline
                else if (splitter1[0].equals("deadline")) {
                    String[] deadlineInputs = myString.split(" ");
                    List<String> listInputs = new ArrayList<>(Arrays.asList(deadlineInputs));
                    listInputs.remove(0);
                    String description = String.join(" ", listInputs);
                    String[] dayInput = description.split(" /by ");
                    Deadline newDeadline = new Deadline("1", dayInput[0], dayInput[1]);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("\t" + "[D]" + "[✗] " + dayInput[0] + " (by: " + dayInput[1] + ")");
                    texts.add(newDeadline);
                    save(texts);
                    System.out.println("Now you have " + texts.size() + " tasks in the list.");
                }

                //command is event
                else if (splitter1[0].equals("event")) {
                    String[] eventInputs = myString.split(" ");
                    List<String> listInputs = new ArrayList<>(Arrays.asList(eventInputs));
                    listInputs.remove(0);
                    String description = String.join(" ", listInputs);
                    String[] dayInput = description.split(" /at ");
                    Event newEvent = new Event("1", dayInput[0], dayInput[1]);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("\t" + "[E]" + "[✗] " + dayInput[0] + " (at: " + dayInput[1] + ")");
                    texts.add(newEvent);
                    save(texts);
                    System.out.println("Now you have " + texts.size() + " tasks in the list.");
                }

                //command is todos
                else if (splitter1[0].equals("todo")) {
                    String[] todoInputs = myString.split(" ");
                    List<String> listInputs = new ArrayList<>(Arrays.asList(todoInputs));
                    listInputs.remove(0);
                    String description = String.join(" ", listInputs);
                    ToDos newToDos = new ToDos(description);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("\t" + "[T]" + "[✗] "  + newToDos.getDescription());
                    texts.add(newToDos);
                    save(texts);
                    System.out.println("Now you have " + texts.size() + " tasks in the list.");
                }

                //random commands that isn't any of the above
                else {
                    throw new DukeExceptions("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeExceptions ex) {
                System.out.println(ex.getMessage());
            }

            //scans next user input
            myString = scanner.nextLine();
        }
        //command is bye
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void save(ArrayList<Task> arr) {
        File file = new File("C:\\Users\\itoot\\Desktop\\duke\\src\\main\\dukeData.txt");
        try {
            PrintWriter output = new PrintWriter(file);
            for (Task x : arr) {
                output.println(x.getFormat());
            }
            output.close();
        } catch (IOException ex) {
            System.out.print("ERROR: Not Available");
        }
    }

    public static void update() throws FileNotFoundException {
        File fileToRead = new File("C:\\Users\\itoot\\Desktop\\duke\\src\\main\\dukeData.txt");
        Scanner scan_file = new Scanner(fileToRead);
        while (scan_file.hasNextLine()) {
            String line = scan_file.nextLine();
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
