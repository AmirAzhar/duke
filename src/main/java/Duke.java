import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();
        ArrayList<Task> texts = new ArrayList();

        //as long as command is not bye
        while(!myString.equals("bye")){

            String[] splitter1 = myString.split(" ");

            //command is list
            if (myString.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i < texts.size(); i++)
                    System.out.println((i+1) + ". " + texts.get(i).toString());
            }

            //command is done
            else if (splitter1[0].equals("done")){
                int taskNumber = Integer.parseInt(splitter1[1]);
                texts.get(taskNumber - 1).setDone();
            }

            //command is deadline
            else if (splitter1[0].equals("deadline")){
                String D = myString.substring(9); // after 9 letters
                String[] splitterD = myString.split("/by ");
                Deadline deadline = new Deadline(splitterD[0], splitterD[1]);
                texts.add(deadline);
                System.out.println("Got it. I've added this task:\n  " + texts.get(texts.size() - 1).toString());
                System.out.println ("Now you have " + texts.size() + " task(s) in the list.");
            }

            //command is event
            else if (splitter1[0].equals("event")){
                String E = myString.substring(9); // after 6 letters
                String[] splitterE = myString.split("/at ");
                Event event = new Event(splitterE[0], splitterE[1]);
                texts.add(event);
                System.out.println("Got it. I've added this task:\n  " + texts.get(texts.size() - 1).toString());
                System.out.println ("Now you have " + texts.size() + " task(s) in the list.");
            }

            //command is todo
            else if (splitter1[0].equals("todo")){
                String tempT = myString.substring(5);
                ToDos todo = new ToDos(tempT);
                texts.add(todo);
                System.out.println("Got it. I've added this task:\n  " + texts.get(texts.size() - 1).toString());
                System.out.println ("Now you have " + texts.size() + " task(s) in the list.");
            }
            myString = scanner.nextLine();
        }
        //command is bye
        System.out.println("Bye. Hope to see you again soon!");
    }
}