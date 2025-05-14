import java.util.Random;

public class Main {
    public static void main(String[] args) {


        Random rd = new Random();
/*
        for (int i = 0; i < 50; i++) {
            int cislo = rd.nextInt(4);
            System.out.println(cislo);

        }

 */

        Konzole k = new Konzole();
        Batoh b = new Batoh();
        b.pridatPredmet(new Predmet("predmet",20,20,20,0,TypPredmetu.ZBRAN));
        b.pridatPredmet(new Predmet("odvar", 20,0, 10,50, TypPredmetu.LEKTVAR));
        b.pridatPredmet(new Predmet("mec",20,30,30,0,TypPredmetu.ZBRAN));
        b.pridatPredmet(new Predmet("maso",20,30,10,0,TypPredmetu.ULOVEK));
        k.start();
/*
        Predmet p = new Predmet("něco",20,20,TypPredmetu.ULOVEK);
        p.setJeKradeny(true);
        System.out.println(p);

 */










    }
}