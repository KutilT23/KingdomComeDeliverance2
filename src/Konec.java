public class Konec extends Command {
    @Override
    public void execute() {
        System.out.println("Konec...");
    }

    @Override
    public boolean exit() {
        return true;
    }
}
