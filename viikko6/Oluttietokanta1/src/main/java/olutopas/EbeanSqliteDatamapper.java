/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Pub;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public class EbeanSqliteDatamapper implements Datamapper {

    private Class[] luokat;
    private EbeanServer server;
    private String tietokantaUrl;
    private boolean dropAndCreate;
    private User user;

    public EbeanSqliteDatamapper(String tietokantaUrl, boolean dropAndCreate, Class... luokat) {
        this.luokat = luokat;
        this.dropAndCreate = dropAndCreate;
        this.tietokantaUrl = tietokantaUrl;
        server = initializeDatabase(dropAndCreate, Database.SQLite);
        init();
    }

    enum Database {

        H2, SQLite
    }

    public void init() {
        if (dropAndCreate) {
            seedDatabase();
        }
    }

    private static EbeanServer initializeDatabase(boolean dropAndCreateDatabase, Database db) {
        ServerConfig config = new ServerConfig();
        config.setName("beerDb");

        if (db == Database.H2) {
            DataSourceConfig hdDB = new DataSourceConfig();
            hdDB.setDriver("org.h2.Driver");
            hdDB.setUsername("test");
            hdDB.setPassword("test");
            hdDB.setUrl("jdbc:h2:mem:tests;DB_CLOSE_DELAY=-1");
            hdDB.setHeartbeatSql("select 1 ");
            config.setDataSourceConfig(hdDB);
        }

        if (db == Database.SQLite) {
            DataSourceConfig sqLite = new DataSourceConfig();
            sqLite.setDriver("org.sqlite.JDBC");
            sqLite.setUsername("mluukkai");
            sqLite.setPassword("mluukkai");
            //sqLite.setUrl("jdbc:sqlite:/home/mluukkai/sqlite/kannat/beer.db");
            sqLite.setUrl("jdbc:sqlite:beer.db");
            config.setDataSourceConfig(sqLite);
            config.setDatabasePlatform(new SQLitePlatform());
            config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
        }

        config.setDefaultServer(false);
        config.setRegister(false);

        config.addClass(Beer.class);
        config.addClass(Brewery.class);
        config.addClass(User.class);
        config.addClass(Rating.class);
        config.addClass(Pub.class);


        if (dropAndCreateDatabase) {
            config.setDdlGenerate(true);
            config.setDdlRun(true);
            //config.setDebugSql(true);
        }

        return EbeanServerFactory.create(config);
    }

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

        server.save(new Pub("Pikkulintu"));
    }

    @Override
    public Brewery brewerywithName(String n) {
        return server.find(Brewery.class).where().like("name", n).findUnique();
    }

    // muut metodit
    // apumetodi, jonka avulla Application-olio pääsee aluksi käsiksi EbeanServer-olioon
    @Override
    public EbeanServer getServer() {
        return server;
    }

    @Override
    public User getCurrentUser() {
        return user;
    }

    @Override
    public void setCurrentUser(User user) {
        this.user = user;
    }
}
