import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Duke {
    private static ArrayList<Task> texts = new ArrayList();
    public static void main(String[] args) throws IOException {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();


        //as long as command is not bye
        while(!myString.equals("bye")){

            String[] splitter1 = myString.split(" ");

            try {
                //commands have no description
                if (myString.equals("todo") || myString.equals("event") || myString.equals("deadline")){
                    throw new DukeExceptions("☹ OOPS!!! The description of a " + myString + " cannot be empty.");
                }

                //command is list
                else if (myString.equals("list")) {
                    readFile();
//                    System.out.println("Here are the tasks in your list:");
//                    for (int i = 0; i < texts.size(); i++)
//                        System.out.println((i + 1) + ". " + texts.get(i).toString());
                }

                //command is done
                else if (splitter1[0].equals("done")) {
                    int taskNumber = Integer.parseInt(splitter1[1]);
                    texts.get(taskNumber - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + texts.get(texts.size() - 1).getStatusIcon() + "] " + texts.get(texts.size() - 1).description);
                }

                //command is deadline
                else if (splitter1[0].equals("deadline")) {
                    String D = myString.substring(9); // after 9 letters
                    String[] splitterD = D.split("/by ");
                    Task deadline = new Deadline(splitterD[0], splitterD[1]);
                    texts.add(deadline);
                    addTaskToFile("[D][✗] " + splitterD[0] + " (at: " + splitterD[1] + ")");
                    int l = lineCounter();
                    System.out.println("Got it. I've added this task:\n  " + texts.get(texts.size() - 1).toString());
                    System.out.println("Now you have " + l + " task(s) in the list.");
                }

                //command is event
                else if (splitter1[0].equals("event")) {
                    String E = myString.substring(6); // after 6 letters
                    String[] splitterE = E.split("/at ");
                    Task event = new Event(splitterE[0], splitterE[1]);
                    texts.add(event);
                    addTaskToFile("[E][✗] " + splitterE[0] + " (at: " + splitterE[1] + ")");
                    int l = lineCounter();
                    System.out.println("Got it. I've added this task:\n  " + texts.get(texts.size() - 1).toString());
                    System.out.println("Now you have " + l + " task(s) in the list.");
                }

                //command is todos
                else if (splitter1[0].equals("todo")) {
                    String tempT = myString.substring(5);
                    Task todo = new ToDos(tempT);
                    texts.add(todo);
                    addTaskToFile("[T][✗] " + tempT);
                    int l = lineCounter();
                    System.out.println("Got it. I've added this task:\n  " + texts.get(texts.size() - 1).toString());
                    System.out.println("Now you have " + l + " task(s) in the list.");
                }

                //random commands that isn't any of the above
                else{
                    throw new DukeExceptions("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            catch (DukeExceptions | IOException ex){
                System.out.println(ex.getMessage());
            }

            //scans next user input
            myString = scanner.nextLine();
        }
        //command is bye
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void readFile()throws IOException {
        try (BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\itoot\\Desktop\\duke\\src\\main\\dukeData.txt"))){
        int lines = 0;
        while (br1.readLine() != null)
            lines++;
        BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\itoot\\Desktop\\duke\\src\\main\\dukeData.txt"));
        String line;
            for (int i=1; i <= lines; i++){
                line = br2.readLine();
                System.out.println(i + ". " + line);
            }
        }
    }

    public static void addTaskToFile(String s) throws IOException{
        try (FileWriter fWriter = new FileWriter("C:\\Users\\itoot\\Desktop\\duke\\src\\main\\dukeData.txt", true)){
            fWriter.write(s + "\n");
        }
    }

    public static int lineCounter() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\itoot\\Desktop\\duke\\src\\main\\dukeData.txt");
        LineNumberReader lnr = new LineNumberReader(fr);

        int linenumber = 0;

        while (lnr.readLine() != null){
            linenumber++;
        }
        lnr.close();
        return linenumber;
    }
}