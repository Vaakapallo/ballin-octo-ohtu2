package olutopas;

import olutopas.Database.Datamapper;
import olutopas.Database.EbeanSqliteDatamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;

import olutopas.model.Rating;
import olutopas.model.User;

public class Main {

    public static void main(String[] args) {
        boolean dropAndCreateTables = true;
        Datamapper mapper = new EbeanSqliteDatamapper("jdbc:sqlite:beer.db", dropAndCreateTables, Beer.class, Brewery.class, Rating.class, User.class);
        new Application(mapper).run();
    }
}
