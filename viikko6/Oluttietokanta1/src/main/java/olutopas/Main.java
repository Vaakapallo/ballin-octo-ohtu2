package olutopas;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import olutopas.model.Beer;
import olutopas.model.Brewery;

import com.avaje.ebean.Transaction;
import olutopas.model.Pub;
import olutopas.model.Rating;
import olutopas.model.User;

public class Main {

    public static void main(String[] args) {
        boolean dropAndCreateTables = true;
        Datamapper mapper = new EbeanSqliteDatamapper("jdbc:sqlite:beer.db", dropAndCreateTables, Beer.class, Brewery.class, Rating.class, User.class);
        new Application(mapper).run();
    }
}
