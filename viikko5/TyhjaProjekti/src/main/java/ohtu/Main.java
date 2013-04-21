package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        String studentNr = "13766933";
        if ( args.length>0) {
            studentNr = args[0];
        }
 
        String url = "http://ohtustats-2013.herokuapp.com/opiskelija/"+studentNr+".json";
 
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
 
        InputStream stream =  method.getResponseBodyAsStream();
 
        String bodyText = IOUtils.toString(stream);
 
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );
 
        Gson mapper = new Gson();
        Palautukset palautukset = mapper.fromJson(bodyText, Palautukset.class);
 
        System.out.println("Opiskelijanumero + " + studentNr);
        int tehtavia = 0;
        int tuntia = 0;
        for (Palautus palautus : palautukset.getPalautukset()) {
            System.out.print(palautus.getViikko() + ": " );
            System.out.print(palautus.getTehtavia() + " tehtävää ");
            System.out.print(palautus.getTehtavat());
            System.out.println("    aikaa kului " + palautus.getTunteja() + " tuntia");
            tehtavia += palautus.getTehtavia();
            tuntia += palautus.getTunteja();
        }
        System.out.println("yhteensä " + tehtavia + " tehtävää " + tuntia + " tuntia");
    }
    
//    opiskelijanumero 123456578
// 
//viikko 1: 5 tehtävää 1, 2, 5, 7, 8      aikaa kului 12 tuntia
//viikko 2: 3 tehtävää 1, 4, 5            aikaa kului  3 tuntia
// 
//yhteensä  8 tehtävää 15 tuntia

}
