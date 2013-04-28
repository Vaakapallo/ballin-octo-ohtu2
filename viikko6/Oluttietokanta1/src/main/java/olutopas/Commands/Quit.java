/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class Quit extends Command {
    
    public Quit(Scanner lukija, EbeanServer server, User user) {
        super(lukija, server, user);
    }
    
    @Override
    public void run() {
        System.out.println("bye");
        System.exit(0);
    }
}
