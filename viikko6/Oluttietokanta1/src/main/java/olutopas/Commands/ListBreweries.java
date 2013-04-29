/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class ListBreweries extends Command {

    public ListBreweries(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    @Override
    public void run() {
        List<Brewery> breweries = mapper.getServer().find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
}
