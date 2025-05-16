public class End extends Command {
    @Override
    public void execute() {
        System.out.println("END...");
    }

    @Override
    public boolean exit() {
        return true;
    }
}
