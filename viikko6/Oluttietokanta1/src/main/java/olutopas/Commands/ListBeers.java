/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.model.Beer;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class ListBeers extends Command {

    public ListBeers(Scanner lukija, EbeanServer server, User user) {
        super(lukija, server, user);
    }

    @Override
    public void run() {
        List<Beer> list = server.find(Beer.class).findList();
        for (Beer beer : list) {
            new PrintBeer(beer, server).run();
        }
    }
}
