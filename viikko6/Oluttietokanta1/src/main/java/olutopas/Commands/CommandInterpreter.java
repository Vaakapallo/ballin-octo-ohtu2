/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.HashMap;
import java.util.Scanner;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public class CommandInterpreter {

    HashMap<String, Command> commands = new HashMap();

    public CommandInterpreter(Scanner scanner, EbeanServer server, User user) {
        commands.put("1", new FindBrewery(scanner, server, user));
        commands.put("2", new FindBeer(scanner, server, user));
        commands.put("3", new AddBeer(scanner, server, user));
        commands.put("4", new ListBreweries(scanner, server, user));
        commands.put("5", new ListBeers(scanner, server, user));
        commands.put("6", new AddBrewery(scanner, server, user));
        commands.put("7", new ShowRatings(scanner, server, user));
        commands.put("8", new ListUsers(scanner, server, user));
        commands.put("q", new Quit(scanner, server, user));
        commands.put("?", new NewUser(scanner, server, user));

    }

    public Command getCommand(String character) {
        return commands.get(character);
    }
}
