package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private User user;

    public Application(EbeanServer server) {
        this.server = server;
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        while (true) {
            System.out.println("Login (give ? to register a new user)");
            String username = scanner.nextLine();
            if (username.equals("?")) {
                newUser();
            } else {
                User foundUser = server.find(User.class).where().like("name", username).findUnique();
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
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                findBrewery();
            } else if (command.equals("2")) {
                findBeer();
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("6")) {
                listBeers();
            } else if (command.equals("7")) {
                addBrewery();
            } else if (command.equals("8")) {
                deleteBrewery();
            } else if (command.equals("y")) {
                listUsers();
            } else if (command.equals("t")) {
                ratingsByUser();
            }else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        System.out.println("6   list beers");
        System.out.println("7   add brewery");
        System.out.println("8   delete brewery");
        System.out.println("y   list users");
        System.out.println("t   my ratings");
        System.out.println("0   quit");
        System.out.println("");
    }

    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));

        User u = new User("vaakapallo");
        server.save(u);

        server.save(new Rating(b, u, 5));
    }

    private void listBeers() {
        List<Beer> list = server.find(Beer.class).findList();
        for (Beer beer : list) {
            printBeerWithRatings(beer);
        }
    }

    private void findBeer() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        printBeerWithRatings(foundBeer);

        System.out.println("give rating (leave blank to skip)");
        String input = scanner.nextLine();
        if (!input.equals("")) {
            int value = Integer.parseInt(input);
            if (value != 0) {
                Rating rating = new Rating(foundBeer, user, value);
                server.save(rating);
            }
        }
    }

    private void findBrewery() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }

    private void listBreweries() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void addBrewery() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery exists = server.find(Brewery.class).where().like("name", name).findUnique();

        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        server.save(new Brewery(name));
        System.out.println(name + " added");
    }

    private void deleteBrewery() {
        System.out.print("brewery to delete: ");
        String name = scanner.nextLine();
        Brewery breweryToDelete = server.find(Brewery.class).where().like("name", name).findUnique();

        if (breweryToDelete == null) {
            System.out.println(name + " not found");
            return;
        }

        server.delete(breweryToDelete);
        System.out.println("deleted: " + breweryToDelete);
    }

    private void addBeer() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);
    }

    private void newUser() {
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

    private void listUsers() {
        List<User> list = server.find(User.class).findList();
        System.out.println("users:");
        for (User beer : list) {
            System.out.println(beer);
        }
    }

    private void ratingsByUser() {
        List<Rating> list = server.find(Rating.class).where().eq("user",user).findList();
        for (Rating rating : list) {
            System.out.println(rating);
        }
    }

    private void printBeerWithRatings(Beer foundBeer) {
        System.out.println(foundBeer);
        List<Rating> ratings = server.find(Rating.class).where().eq("beer", foundBeer).findList();
        if (!ratings.isEmpty()) {
            int average = 0;
            for (Rating rating : ratings) {
                average += rating.getValue();
            }

            System.out.println("number of ratings: " + ratings.size() + " average: " + average / ratings.size());
        } else {
            System.out.println("no ratings");
        }
    }
}