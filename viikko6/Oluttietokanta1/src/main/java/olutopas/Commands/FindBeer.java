/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Rating;

/**
 *
 * @author lassi
 */
public class FindBeer extends Command {

    public FindBeer(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    

    @Override
    public void run() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = mapper.getServer().find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        new PrintBeer(foundBeer, mapper).run();

        System.out.println("give rating (leave blank to skip)");
        String input = scanner.nextLine();
        if (!input.equals("")) {
            int value = Integer.parseInt(input);
            if (value != 0) {
                Rating rating = new Rating(foundBeer, mapper.getCurrentUser(), value);
                mapper.getServer().save(rating);
            }
        }
    }
}
