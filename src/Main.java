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

        b.pridatPredmet(new Predmet("Odvar", 20,0, 1,50, TypPredmetu.LEKTVAR));
        b.pridatPredmet(new Predmet("Meč",20,30,3,0,TypPredmetu.ZBRAN));
        b.pridatPredmet(new Predmet("Maso",20,30,1,0,TypPredmetu.ULOVEK));
        b.pridatPredmet(new Predmet("Odvar", 20,0, 1,50, TypPredmetu.LEKTVAR));
        b.pridatPredmet(new Predmet("Meč",20,30,3,0,TypPredmetu.ZBRAN));
        b.pridatPredmet(new Predmet("Maso",20,30,1,0,TypPredmetu.ULOVEK));
        k.start();



        //dodělat kytky
        //začít načítat lidi do měst a enemáky do nepř. táboru
/*
        Predmet p = new Predmet("něco",20,20,TypPredmetu.ULOVEK);
        p.setJeKradeny(true);
        System.out.println(p);

 */










    }
}