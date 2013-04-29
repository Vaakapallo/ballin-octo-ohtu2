/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.List;
import olutopas.Database.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Rating;

/**
 *
 * @author lassi
 */
public class PrintBeer extends Command {

    private Beer beer;

    public PrintBeer(Beer beer, Datamapper mapper) {
        super(null, mapper);
        this.beer = beer;
    }

    @Override
    public void run() {
        System.out.println(beer);
        List<Rating> ratings = mapper.getServer().find(Rating.class).where().eq("beer", beer).findList();
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
