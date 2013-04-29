/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class Quit extends Command {

    public Quit(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    @Override
    public void run() {
        System.out.println("bye");
        System.exit(0);
    }
}
