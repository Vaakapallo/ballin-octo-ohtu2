/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.List;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Rating;

/**
 *
 * @author lassi
 */
class ShowRatings extends Command {

    public ShowRatings(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    @Override
    public void run() {
        List<Rating> list = mapper.getServer().find(Rating.class).where().eq("user", mapper.getCurrentUser()).findList();
        for (Rating rating : list) {
            System.out.println(rating);
        }
    }
}
