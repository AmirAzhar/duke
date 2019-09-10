package main.java.Types;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getFormat(){
        return "x";
    }

    public String getDescription() {
        return description;
    }

    public boolean contains(String string) {
        if (this.description.contains(string))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}