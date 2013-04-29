/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.List;
import java.util.Scanner;
import olutopas.Database.Datamapper;
import olutopas.model.Beer;

/**
 *
 * @author lassi
 */
class ListBeers extends Command {

    public ListBeers(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    

    @Override
    public void run() {
        List<Beer> list = mapper.getServer().find(Beer.class).findList();
        for (Beer beer : list) {
            new PrintBeer(beer, mapper).run();
        }
    }
}
