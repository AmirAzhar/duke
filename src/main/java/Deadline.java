package main.java;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String i, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getFormat(){
        return "D | " + isDone + " | " + super.getDescription() + " | " + by;
    }
}