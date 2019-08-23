import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();
        List<Task> texts = new ArrayList();

        while(!myString.equals("bye")){
            String[] splitter = myString.split(" ");
            if (myString.equals("list")){
                for (int i=0; i < texts.size(); i++)
                    System.out.println((i+1) + ". " + "[" + texts.get(i).getStatusIcon() + "] " + texts.get(i).description);
            }
            else if (splitter[0].equals("done")){
                int taskNumber = Integer.parseInt(splitter[1]);
                texts.get(taskNumber - 1).setDone();
            }
            else {
                Task task = new Task(myString);
                texts.add(task);
                System.out.println("added: " + myString);

            }
                myString = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}