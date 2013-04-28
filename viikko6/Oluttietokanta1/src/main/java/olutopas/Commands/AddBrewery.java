/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class AddBrewery extends Command {

    public AddBrewery(Scanner lukija, EbeanServer server, User user) {
        super(lukija, server, user);
    }

    @Override
    public void run() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery exists = server.find(Brewery.class).where().like("name", name).findUnique();

        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        server.save(new Brewery(name));
        System.out.println(name + " added");
    }
}
