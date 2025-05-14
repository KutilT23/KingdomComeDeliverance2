import java.util.Scanner;

public class Pohyb extends Command{
    private Mapa svet = new Mapa();
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Nacházíš se na lokaci\uD83D\uDCCD\uD83D\uDDFA\uFE0F: " + svet.getCurrentPosition1().getName().toUpperCase());
        kudyMuze();
        //System.out.println("Zvolte směr, kterým chcete jít\uD83E\uDDED: \ns - sever⬆\uFE0F \nj- jih⬇\uFE0F \nz - zapad⬅\uFE0F \nv - vychod➡\uFE0F");s
        System.out.println("\"Zvolte směr, kterým chcete jít\uD83E\uDDED:\"");
        String direction = "";
        direction = sc.next();
        direction = direction.toLowerCase();
        while (!direction.equals("s") && !direction.equals("j") && !direction.equals("z") && !direction.equals("v")) {
            System.out.println("Špatný směr\uD83E\uDDED");
            direction = sc.next();
            direction.toLowerCase();
        }
        System.out.println(svet.pohyb(direction));
        Lov.setLovNacteny(false);
    }
    public void kudyMuze(){
        System.out.println("s - Na sever: " + svet.test("s")+" ⬆\uFE0F"  + "\nj - Na jih: " + svet.test("j") +" ⬇\uFE0F" + "\nz - Na zapad: " + svet.test("z")+" ⬅\uFE0F" + "\nv - Na vychod: " + svet.test("v")+" ➡\uFE0F" );
    }

    @Override
    public boolean exit() {
        return false;
    }
}
