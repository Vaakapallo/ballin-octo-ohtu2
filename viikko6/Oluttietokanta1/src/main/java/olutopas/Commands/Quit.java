/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.Scanner;
import olutopas.Datamapper;

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
