/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.Scanner;
import olutopas.Database.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;

/**
 *
 * @author lassi
 */
class AddBeer extends Command {

    public AddBeer(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    @Override
    public void run() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = mapper.brewerywithName(name);

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = mapper.beerwithName(name);
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        mapper.getServer().save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }
}
