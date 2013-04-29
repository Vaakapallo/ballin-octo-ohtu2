package olutopas;

import olutopas.Commands.CommandInterpreter;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.Commands.Command;
import olutopas.Commands.NewUser;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Pub;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private Datamapper mapper;
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private CommandInterpreter commands;

    public Application(Datamapper mapper) {
        this.mapper = mapper;
        mapper.setCurrentUser(user);
        commands = new CommandInterpreter(scanner, mapper);
    }

    public void run() {
        while (true) {
            System.out.println("Login (give ? to register a new user)");
            String username = scanner.nextLine();
            if (username.equals("?")) {
                new NewUser(scanner, mapper).run();
            } else {
                User foundUser = mapper.getServer().find(User.class).where().like("name", username).findUnique();
                if (foundUser != null) {
                    user = foundUser;
                    break;
                }
            }
        }

        System.out.println("Welcome!");

        while (true) {
            menu();
            System.out.print("> ");
            Command command = commands.getCommand(scanner.nextLine());
            if (command != null) {
                command.run();
            } else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   list beers");
        System.out.println("6   add brewery");
        System.out.println("7   my ratings");
        System.out.println("8   list users");
        System.out.println("q   quit");
        System.out.println("");
    }
    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
}
