/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.List;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
class ListUsers extends Command {

    public ListUsers(Scanner scanner, Datamapper mapper) {
        super(scanner, mapper);
    }

    @Override
    public void run() {
        List<User> list = mapper.getServer().find(User.class).findList();
        System.out.println("users:");
        for (User beer : list) {
            System.out.println(beer);
        }
    }
}
