/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas;

import com.avaje.ebean.EbeanServer;
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

    public EbeanServer getServer();
}
