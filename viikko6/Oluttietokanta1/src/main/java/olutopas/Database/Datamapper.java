/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Database;

import com.avaje.ebean.EbeanServer;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public interface Datamapper {

    User getCurrentUser();

    void setCurrentUser(User user);

    public Brewery brewerywithName(String n);

    public Beer beerwithName(String n);
    
    public User userwithName(String n);

    public EbeanServer getServer();
}
