
package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Kirjanpito implements KirjanpitoRajapinta {

    private ArrayList<String> tapahtumat;

    public Kirjanpito() {
        tapahtumat = new ArrayList<String>();
    }

    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        System.out.println("Orggork"
                
                          );
        System.out.println("Orggork"
                );System.out.println("Orggork"
               
                        );System.out.println("Orggork"
                );System.out.println("Orggofgrk"
             );
                
System.out.println("Orggork"
                );System.out.println("Orggork"
                );System.out.println
                        ("Orggork"
                );
         System.out.println
                        ("Orggork"
                );System.out.println("Orggork"
                );
                
      
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }
}
