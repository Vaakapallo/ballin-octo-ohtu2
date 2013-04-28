/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import olutopas.Commands.PrintBeer;
import olutopas.Commands.Command;
import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.model.Beer;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public class FindBeer extends Command {

    public FindBeer(Scanner scanner, EbeanServer server, User user) {
        super(scanner, server, user);
    }

    @Override
    public void run() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        new PrintBeer(foundBeer, server).run();

        System.out.println("give rating (leave blank to skip)");
        String input = scanner.nextLine();
        if (!input.equals("")) {
            int value = Integer.parseInt(input);
            if (value != 0) {
                Rating rating = new Rating(foundBeer, user, value);
                server.save(rating);
            }
        }
    }
}
