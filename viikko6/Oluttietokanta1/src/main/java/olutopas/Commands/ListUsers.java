/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class ListUsers extends Command {

    public ListUsers(Scanner lukija, EbeanServer server, User user) {
        super(lukija, server, user);
    }

    @Override
    public void run() {
        List<User> list = server.find(User.class).findList();
        System.out.println("users:");
        for (User beer : list) {
            System.out.println(beer);
        }
    }
}
