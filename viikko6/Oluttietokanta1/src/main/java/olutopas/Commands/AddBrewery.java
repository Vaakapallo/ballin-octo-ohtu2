/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Brewery;

/**
 *
 * @author lassi
 */
class AddBrewery extends Command {

    public AddBrewery(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    @Override
    public void run() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery exists = mapper.brewerywithName(name);

        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        mapper.getServer().save(new Brewery(name));
        System.out.println(name + " added");
    }
}
