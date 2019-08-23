import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();
        List texts = new ArrayList();
        while(!myString.equals("bye")){
            if (myString.equals("list")){
                for (int i=0; i < texts.size(); i++)
                    System.out.println((i+1) + ". " + texts.get(i));
            }
            else {
                System.out.println("added: " + myString);
                texts.add(myString);
            }
                myString = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}