package main.java;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event (String i, String description, String at) {
        super(description);
        this.at = at;
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getFormat(){
        return "E | " + isDone + " | " + super.getDescription() + " | "  + at;
    }
}