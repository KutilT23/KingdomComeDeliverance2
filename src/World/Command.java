package World;

/**
 * class for commands
 */
public abstract class Command {
    protected String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public abstract void execute();
    public abstract boolean exit();
}
