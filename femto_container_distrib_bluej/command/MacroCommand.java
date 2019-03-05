package command;

public class MacroCommand implements Command{
    private Command[] commands;

    public void setCommands(Command[] commands){
        this.commands = commands;
    }

    public void execute(){
        for(Command c : commands){
            c.execute();
        }
    }

    public void undo(){
        for(Command c : commands){
            c.undo();
        }
    }
    
}
