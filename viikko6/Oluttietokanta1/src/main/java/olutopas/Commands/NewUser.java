/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public class NewUser extends Command {

    public NewUser(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    @Override
    public void run() {
        System.out.println("Register new user");
        System.out.println("give username:");
        String name = scanner.nextLine();
        User exists = mapper.getServer().find(User.class).where().like("name", name).findUnique();

        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        mapper.getServer().save(new User(name));
        System.out.println("user created");
    }
}
