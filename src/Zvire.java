import java.util.ArrayList;

public class Zvire {
    private String jmeno;
    private int zivoty;
    private Predmet predmet;
    private ArrayList<Predmet>korist = new ArrayList<>();

    public Zvire(String jmeno, int zivoty, Predmet predmet,Predmet predmet1,Predmet predmet2) {
        this.jmeno = jmeno;
        this.zivoty = zivoty;
        this.korist.add(predmet);
        this.korist.add(predmet1);
        this.korist.add(predmet2);


    }

    @Override
    public String toString() {
        return "\nZVIRE: " + jmeno + " ,ZIVOTY: " + zivoty + ",KORIST: " + korist;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }

    public ArrayList<Predmet> getKorist() {
        return korist;
    }

    public void setKorist(ArrayList<Predmet> korist) {
        this.korist = korist;
    }
}
