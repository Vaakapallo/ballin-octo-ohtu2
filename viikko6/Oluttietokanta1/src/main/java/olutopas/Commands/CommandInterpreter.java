/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.HashMap;
import java.util.Scanner;
import olutopas.Database.Datamapper;

/**
 *
 * @author lassi
 */
public class CommandInterpreter {

    HashMap<String, Command> commands = new HashMap();

    public CommandInterpreter(Scanner scanner, Datamapper mapper) {
        commands.put("1", new FindBrewery(scanner, mapper));
        commands.put("2", new FindBeer(scanner, mapper));
        commands.put("3", new AddBeer(scanner, mapper));
        commands.put("4", new ListBreweries(scanner, mapper));
        commands.put("5", new ListBeers(scanner, mapper));
        commands.put("6", new AddBrewery(scanner, mapper));
        commands.put("7", new ShowRatings(scanner, mapper));
        commands.put("8", new ListUsers(scanner, mapper));
        commands.put("q", new Quit(scanner, mapper));
        commands.put("?", new NewUser(scanner, mapper));
    }

    public Command getCommand(String character) {
        return commands.get(character);
    }
}
