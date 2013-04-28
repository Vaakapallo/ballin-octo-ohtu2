/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import olutopas.Commands.Command;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.model.Beer;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public class PrintBeer extends Command {

    private Beer beer;

    public PrintBeer(Beer beer, EbeanServer server) {
        super(null, server, null);
        this.beer = beer;
    }

    @Override
    public void run() {
        System.out.println(beer);
        List<Rating> ratings = server.find(Rating.class).where().eq("beer", beer).findList();
        if (!ratings.isEmpty()) {
            int average = 0;
            for (Rating rating : ratings) {
                average += rating.getValue();
            }
            System.out.println("number of ratings: " + ratings.size() + " average: " + average / ratings.size());
        } else {
            System.out.println("no ratings");
        }
    }
}
