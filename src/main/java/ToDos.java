package main.java;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String i, String description) {
        super(description);
        this.isDone = i.equals("1");
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getFormat(){
        return "T | " + isDone + " | " + super.getDescription();
    }
}