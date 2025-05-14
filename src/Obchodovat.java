import java.util.Scanner;

public class Obchodovat extends Command{

    private boolean mesto = false;

    Scanner sc = new Scanner(System.in);
    @Override
    public void execute() {
        nenimesto();
        if(isMesto()){
            vyber();
        }else{
            System.out.println("Nejsi ve městě");
        }

    }
    public void nenimesto(){
        if(!Mapa.getNazevAktualniLokace().equals("Loviště")&&!Mapa.getNazevAktualniLokace().equals("Louka")
                &&!Mapa.getNazevAktualniLokace().equals("Les")&&!Mapa.getNazevAktualniLokace().equals("Zřícenina")
                &&!Mapa.getNazevAktualniLokace().equals("NepřátelskýTábor")&&!Mapa.getNazevAktualniLokace().equals("Převozník")
                &&!Mapa.getNazevAktualniLokace().equals("Cesta")){
                setMesto(true);
        }
    }
    public void vyber(){
        Obchod obchod = new Obchod();
        Batoh batoh = new Batoh();
        System.out.println(">>PRODAT,NAKUP");
        String odpoved = sc.next();
        odpoved = odpoved.toLowerCase();
        switch (odpoved){
            case "prodat":
                if(!Batoh.getBatoh().isEmpty()){
                    batoh.vypisBatoh();
                    System.out.println("CO CHCETE PRODAT >> INDEX:");
                    String test = sc.next();
                    int prodani = 0;
                    while (!test.matches("[0-9]+")||Integer.parseInt(test)>= Batoh.getBatoh().size()){
                        System.out.println("Chybný vstup >> INDEX:");
                        test = sc.next();
                    }
                    prodani = Integer.parseInt(test);

                    Hrac.setPenize(Hrac.getPenize()+Batoh.getBatoh().get(prodani).getCena());
                    Batoh.setAktualniKapacita(Batoh.getAktualniKapacita()+Batoh.getBatoh().get(prodani).getVaha());
                    if(Batoh.getBatoh().get(prodani).isPouzivany()){
                        Hrac.setSila(10);
                    }
                    Batoh.getBatoh().remove(prodani);
                    batoh.vypisBatoh();
                    System.out.println(Hrac.getPenize());
                }else{
                    System.out.println("NEMÁŠ CO PRODAT");
                }
                break;
            case "nakup":
                if(Hrac.getPenize()>0){
                    System.out.println(Hrac.getPenize());
                    obchod.nacistItemy();
                    System.out.println("CO CHCETE NAKOUPIT >> INDEX:");
                    String test2 = sc.next();
                    int nakoupeni = 0;
                    while (!test2.matches("[0-9]+")||Integer.parseInt(test2)>= Obchod.getObchod().size()){
                        System.out.println("Chybný vstup >> INDEX:");
                        test2 = sc.next();
                    }
                    nakoupeni = Integer.parseInt(test2);
                    if(Hrac.getPenize()>= Obchod.getObchod().get(nakoupeni).getCena()){
                        Hrac.setPenize(Hrac.getPenize()-Obchod.getObchod().get(nakoupeni).getCena());
                        Batoh.setAktualniKapacita(Batoh.getAktualniKapacita()-Obchod.getObchod().get(nakoupeni).getVaha());
                        Batoh.getBatoh().add(Obchod.getObchod().get(nakoupeni));
                        batoh.vypisBatoh();
                        System.out.println(Hrac.getPenize());
                    }else{
                        System.out.println("NEMÁŠ DOSTATEK PENĚZ");
                    }
                }else{
                    System.out.println("NEMÁŠ ŽÁDNÉ PENÍZE");
                }

                break;
            default:
                System.out.println("Není možnost");
               vyber();

        }
    }

    public boolean isMesto() {
        return mesto;
    }

    public void setMesto(boolean mesto) {
        this.mesto = mesto;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
