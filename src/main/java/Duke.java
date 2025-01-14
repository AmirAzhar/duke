package main.java;

import main.java.Exceptions.DukeExceptions;
import main.java.Types.Deadline;
import main.java.Types.Event;
import main.java.Types.Task;
import main.java.Types.ToDos;

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

import static main.java.Storage.Storage.save;
import static main.java.Storage.Storage.update;

public class Duke {
    private static ArrayList<Task> texts = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        update(texts);
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

                //command is delete
                else if (splitter1[0].equals("delete")){
                    int taskNumber = Integer.parseInt(splitter1[1]);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + texts.get(taskNumber-1).toString());
                    texts.remove(taskNumber-1);
                    System.out.println("Now you have " + texts.size() + " tasks in the list.");
                    save(texts);
                }

                //command is list
                else if (myString.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < texts.size(); i++){
                        System.out.print(i + 1 + ". ");
                        System.out.println(texts.get(i).toString());
                    }
                }

                //command is find
                else if (splitter1[0].equals("find")){
                    int counter = 0;
                    String findThis = splitter1[1];
                    System.out.println("Here are the matching tasks in your list:");
                    for (int i=0; i < texts.size(); i++){
                        if(texts.get(i).contains(findThis)) {
                            counter++;
                            System.out.println(counter + ". " + texts.get(i).toString());
                        }
                    }
                }

                //command is done
                else if (splitter1[0].equals("done")) {
                    int taskNumber = Integer.parseInt(splitter1[1]);
                    texts.get(taskNumber - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + texts.get(taskNumber-1).toString());
                    save(texts);
                }

                //command is deadline
                else if (splitter1[0].equals("deadline")) {
                    String[] deadlineInputs = myString.split(" ");
                    List<String> listInputs = new ArrayList<>(Arrays.asList(deadlineInputs));
                    listInputs.remove(0);
                    String description = String.join(" ", listInputs);
                    String[] dayInput = description.split(" /by ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                    LocalDateTime formatDateTime = LocalDateTime.parse(dayInput[1], formatter);
                    Deadline newDeadline = new Deadline("1", dayInput[0], formatDateTime.format(formatter));
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
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                    LocalDateTime formatDateTime = LocalDateTime.parse(dayInput[1], formatter);
                    Event newEvent = new Event("1", dayInput[0], formatDateTime.format(formatter));
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

}
