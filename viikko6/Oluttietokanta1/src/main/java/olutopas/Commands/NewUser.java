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
public class NewUser extends Command {

    public NewUser(Scanner lukija, EbeanServer server, User user) {
        super(lukija, server, user);
    }

    @Override
    public void run() {
        System.out.println("Register new user");
        System.out.println("give username:");
        String name = scanner.nextLine();
        User exists = server.find(User.class).where().like("name", name).findUnique();

        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        server.save(new User(name));
        System.out.println("user created");
    }
}
