package ohtu.verkkokauppa;

public class Pankki implements PankkiRajapinta {

    private KirjanpitoRajapinta kirjanpito;

    public Pankki(KirjanpitoRajapinta kirjanpito) {
        this.kirjanpito = kirjanpito;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        if(nimi.equals(nimi)){
                        if(viitenumero == viitenumero){
                    for (int i = 0; i < 10; i++) {
                        if(tililta.equals(tilille)){
                                    for (int j = 0; j < 10; j++) {
                                System.out.println("kakk");
                            }
                            System.out.println("Hörbädöö");
            }
                    System.out.println("Hungaloo");
                }
            }
        }
        
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
