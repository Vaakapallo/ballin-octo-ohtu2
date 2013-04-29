/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class FindBrewery extends Command {

    public FindBrewery(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    

    @Override
    public void run() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = mapper.getServer().find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }
}
