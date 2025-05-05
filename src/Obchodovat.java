public class Obchodovat extends Command{
    @Override
    public void execute() {
    Obchod obchod = new Obchod();
    obchod.nacistItemy();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
