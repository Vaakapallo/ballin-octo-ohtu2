/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class ShowRatings extends Command {

    public ShowRatings(Scanner lukija, EbeanServer server, User user) {
        super(lukija, server, user);
    }

    @Override
    public void run() {
        List<Rating> list = server.find(Rating.class).where().eq("user", user).findList();
        for (Rating rating : list) {
            System.out.println(rating);
        }
    }
}
