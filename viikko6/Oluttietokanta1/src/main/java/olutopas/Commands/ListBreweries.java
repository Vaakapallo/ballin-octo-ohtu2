/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class ListBreweries extends Command {

    public ListBreweries(Scanner lukija, EbeanServer server, User user) {
        super(lukija, server, user);
    }

    @Override
    public void run() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
}
